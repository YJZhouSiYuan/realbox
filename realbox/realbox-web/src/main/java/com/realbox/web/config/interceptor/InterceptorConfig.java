/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author MJJ
 * @create Id: InterceptorConfig.java v 0.1 2017年11月05日 上午12:48 MJJ Exp $
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 注入拦截器功能逻辑实现类
     *
     * @return
     */
    @Bean
    InterceptorService interceptorService() {
        return new InterceptorService();
    }

    /**
     * 定义拦截接口
     *
     * @param registry 拦截注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 添加拦截的接口
        registry.addInterceptor(interceptorService())
                // 系统初始化
                .excludePathPatterns("/terminal/activate")
                .excludePathPatterns("/activate/export")
                // 激活码
                .addPathPatterns("/activate/**")
                // 日志
                .addPathPatterns("/log/**")
                // 权限
                .addPathPatterns("/privilege/**")
                // 节目
                .addPathPatterns("/program/**")
                // 发布
                .addPathPatterns("/publish/**")
                // 资源
                .addPathPatterns("/resource/**")
                // 角色
                .addPathPatterns("/role/**")
                // 模板
                .addPathPatterns("/template/**")
                // 终端
                .addPathPatterns("/terminal/**")
                // 树
                .addPathPatterns("/tree/**")
                // 用户
                .addPathPatterns("/user/**");
    }
}
