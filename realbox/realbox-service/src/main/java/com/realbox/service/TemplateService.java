/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.Template;
import com.realbox.model.entity.TemplateEntity;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TemplateService.java v 0.1 2017年12月08日 上午11:05 MJJ Exp $
 **/
public interface TemplateService {

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
    RespEntity<Map<String, Object>> queryTemplate(String groupId, String id, String name, String resolution,
                                                  String terminalType, Integer pageNo, Integer pageCount);

    /**
     * 新增模板
     *
     * @param entity 模板对象
     * @return
     */
    RespEntity<Map<String, Object>> createTemplate(TemplateEntity entity);

    /**
     * 编辑模板
     *
     * @param entity 模板对象
     * @return
     */
    RespEntity<String> updateTemplate(TemplateEntity entity);

    /**
     * 删除模板
     *
     * @param ids ID(List集合)
     * @return
     */
    RespEntity<String> deleteTemplate(String ids);
}
