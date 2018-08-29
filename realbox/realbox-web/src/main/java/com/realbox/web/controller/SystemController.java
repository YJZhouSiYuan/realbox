/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author MJJ
 * @create Id: SystemController.java v 0.1 2017年12月03日 下午2:54 MJJ Exp $
 **/
@RestController
@RequestMapping("system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 检测Token
     *
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    public RespEntity<String> check(HttpServletRequest request) {
        return systemService.check(request);
    }

    /**
     * 初始化权限功能
     *
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.GET)
    public RespEntity<String> init() {
        return systemService.init();
    }

    /**
     * 设置语言
     *
     * @param language 语言
     * @return
     */
    @RequestMapping(value = "language", method = RequestMethod.GET)
    public RespEntity<String> language(@RequestParam String language) {
        return systemService.language(language);
    }
}
