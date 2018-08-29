/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.realbox.model.ProItems;
import com.realbox.model.Program;
import com.realbox.model.Template;
import com.realbox.model.entity.ProgramEntity;
import com.realbox.repository.mongodb.mapper.ProItemsMapper;
import com.realbox.repository.mongodb.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MJJ
 * @create Id: ProgramUtils.java v 0.1 2017年12月28日 23:25 MJJ Exp $
 **/
@Component
public class ProgramUtils {

    @Autowired
    private ProItemsMapper proItemsMapper;

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 获取节目
     *
     * @param programs 节目对象
     * @return
     */
    public List<ProgramEntity> programs(List<Program> programs) {
        List<ProgramEntity> entities = new ArrayList<ProgramEntity>();

        // 循环遍历节目
        program(programs, entities);

        // 返回
        return entities;
    }

    /**
     * 循环遍历节目
     *
     * @param programs 节目对象
     * @param entities 自定义节目对象
     */
    private void program(List<Program> programs, List<ProgramEntity> entities) {
        for (Program program : programs) {
            // 查询元素
            List<ProItems> proItems = proItemsMapper.batchQueryById(program.getElemIds(), ProItems.class);

            // 查询模板
            Template template = templateMapper.queryById(program.getModelId(), Template.class);

            // 设置节目参数
            setProgram(template, program, proItems, entities);
        }
    }

    /**
     * 设置节目参数
     *
     * @param template 模板对象
     * @param program  节目对象
     * @param proItems 节目元素对象
     * @param entities 自定义节目对象
     */
    private void setProgram(Template template, Program program, List<ProItems> proItems, List<ProgramEntity> entities) {
        ProgramEntity entity = new ProgramEntity();
        entity.setId(program.getId());                      // ID
        entity.setGroupId(program.getGroupId());            // 组ID(节目列表树ID)
        entity.setPrivId(program.getPrivId());              // 资源权限ID
        entity.setModelId(program.getModelId());            // 模板ID
        entity.setName(program.getName());                  // 节目名
        entity.setPreview(program.getPreview());            // 预览图
        entity.setResolution(template.getResolution());     // 分辨率
        entity.setTerminalType(template.getTerminalType()); // 终端类型
        entity.setStatus(program.getStatus());              // 状态
        entity.setUpdateTime(program.getUpdateTime());      // 更新时间
        entity.setProItems(proItems);                       // 节目元素
        entity.setBody(template.getBody());                 // 代码内容

        // 存储
        entities.add(entity);
    }
}
