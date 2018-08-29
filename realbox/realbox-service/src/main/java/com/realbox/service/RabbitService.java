/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;

/**
 * @author MJJ
 * @create Id: RabbitService.java v 0.1 2017年12月29日 19:29 MJJ Exp $
 **/
public interface RabbitService {

    /**
     * 广播
     *
     * @param json 数据
     * @return
     */
    RespEntity<String> sendBroad(String json);

    /**
     * 单发
     *
     * @param routingKey routingKey
     * @param json       数据
     * @return
     */
    RespEntity<String> sendSingle(String routingKey, String json);

    /**
     * 接收信息
     *
     * @param bytes 比特数组
     */
    void receiveData(byte[] bytes);

}
