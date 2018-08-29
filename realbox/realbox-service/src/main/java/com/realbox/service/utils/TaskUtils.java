/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.alibaba.fastjson.JSON;
import com.realbox.common.utils.AESUtils;
import com.realbox.common.utils.Maps;
import com.realbox.model.*;
import com.realbox.model.bean.terminal.AdvAdvtask;
import com.realbox.model.bean.terminal.AdvBean;
import com.realbox.model.bean.terminal.AdvDataBean;
import com.realbox.model.bean.terminal.AdvPropertyBean;
import com.realbox.model.enums.Task;
import com.realbox.repository.mongodb.mapper.*;
import com.realbox.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author MJJ
 * @create Id: TaskUtils.java v 0.1 2017年12月30日 20:14 MJJ Exp $
 **/
@Component
public class TaskUtils {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private TerminalMapper terminalMapper;

    @Autowired
    private ActivateMapper activateMapper;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private PublishUtils publishUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogUtils logUtils;

    @Value("${comKey}")
    String comKey;

    /**
     * 任务管理
     *
     * @param taskName 任务名
     * @param json     终端请求Json数据
     */
    public void taskManage(String taskName, String json) {
        Task task = Task.getTask(taskName);
        if (task == null) {
            return;
        }
        switch (task) {
            // 登录
            case LOGIN:
                login(json);
                break;
            // 终端信息同步
            case SYNC:
                programList(json);
                break;
            // 心跳包
            case HEART:
                heartbeat(json);
                break;
            case INFO:
                // 查询节目信息
                programInfo(json);
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     *
     * @param json 登录数据
     */
    private void login(String json) {
        // 解析终端实体类
        AdvBean bean = JSON.parseObject(json, AdvBean.class);
        AdvDataBean data = bean.getInformation().getAdvTask().getData();
        // 验证终端是否激活
        Map<String, Object> cond = Maps.create().add("terminalCode", data.getTermialCode()).get();
        Terminal entity = terminalMapper.query(cond, Terminal.class);
        if (entity != null) {
            // 设置终端参数
            Terminal terminal = new Terminal();
            setTerminal(data, entity, terminal);
            // 修改终端信息
            terminalMapper.update(entity.getId(), terminal);
            // 响应终端(成功)
            response(json, data.getTermialCode(), 1);
            // 记录日志
            logUtils.systemLog("终端登录");
        } else {
            // 响应终端(失败)
            response(json, data.getTermialCode(), 0);
            // 记录日志
            logUtils.systemLog("终端登录失败,终端不存在");
        }
    }

    /**
     * 响应终端
     *
     * @param json         自定义返回对象
     * @param terminalCode 终端硬件号
     * @param type         返回类型(0:失败,1:成功)
     */
    private void response(String json, String terminalCode, int type) {
        AdvBean advBean = JSON.parseObject(json, AdvBean.class);
        AdvAdvtask task = advBean.getInformation().getAdvTask();
        AdvDataBean data = new AdvDataBean();
        switch (type) {
            case 0:
                data.setErrCode("9999");
                data.setErrMsg("终端不存在");
                break;
            case 1:
                data.setErrCode("0000");
                data.setErrMsg("登录成功");
                break;
            default:
                break;
        }
        AdvPropertyBean property = task.getProperty();
        property.setTaskKind("notify");

        task.setProperty(property);
        task.setData(data);

        // AES加密
        json = JSON.toJSONString(advBean);
        try {
            json = AESUtils.encrypt(comKey, json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 发布
        rabbitService.sendSingle(terminalCode, json);
    }

    /**
     * 终端信息同步
     *
     * @param json 同步数据
     */
    private void programList(String json) {
        // 获取终端实体类
        AdvBean bean = JSON.parseObject(json, AdvBean.class);
        AdvDataBean data = bean.getInformation().getAdvTask().getData();

        // 查询终端
        Map<String, Object> cond = Maps.create().add("terminalCode", data.getTermialCode()).get();
        Terminal terminal = terminalMapper.query(cond, Terminal.class);

        // 查询终端对应的节目
        cond = Maps.create().add("terminalId", terminal.getId()).add("status", "已发布").get();
        List<PublishManage> publish = publishMapper.queryList(cond, PublishManage.class);

        // 返回终端同步数据
        AdvPropertyBean property = bean.getInformation().getAdvTask().getProperty();
        property.setTaskKind("reply");
        property.setReplyId(property.getTaskId());

        // 同步信息“单发”给终端
        if (publish.isEmpty()) {
            // AES加密
            String result = JSON.toJSONString(bean);
            try {
                result = AESUtils.encrypt(comKey, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rabbitService.sendSingle(data.getTermialCode(), result);
        } else {
            // 设置发布节目参数
            setPublishId(publish, data);

            // AES加密
            String result = JSON.toJSONString(bean);
            try {
                result = AESUtils.encrypt(comKey, result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            rabbitService.sendSingle(data.getTermialCode(), result);
        }

        // 记录日志
        logUtils.systemLog("终端同步节目信息");
    }

    /**
     * 心跳包
     *
     * @param json 心跳数据
     */
    private void heartbeat(String json) {
        // 获取数据
        AdvBean bean = JSON.parseObject(json, AdvBean.class);
        AdvDataBean data = bean.getInformation().getAdvTask().getData();

        // Redis存储心跳标记
        redisTemplate.opsForValue().set(data.getTermialCode(), data.getTermialCode(), 1, TimeUnit.MINUTES);
    }

    /**
     * 查询节目信息
     *
     * @param json 查询数据
     */
    private void programInfo(String json) {
        // 获取数据
        AdvBean bean = JSON.parseObject(json, AdvBean.class);
        AdvDataBean data = bean.getInformation().getAdvTask().getData();

        // 查询发布任务
        List<PublishManage> publish = publishMapper.batchQueryById(data.getProgrammeid(), PublishManage.class);
        for (PublishManage pub : publish) {
            // 查询节目
            Program program = programMapper.queryById(pub.getProId(), Program.class);

            // 查询终端
            Terminal terminal = terminalMapper.queryById(pub.getTerminalId(), Terminal.class);

            // AES加密
            json = publishUtils.getPublishData(pub, program);
            try {
                json = AESUtils.encrypt(comKey, json);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 发布节目
            rabbitService.sendSingle(terminal.getTerminalCode(), json);

            // 记录日志
            logUtils.systemLog("终端查询节目信息");
        }
    }

    /**
     * 获取发布节目ID
     *
     * @param publish 发布节目对象(List集合)
     */
    private void setPublishId(List<PublishManage> publish, AdvDataBean data) {
        List<String> ids = new ArrayList<String>();
        for (PublishManage pub : publish) {
            ids.add(pub.getId());
        }
        data.setProgrammelistbean(ids);
    }

    /**
     * 设置终端参数
     *
     * @param data     终端实体对象
     * @param data     终端实体对象
     * @param terminal 终端对象
     */
    private void setTerminal(AdvDataBean data, Terminal entity, Terminal terminal) {
        // 终端组ID(终端树ID)、所属部门ID
        Map<String, Object> cond = Maps.create().add("name", data.getTermialCode()).get();
        Activate activate = activateMapper.query(cond, Activate.class);
        if (activate != null) {
            terminal.setGroupId(activate.getTerId());
        } else {
            terminal.setGroupId("41");
        }

        // 用户ID
        cond = Maps.create().add("name", data.getCreator()).get();
        User user = userMapper.query(cond, User.class);
        if (user != null) {
            terminal.setUserId(user.getId());
        } else {
            cond = Maps.create().add("name", "admin").get();
            user = userMapper.query(cond, User.class);
            terminal.setUserId(user.getId());
        }

        terminal.setId(entity.getId());                             // ID
        terminal.setCreator(data.getCreator());                     // 创建人
        terminal.setName(data.getTermialCode());                    // 终端名称
        terminal.setIp(data.getIp());                               // IP
        terminal.setMac(data.getMac());                             // MAC
        terminal.setType(data.getType());                           // 终端类型
        terminal.setResolution(data.getResolution());               // 分辨率
        terminal.setTerminalCode(data.getTermialCode());            // 硬件号
        terminal.setDiskSpace(data.getDiskspace());                 // 磁盘空间
        terminal.setDisplayDriver(data.getDisplaydriver());         // 驱动
        terminal.setBoardType(data.getBoardtype());                 // 版卡类型
        terminal.setVersion(data.getVersion());                     // 版本
        terminal.setSversion(data.getSversion());                   // 服务版本
        terminal.setDownloadStatus(data.getDownloadstatus());       // 下载状态
        terminal.setUpdateTime(data.getUpdatetime());               // 更新时间
        terminal.setLastManualOpeTime(data.getLastManualOpetime()); // 最后打开时间
        terminal.setLastPublishTime(data.getLastpublishtime());     // 最后发布时间
        terminal.setLastUpdateTime(data.getLastupdatetime());       // 最后更新时间
        terminal.setLastSettingTime(data.getLastsettingtime());     // 最后设置时间
        terminal.setBandLimit(data.getBandlimit());
        terminal.setCustomer(data.getCustomer());
        terminal.setInterGrate(data.getIntergrate());               // 完整性
        terminal.setStatus("1");                                    // 终端在线状态
        terminal.setActStatus(entity.isActStatus());                // 终端激活状态
    }
}
