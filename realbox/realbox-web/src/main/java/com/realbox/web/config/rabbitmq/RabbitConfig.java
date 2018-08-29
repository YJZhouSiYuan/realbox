/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author MJJ
 * @create Id: RabbitConfig.java v 0.1 2017年12月23日 22:21 MJJ Exp $
 **/
@Configuration
public class RabbitConfig {

    // 终端队表
    @Value("${backQueue}")
    String backQueue;

    // 终端routingKey
    @Value("${advBackRKey}")
    String advBackRKey;

    // 终端交换机
    @Value("${advExchange}")
    String advExchange;

    // 广播
    @Value("${advBroadExchange}")
    String advBroadExchange;

    // 单发
    @Value("${advSingleExchange}")
    String advSingleExchange;

    /**
     * 队列(终端)
     */
    @Bean
    public Queue advQueue() {
        return new Queue(backQueue);
    }

    /**
     * 交换机(终端)
     *
     * @return
     */
    @Bean
    public DirectExchange advExchange() {
        return new DirectExchange(advExchange, false, false);
    }

    /**
     * 绑定(终端)
     *
     * @return
     */
    @Bean
    public Binding bindingAdvExchange() {
        return BindingBuilder.bind(advQueue()).to(advExchange()).with(advBackRKey);
    }

    /**
     * 交换机(广播)
     *
     * @return
     */
    @Bean
    public DirectExchange advBroadExchange() {
        return new DirectExchange(advBroadExchange, false, false);
    }

    /**
     * 交换机(单发)
     *
     * @return
     */
    @Bean
    public DirectExchange advSingleExchange() {
        return new DirectExchange(advSingleExchange, false, false);
    }
}
