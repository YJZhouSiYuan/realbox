/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MJJ
 * @create Id: CheckService.java v 0.1 2018年02月06日 18:50 MJJ Exp $
 **/
public interface CheckService {

    /**
     * 验证Token
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    boolean token(HttpServletRequest request, HttpServletResponse response);

    /**
     * 验证权限
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    boolean privilege(HttpServletRequest request, HttpServletResponse response);
}
