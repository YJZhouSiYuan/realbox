/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.config.interceptor;

import com.realbox.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MJJ
 * @create Id: InterceptorService.java v 0.1 2017年11月05日 上午12:51 MJJ Exp $
 **/
public class InterceptorService extends HandlerInterceptorAdapter {

    @Autowired
    private CheckService checkService;

    /**
     * 调用接口之前加载
     *
     * @param request  请求
     * @param response 响应
     * @param handler  管理
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证Token
        boolean flag = checkService.token(request, response);
        if (flag) {
            // 验证权限
            flag = checkService.privilege(request, response);
        }
        // 返回
        return flag;
    }
}
