/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.Template;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TemplateMapper.java v 0.1 2017年12月21日 22:29 MJJ Exp $
 **/
@Repository
public class TemplateMapper extends MongoDao<Template> {

    /**
     * 修改(ID)
     *
     * @param id       ID
     * @param template 模板对象
     */
    public void update(String id, Template template) {
        // 修改
        super.update(id, getUpdate(template), Template.class);
    }

    /**
     * 获取修改参数
     *
     * @param template 模板对象
     * @return
     */
    private Map<String, Object> getUpdate(Template template) {
        // 实体转Map
        return BeanUtils.beanToMap(template);
    }
}
