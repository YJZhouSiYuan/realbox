/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.realbox.common.utils.DateUtils;
import com.realbox.model.SystemLog;
import com.realbox.model.UserLog;
import com.realbox.repository.mongodb.mapper.SystemLogMapper;
import com.realbox.repository.mongodb.mapper.UserLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MJJ
 * @create Id: LogUtils.java v 0.1 2018年01月26日 11:17 MJJ Exp $
 **/
@Component
public class LogUtils {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Autowired
    private UserLogMapper userLogMapper;

    /**
     * 添加系统日志
     *
     * @param desc     日志内容
     */
    public void systemLog(String desc) {
        SystemLog log = new SystemLog();
        log.setTime(DateUtils.getDateTime());
        log.setOperator("系统");
        log.setDesc(desc);

        systemLogMapper.create(log);
    }

    /**
     * 添加用户日志
     *
     * @param desc    日志内容
     * @param request 请求
     */
    public void userLog(String desc, HttpServletRequest request) {
        UserLog log = new UserLog();
        log.setTime(DateUtils.getDateTime());
        String operator = request.getHeader("name");
        log.setOperator(operator);
        log.setDesc(desc);

        userLogMapper.create(log);
    }
}
