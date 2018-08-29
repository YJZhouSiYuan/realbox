/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.alibaba.fastjson.JSON;
import com.realbox.common.utils.AESUtils;
import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.Activate;
import com.realbox.model.PublishManage;
import com.realbox.model.Terminal;
import com.realbox.model.bean.terminal.*;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.ActivateMapper;
import com.realbox.repository.mongodb.mapper.PublishMapper;
import com.realbox.repository.mongodb.mapper.TerminalMapper;
import com.realbox.service.RabbitService;
import com.realbox.service.TerminalService;
import com.realbox.service.utils.AsyncUtils;
import com.realbox.service.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author MJJ
 * @create Id: TerminalServiceImpl.java v 0.1 2017年12月12日 10:49 MJJ Exp $
 **/
@Service("terminalService")
public class TerminalServiceImpl extends BaseService implements TerminalService {

    private static Logger logger = Logger.getLogger(TerminalService.class);

    // 服务器
    @Value("${spring.rabbitmq.host}")
    String host;

    // 端口
    @Value("${spring.rabbitmq.port}")
    String port;

    // 用户
    @Value("${spring.rabbitmq.username}")
    String user;

    // 密码
    @Value("${spring.rabbitmq.password}")
    String password;

    // 广播交换机
    @Value("${advBroadExchange}")
    String advBroadExchange;

    // 广播routingKey
    @Value("${advBroadRKey}")
    String advBroadRKey;

    // 终端routingKey
    @Value("${advBackRKey}")
    String advBackRKey;

    // 终端交换机
    @Value("${advExchange}")
    String advExchange;

    // 单发交换机
    @Value("${advSingleExchange}")
    String advSingleExchange;

    @Value("${comKey}")
    String comKey;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private TerminalMapper terminalMapper;

    @Autowired
    private ActivateMapper activateMapper;

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogUtils logUtils;

    /**
     * 查询终端
     *
     * @param groupId   终端分组树ID
     * @param name      终端名称
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryTerminal(String groupId, String name, String code, Integer pageNo, Integer pageCount) {
        // 查询数量
        Map<String, Object> cond = Maps.create().add("groupId", groupId).add("name", name).add("terminalCode", code).get();
        long count = terminalMapper.count(cond, Terminal.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询终端
        List<Terminal> terminals = terminalMapper.queryList(cond, pages, Terminal.class);

        // 查询在线终端
        for (Terminal terminal : terminals) {
            String tick = redisTemplate.opsForValue().get(terminal.getTerminalCode());
            if (StringUtils.isEmpty(tick)) {
                terminal.setStatus("0");
            } else {
                terminal.setStatus("1");
            }
        }

        // 返回
        Map<String, Object> map = Maps.create()
                .add("terminals", terminals)
                .add("pages", pages)
                .get();
        return success(map);
    }

    /**
     * 删除终端
     *
     * @param ids 终端ID
     * @return
     */
    @Override
    public RespEntity<String> deleteTerminal(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.TERMINAL000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证终端
        List<Terminal> terminals = terminalMapper.batchQueryById(arrayId, Terminal.class);
        if (terminals.isEmpty()) {
            return errors(RealBox.TERMINAL003);
        }

        // 删除终端
        terminalMapper.batchDelete(arrayId, Terminal.class);

        // 记录日志
        logUtils.userLog("删除终端", request);

        // 返回
        return success();
    }

    /**
     * 激活终端
     *
     * @param code 激活码
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> activate(String code) {
        if (StringUtils.isEmpty(code)) {
            return error(RealBox.ACTIV002);
        }
        // AES解密
        try {
            code = code.replace(" ", "+");
            code = AESUtils.decrypt(comKey, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 验证激活码
        Map<String, Object> cond = Maps.create("code", code).get();
        Activate activate = activateMapper.query(cond, Activate.class);
        if (activate == null) {
            return error(RealBox.ACTIV003);
        }
        if ("0".equals(activate.getUsed())) {
            return error(RealBox.ACTIV004);
        }
        // 编辑激活码状态
        cond = Maps.create().add("used", "0").get();
        activateMapper.update(activate.getId(), cond, Activate.class);
        // 新增终端(添加终端状态)
        Terminal terminal = new Terminal();
        terminal.setName(activate.getCode());           // 终端名称
        terminal.setTerminalCode(activate.getName());   // 硬件号
        terminal.setActStatus(true);                    // 激活状态
        terminal.setGroupId(activate.getTerId());       // 树ID
        terminalMapper.create(terminal);
        // 记录日志
        logUtils.systemLog("终端激活" + code + "(激活码)");
        // AES加密
        String newHost = null;
        String newPort = null;
        String newUser = null;
        String newPassword = null;
        String newAdvBroadExchange = null;
        String newAdvBroadRKey = null;
        String newAdvSingleExchange = null;
        String newAdvExchange = null;
        String newAdvBackRKey = null;
        String newTerminalCode = null;
        try {
            newHost = AESUtils.encrypt(comKey, host);
            newPort = AESUtils.encrypt(comKey, port);
            newUser = AESUtils.encrypt(comKey, user);
            newPassword = AESUtils.encrypt(comKey, password);
            newAdvBroadExchange = AESUtils.encrypt(comKey, advBroadExchange);
            newAdvBroadRKey = AESUtils.encrypt(comKey, advBroadRKey);
            newAdvSingleExchange = AESUtils.encrypt(comKey, advSingleExchange);
            newAdvExchange = AESUtils.encrypt(comKey, advExchange);
            newAdvBackRKey = AESUtils.encrypt(comKey, advBackRKey);
            newTerminalCode = AESUtils.encrypt(comKey, activate.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回
        Map<String, Object> map = Maps.create()
                .add("host", newHost)                          // 服务器地址
                .add("port", newPort)                          // 服务器端口
                .add("user", newUser)                          // 服务器用户名
                .add("password", newPassword)                  // 服务器密码
                .add("advBroadExchange", newAdvBroadExchange)  // 广播交换机名称
                .add("advBroadRKey", newAdvBroadRKey)          // 广播交换机routingKey
                .add("advSingleExchange", newAdvSingleExchange)// 单发交换机名称
                .add("advExchange", newAdvExchange)            // 终端返回交换机名称
                .add("advBackRKey", newAdvBackRKey)            // 终端返回routingKey
                .add("terminalCode", newTerminalCode)          // 硬件号
                .get();
        return success(map);
    }

    /**
     * 服务器强制终端同步
     *
     * @param ids 终端ID
     * @return
     */
    @Override
    public RespEntity<String> aynTerminal(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.TERMINAL000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        for (String id : arrayId) {
            // 验证终端
            Terminal terminal = terminalMapper.queryById(id, Terminal.class);
            if (terminal == null) {
                return errors(RealBox.TERMINAL003);
            }

            // 查询节目
            Map<String, Object> cond = Maps.create().add("terminalId", id).get();
            List<PublishManage> publish = publishMapper.queryList(cond, PublishManage.class);

            List<String> publishId = new ArrayList<String>();
            for (PublishManage pub : publish) {
                publishId.add(pub.getId());
            }

            // 设置终端同步数据
            AdvBean bean = new AdvBean();
            setAynTerminal(bean, publishId);

            // AES加密
            String json = JSON.toJSONString(bean);
            try {
                json = AESUtils.encrypt(comKey, json);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 发布
            rabbitService.sendSingle(terminal.getTerminalCode(), json);
        }

        // 记录日志
        logUtils.systemLog("服务器强制终端同步");

        // 记录日志
        logUtils.userLog("服务器强制终端同步",request);

        return success();
    }

    /**
     * 设置终端同步数据
     *
     * @param bean 自定义实体对象
     */
    private void setAynTerminal(AdvBean bean, List<String> ids) {
        AdvDataBean data = new AdvDataBean();
        data.setProgrammelistbean(ids);

        AdvPropertyBean property = new AdvPropertyBean();
        property.setTaskId(UUID.randomUUID().toString());
        property.setTaskKind("notify");
        property.setTaskName("progammesync");

        AdvAdvtask task = new AdvAdvtask();
        task.setData(data);
        task.setProperty(property);

        AdvInformationBean information = new AdvInformationBean();
        information.setAdvTask(task);

        bean.setHead("REALTOPTV_WebCommunicationProtocol_V1.0");
        bean.setServerId("REALTOPTV_InformationPublishSystem_074532010");
        bean.setPublishTime(DateUtils.getDateTime());
        bean.setDescription("Advertising");
        bean.setLevel(1);
        bean.setUser("45364442534536444253453644425332");
        bean.setInformation(information);
    }
}
