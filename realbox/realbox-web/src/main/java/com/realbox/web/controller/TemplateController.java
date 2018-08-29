/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.entity.TemplateEntity;
import com.realbox.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TemplateController.java v 0.1 2017年12月08日 上午10:55 MJJ Exp $
 **/
@RestController
@RequestMapping("template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 查询模板
     *
     * @param groupId      组ID(模板结构树ID)
     * @param id           模板ID
     * @param name         模板名
     * @param resolution   分辨率
     * @param terminalType 终端类型
     * @param pageNo       当前页数
     * @param pageCount    页面显示数量
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryTemplate(@RequestParam(required = false) String groupId,
                                                         @RequestParam(required = false) String id,
                                                         @RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String resolution,
                                                         @RequestParam(required = false) String terminalType,
                                                         @RequestParam Integer pageNo,
                                                         @RequestParam Integer pageCount) {
        return templateService.queryTemplate(groupId, id, name, resolution, terminalType, pageNo, pageCount);
    }

    /**
     * 新增模板
     *
     * @param entity 模板对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<Map<String, Object>> createTemplate(@RequestBody TemplateEntity entity) {
        return templateService.createTemplate(entity);
    }

    /**
     * 编辑模板
     *
     * @param entity 模板对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updateTemplate(@RequestBody TemplateEntity entity) {
        return templateService.updateTemplate(entity);
    }

    /**
     * 删除模板
     *
     * @param ids ID(List集合)
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteTemplate(@RequestParam String ids) {
        return templateService.deleteTemplate(ids);
    }
}
