/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.Inter;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: InterMapper.java v 0.1 2018年03月08日 17:53 MJJ Exp $
 **/
@Repository
public class InterMapper extends MongoDao<Inter> {

    /**
     * 修改
     *
     * @param id    ID
     * @param inter 国际化树对象
     */
    public void update(String id, Inter inter) {
        // 修改
        super.update(id, getUpdate(inter), Inter.class);
    }

    /**
     * 获取修改参数
     *
     * @param inter 国际化树对象
     * @return
     */
    private Map<String, Object> getUpdate(Inter inter) {
        return BeanUtils.beanToMap(inter);
    }
}
