/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.AESUtils;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.Program;
import com.realbox.model.PublishManage;
import com.realbox.model.Terminal;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.ProgramMapper;
import com.realbox.repository.mongodb.mapper.PublishMapper;
import com.realbox.repository.mongodb.mapper.TerminalMapper;
import com.realbox.service.PublishService;
import com.realbox.service.RabbitService;
import com.realbox.service.utils.LogUtils;
import com.realbox.service.utils.PublishUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: PublishServiceImpl.java v 0.1 2017年12月31日 20:51 MJJ Exp $
 **/
@Service("publishService")
public class PublishServiceImpl extends BaseService implements PublishService {
    @Autowired
    private TerminalMapper terminalMapper;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PublishUtils publishUtils;

    @Autowired
    private LogUtils logUtils;

    @Value("${comKey}")
    String comKey;

    /**
     * 查询发布任务管理
     *
     * @param proName   节目名称
     * @param proStatus 节目状态
     * @param status    发布状态
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryPublish(String proName,
                                                        String proStatus,
                                                        String status,
                                                        String startTime,
                                                        String endTime,
                                                        Integer pageNo,
                                                        Integer pageCount) {
        // 查询数量
        Map<String, Object> cond = Maps.create()
                .add("proName", proName).add("proStatus", proStatus).add("status", status)
                .add("startTime", startTime).add("endTime", endTime).get();
        long count = publishMapper.count(cond, PublishManage.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询发布任务管理
        List<PublishManage> publish = publishMapper.queryList(cond, pages, PublishManage.class);

        // 返回
        Map<String, Object> map = Maps.create()
                .add("publish", publish)
                .add("pages", pages)
                .get();
        return success(map);
    }

    /**
     * 新增发布任务管理
     *
     * @param publish 发布任务管理对象
     * @return
     */
    @Override
    public RespEntity<String> createPublish(PublishManage publish) {
        if (StringUtils.isEmpty(publish.getTerminalId())) {
            return errors(RealBox.TERMINAL000);
        }
        if (StringUtils.isEmpty(publish.getProId())) {
            return errors(RealBox.PROGRAM000);
        }
        if (StringUtils.isEmpty(publish.getProType())) {
            return errors(RealBox.PROGRAM008);
        }
        if (StringUtils.isEmpty(publish.getPublisher())) {
            return errors(RealBox.PUBLISH001);
        }
        if (StringUtils.isEmpty(publish.getDisType())) {
            return errors(RealBox.PUBLISH002);
        }
        if (StringUtils.isEmpty(publish.getDisType())) {
            return errors(RealBox.PUBLISH003);
        }
        if (StringUtils.isEmpty(publish.getInvalidTime())) {
            return errors(RealBox.PUBLISH004);
        }

        // 验证终端
        Terminal terminal = terminalMapper.queryById(publish.getTerminalId(), Terminal.class);
        if (terminal == null) {
            return errors(RealBox.TERMINAL003);
        }

        // 验证节目
        Program program = programMapper.queryById(publish.getProId(), Program.class);
        if (program == null) {
            return errors(RealBox.PROGRAM007);
        }

        // 设置发布任务管理参数
        setPublish(program, publish);

        // 新增发布任务管理
        publishMapper.create(publish);

        // 记录日志
        logUtils.userLog("新增发布任务管理", request);

        // 返回
        return success();
    }

    /**
     * 编辑发布任务管理
     *
     * @param publish 发布任务管理对象
     * @return
     */
    @Override
    public RespEntity<String> updatePublish(PublishManage publish) {
        return null;
    }

    /**
     * 删除发布任务管理
     *
     * @param ids ID(List集合)
     * @return
     */
    @Override
    public RespEntity<String> deletePublish(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.PUBLISH000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

//        List<PublishManage> publish = publishMapper.batchQueryById(arrayId, PublishManage.class);
//        if (publish.isEmpty()) {
//            return errors(RealBox.PUBLISH008);
//        }
//
//        // 通知终端删除节目
//        Map<String, List<String>> programs = new HashMap<String, List<String>>();
//        for (PublishManage pub : publish) {
//            // 验证节目是否在发布状态
//            if ("已发布".equals(pub.getStatus())) {
//                // 验证终端
//                Terminal terminal = terminalMapper.queryById(pub.getTerminalId(), Terminal.class);
//                if (terminal == null) {
//                    return errors(RealBox.TERMINAL003);
//                }
//                List<String> list = programs.get(terminal.getTerminalCode());
//                if (list == null) {
//                    list = new ArrayList<String>();
//                }
//                // 添加发布任务管理ID
//                list.add(pub.getId());
//                // 存储
//                programs.put(terminal.getTerminalCode(), list);
//            }
//        }
//
//        // 设置节目删除参数
//        for (Map.Entry<String, List<String>> map : programs.entrySet()) {
//            // AES加密
//            String json = terminalUtils.getPublishData(map);
//
//            // 发布
//            rabbitService.sendSingle(map.getKey(), json);
//
//            // 记录日志
//            logUtils.systemLog("删除节目");
//        }

        // 删除发布任务管理
        publishMapper.batchDelete(arrayId, PublishManage.class);

        // 记录日志
        logUtils.userLog("删除发布任务管理", request);

        // 返回
        return success();
    }

    /**
     * 审核节目
     *
     * @param id   ID(发布单号)
     * @param type 审核状态
     * @return
     */
    @Override
    public RespEntity<String> auditProgram(String id, String type) {
        if (StringUtils.isEmpty(id)) {
            return errors(RealBox.PUBLISH000);
        }
        if (StringUtils.isEmpty(type)) {
            return errors(RealBox.PUBLISH009);
        }

        // 验证发布任务管理
        PublishManage publish = publishMapper.queryById(id, PublishManage.class);
        if (publish == null) {
            return errors(RealBox.PUBLISH008);
        }

        // 审核通过
        if ("pass".equals(type)) {
            // 验证终端
            Terminal terminal = terminalMapper.queryById(publish.getTerminalId(), Terminal.class);
            if (terminal == null) {
                return errors(RealBox.TERMINAL003);
            }
            // 终端是否激活
            if (!terminal.isActStatus()) {
                return errors(RealBox.TERMINAL004);
            }
            // 验证节目
            Program program = programMapper.queryById(publish.getProId(), Program.class);
            if (program == null) {
                return errors(RealBox.PROGRAM007);
            }
            // AES加密
            String json = publishUtils.getPublishData(publish, program);
            try {
                json = AESUtils.encrypt(comKey, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 发布节目
            rabbitService.sendSingle(terminal.getTerminalCode(), json);
            // 记录日志
            logUtils.systemLog("发布节目");
            // 编辑发布任务管理状态
            Map<String, Object> cond = Maps.create().add("status", "已发布").get();
            publishMapper.update(id, cond, PublishManage.class);
            // 记录日志
            logUtils.userLog("发布节目", request);
            // 记录日志
            logUtils.userLog("编辑发布任务管理状态", request);
        }

        // 审核不通过
        if ("refuse".equals(type)) {
            // 修改发布节目状态
            Map<String, Object> cond = Maps.create().add("status", "审核未通过").get();
            publishMapper.update(id, cond, PublishManage.class);
        }

        // 返回
        return success();
    }

    /**
     * 设置发布任务管理参数
     *
     * @param program 节目对象
     * @param publish 发布任务管理对象
     */
    private void setPublish(Program program, PublishManage publish) {
        publish.setProName(program.getName());          // 节目名称
        publish.setProStatus(program.getStatus());      // 节目状态
        publish.setStatus("未审核");                       // 发布状态
    }

}
