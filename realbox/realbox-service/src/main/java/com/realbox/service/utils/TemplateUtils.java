/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.realbox.model.TemItems;
import com.realbox.model.Template;
import com.realbox.model.entity.TemplateEntity;
import com.realbox.repository.mongodb.mapper.TemItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MJJ
 * @create Id: TemplateUtils.java v 0.1 2017年12月28日 16:36 MJJ Exp $
 **/
@Component
public class TemplateUtils {

    @Autowired
    private TemItemsMapper temItemsMapper;

    /**
     * 获取模板
     *
     * @param templates 模板对象
     * @return
     */
    public List<TemplateEntity> templates(List<Template> templates) {
        List<TemplateEntity> entities = new ArrayList<TemplateEntity>();

        // 循环遍历模板
        template(templates, entities);

        // 返回
        return entities;
    }

    /**
     * 循环遍历模板
     *
     * @param templates 模板对象
     * @param entities  自定义模板对象
     */
    private void template(List<Template> templates, List<TemplateEntity> entities) {
        for (Template template : templates) {
            // 查询元素
            List<TemItems> temItems = temItemsMapper.batchQueryById(template.getElemIds(), TemItems.class);

            // 设置模板参数
            setTemplate(template, temItems, entities);
        }
    }

    /**
     * 设置模板参数
     *
     * @param template    模板对象
     * @param temItems 模板元素对象
     * @param entities    自定义模板对象
     */
    private void setTemplate(Template template, List<TemItems> temItems, List<TemplateEntity> entities) {
        TemplateEntity entity = new TemplateEntity();
        entity.setId(template.getId());                     // ID
        entity.setGroupId(template.getGroupId());           // 组ID(模板树ID)
        entity.setPrivId(template.getPrivId());             // 资源权限ID
        entity.setName(template.getName());                 // 模板名
        entity.setPreview(template.getPreview());           // 预览图
        entity.setCreator(template.getCreator());           // 创建人
        entity.setType(template.getType());                 // 模板类型
        entity.setTerminalType(template.getTerminalType()); // 终端类型
        entity.setUpdateTime(template.getUpdateTime());     // 更新时间
        entity.setResolution(template.getResolution());     // 分辨率
        entity.setHeight(template.getHeight());             // 高
        entity.setWidth(template.getWidth());               // 宽
        entity.setTemItems(temItems);                 // 元素
        entity.setBody(template.getBody());                 // 代码内容
        entity.setDesc(template.getDesc());                 // 描述

        // 存储
        entities.add(entity);
    }
}
