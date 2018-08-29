/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.model.SystemLog;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: SystemLogMapper.java v 0.1 2018年01月26日 11:17 MJJ Exp $
 **/
@Repository
public class SystemLogMapper extends MongoDao<SystemLog> {

    @Autowired
    private MongoOperations operations;

    public List<SystemLog> queryLikeList(Map<String, Object> cond, SystemLog log) {
        return null;
    }

}
