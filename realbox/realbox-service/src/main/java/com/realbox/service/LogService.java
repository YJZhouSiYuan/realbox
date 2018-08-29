/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: LogService.java v 0.1 2018年01月26日 18:16 MJJ Exp $
 **/
public interface LogService {
    /**
     * 查询系统日志
     *
     * @param operator  操作人
     * @param pageNo    当前页面
     * @param pageCount 页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> querySystemLog(String operator, Integer pageNo, Integer pageCount);

    /**
     * 查询用户日志
     *
     * @param operator  操作人
     * @param pageNo    当前页面
     * @param pageCount 页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> queryUserLog(String operator, Integer pageNo, Integer pageCount);
}
