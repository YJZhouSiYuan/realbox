/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.alibaba.fastjson.JSON;
import com.realbox.common.utils.AESUtils;
import com.realbox.model.bean.terminal.AdvAdvtask;
import com.realbox.model.bean.terminal.AdvBean;
import com.realbox.model.bean.terminal.AdvInformationBean;
import com.realbox.model.entity.RespEntity;
import com.realbox.service.RabbitService;
import com.realbox.service.utils.TaskUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author MJJ
 * @create Id: RabbitServiceImpl.java v 0.1 2017年12月29日 19:34 MJJ Exp $
 **/
@Service("rabbitService")
public class RabbitServiceImpl extends BaseService implements RabbitService {
    // 广播交换机
    @Value("${advBroadExchange}")
    String advBroadExchange;

    // 广播routingKey
    @Value("${advBroadRKey}")
    String advBroadRKey;

    // 单发交换机
    @Value("${advSingleExchange}")
    String advSingleExchange;

    @Value("${comKey}")
    String comKey;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private TaskUtils taskUtils;

    /**
     * 广播
     *
     * @param json 数据
     * @return
     */
    @Override
    public RespEntity<String> sendBroad(String json) {
        rabbitTemplate.convertAndSend(advBroadExchange, advBroadRKey, json.getBytes());
        return success();
    }

    /**
     * 单发
     *
     * @param routingKey routingKey
     * @param json       数据
     * @return
     */
    @Override
    public RespEntity<String> sendSingle(String routingKey, String json) {
        rabbitTemplate.convertAndSend(advSingleExchange, routingKey, json.getBytes());
        return success();
    }

    /**
     * 监听RabbitMQ任务
     *
     * @param bytes 比特数组
     */
    @RabbitListener(queues = "backQueue")
    @Override
    public void receiveData(byte[] bytes) {
        try {
            // 获取数据
            String json = new String(bytes, "UTF-8");
            // 解密
            json = AESUtils.decrypt(comKey, json);
            // 获取任务
            String taskName = getTask(json);
            if (!StringUtils.isEmpty(taskName)) {
                taskUtils.taskManage(taskName, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取监听任务
     *
     * @param json 终端请求数据
     * @return
     */
    private String getTask(String json) {
        AdvBean bean = JSON.parseObject(json, AdvBean.class);
        AdvInformationBean information = bean.getInformation();
        AdvAdvtask task = information.getAdvTask();
        return task.getProperty().getTaskName();
    }
}
