/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MJJ
 * @create Id: SystemService.java v 0.1 2017年12月03日 下午2:55 MJJ Exp $
 **/
public interface SystemService {

    /**
     * 检测Token
     *
     * @param request 请求
     * @return
     */
    RespEntity<String> check(HttpServletRequest request);

    /**
     * 初始化系统
     *
     * @return
     */
    RespEntity<String> init();

    /**
     * 设置语言
     *
     * @param language 语言
     * @return
     */
    RespEntity<String> language(String language);
}
