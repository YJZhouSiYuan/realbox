/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: LoginService.java v 0.1 2017年11月04日 上午3:56 MJJ Exp $
 **/
public interface LoginService {
    /**
     * 登录
     *
     * @param user    用户对象
     * @param request 请求
     * @return
     */
    RespEntity<Map<String, Object>> login(User user, HttpServletRequest request);

    /**
     * 生成验证码
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    void code(HttpServletRequest request, HttpServletResponse response);
}
