/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: LogController.java v 0.1 2018年01月26日 17:33 MJJ Exp $
 **/
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 查询系统日志
     *
     * @param operator  操作人
     * @param pageNo    当前页面
     * @param pageCount 页面显示数量
     * @return
     */
    @RequestMapping(value = "query/system", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> querySystemLog(@RequestParam(required = false) String operator,
                                                          @RequestParam(required = false) Integer pageNo,
                                                          @RequestParam(required = false) Integer pageCount) {
        return logService.querySystemLog(operator, pageNo, pageCount);
    }

    /**
     * 查询用户日志
     *
     * @param operator  操作人
     * @param pageNo    当前页面
     * @param pageCount 页面显示数量
     * @return
     */
    @RequestMapping(value = "query/user", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryUserLog(@RequestParam(required = false) String operator,
                                                        @RequestParam(required = false) Integer pageNo,
                                                        @RequestParam(required = false) Integer pageCount) {
        return logService.queryUserLog(operator, pageNo, pageCount);
    }
}
