/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author MJJ
 * @version Id: AppStart.java, v 0.1 2017年11月3日 上午23:43:34 MJJ Exp $
 */
@EnableAsync
@SpringBootApplication
@ComponentScan("com.realbox")
@EnableMongoRepositories("com.realbox.repository")
public class AppStart extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}





