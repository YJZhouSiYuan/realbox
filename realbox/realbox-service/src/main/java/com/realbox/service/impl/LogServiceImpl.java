/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.SystemLog;
import com.realbox.model.UserLog;
import com.realbox.model.entity.RespEntity;
import com.realbox.repository.mongodb.mapper.SystemLogMapper;
import com.realbox.repository.mongodb.mapper.UserLogMapper;
import com.realbox.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: LogServiceImpl.java v 0.1 2018年01月26日 18:17 MJJ Exp $
 **/
@Service("logService")
public class LogServiceImpl extends BaseService implements LogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Autowired
    private UserLogMapper userLogMapper;

    /**
     * 查询系统日志
     *
     * @param operator  操作人
     * @param pageNo    当前页面
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> querySystemLog(String operator, Integer pageNo, Integer pageCount) {
        // 查询数量
        Map<String, Object> cond = Maps.create().add("operator", operator).get();
        long count = systemLogMapper.count(cond, SystemLog.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询数据
        List<SystemLog> logs = systemLogMapper.queryList(cond, pages, SystemLog.class);

        // 返回
        Map<String, Object> map = Maps.create()
                .add("logs", logs)
                .add("pages", pages)
                .get();
        return success(map);
    }

    /**
     * 查询用户日志
     *
     * @param operator  操作人
     * @param pageNo    当前页面
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryUserLog(String operator, Integer pageNo, Integer pageCount) {
        // 查询数量
        Map<String, Object> cond = Maps.create().add("operator", operator).get();
        long count = userLogMapper.count(cond, UserLog.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询数据
        List<UserLog> logs = userLogMapper.queryList(cond, pages, UserLog.class);

        // 返回
        Map<String, Object> map = Maps.create()
                .add("logs", logs)
                .add("pages", pages)
                .get();
        return success(map);
    }
}
