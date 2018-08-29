/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.User;
import com.realbox.model.entity.RespEntity;
import com.realbox.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: UserController.java v 0.1 2017年11月03日 23:41 MJJ Exp $
 **/
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param user    用户对象
     * @param request 请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public RespEntity<Map<String, Object>> login(@RequestBody User user, HttpServletRequest request) {
        return loginService.login(user, request);
    }

    /**
     * 生成验证码
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public void code(HttpServletRequest request, HttpServletResponse response) {
        loginService.code(request, response);
    }
}
