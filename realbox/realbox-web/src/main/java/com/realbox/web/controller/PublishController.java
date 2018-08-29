/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.PublishManage;
import com.realbox.model.entity.RespEntity;
import com.realbox.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: PublishController.java v 0.1 2017年12月31日 20:19 MJJ Exp $
 **/
@RestController
@RequestMapping("publish")
public class PublishController {

    @Autowired
    private PublishService publishService;

    /**
     * 查询节目管理
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
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryPublish(@RequestParam(required = false) String proName,
                                                        @RequestParam(required = false) String proStatus,
                                                        @RequestParam(required = false) String status,
                                                        @RequestParam(required = false) String startTime,
                                                        @RequestParam(required = false) String endTime,
                                                        @RequestParam Integer pageNo,
                                                        @RequestParam Integer pageCount) {
        return publishService.queryPublish(proName, proStatus, status, startTime, endTime, pageNo, pageCount);
    }

    /**
     * 新增节目管理
     *
     * @param publish 节目管理对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<String> createPublish(@RequestBody PublishManage publish) {
        return publishService.createPublish(publish);
    }

    /**
     * 编辑节目管理
     *
     * @param publish 节目管理对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updatePublish(@RequestBody PublishManage publish) {
        return publishService.updatePublish(publish);
    }

    /**
     * 删除节日管理
     *
     * @param ids ID(List集合)
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deletePublish(@RequestParam String ids) {
        return publishService.deletePublish(ids);
    }

    /**
     * 审核节目
     *
     * @param id   ID(发布单号)
     * @param type 审核状态
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.GET)
    public RespEntity<String> auditProgram(@RequestParam String id, @RequestParam String type) {
        return publishService.auditProgram(id, type);
    }
}
