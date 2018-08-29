/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MJJ
 * @create Id: CheckServiceImpl.java v 0.1 2018年02月06日 18:57 MJJ Exp $
 **/
@Service("checkService")
public class CheckServiceImpl implements CheckService {
    private static final String INFO1 = "请求头name参数不能为空";
    private static final String INFO2 = "请求头token参数不能为空";
    private static final String INFO3 = "用户token令牌失效";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 验证Token
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    @Override
    public boolean token(HttpServletRequest request, HttpServletResponse response) {
        // 获取用户名
        String name = request.getHeader("name");
        if (StringUtils.isEmpty(name)) {
            setInfo(INFO1, response);
            return false;
        }

        // 验证获取Token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(token)) {
            setInfo(INFO2, response);
            return false;
        }

        // 验证Redis中的Token
        String result = redisTemplate.opsForValue().get(name);
        if (StringUtils.isEmpty(result)) {
            setInfo(INFO3, response);
            return false;
        }
        if (!token.equals(result)) {
            setInfo(INFO3, response);
            return false;
        }

        // 返回
        return true;
    }

    /**
     * 验证权限
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    @Override
    public boolean privilege(HttpServletRequest request, HttpServletResponse response) {
        // 查询用户

        // 查询角色
        // 查询权限
        System.out.println(request.getRequestURL());
        return true;
    }

    /**
     * 反馈信息
     *
     * @param info     信息
     * @param response 响应
     * @throws Exception
     */
    private void setInfo(String info, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(info);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.reset();
        response.setContentType("text/html;charset=UTF-8");
    }
}
