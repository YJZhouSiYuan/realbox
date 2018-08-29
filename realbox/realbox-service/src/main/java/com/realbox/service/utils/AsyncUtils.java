/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.TokenUtils;
import com.realbox.model.Activate;
import com.realbox.repository.mongodb.mapper.ActivateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: ActivateAsync.java v 0.1 2017年12月18日 10:50 MJJ Exp $
 **/
@Component
public class AsyncUtils {

    @Autowired
    private ActivateMapper activateMapper;

    /**
     * 新增激活码
     *
     * @param terId   终端树ID
     * @param creator 创建人
     * @param number  生成数量
     */
    @Async
    public void createActivation(String terId, String creator, Integer number) {
        List<Activate> activates = new ArrayList<Activate>();
        for (int i = 0; i < number; i++) {
            // 生成激活码、硬件号
            Map<String, String> maps = TokenUtils.generate();

            // 设置实体类
            Activate activate = new Activate();
            setActivate(terId, creator, maps, activate);

            // 存储数据
            activates.add(activate);
        }

        // 批量存储
        activateMapper.batchCreate(activates, Activate.class);
    }

    /**
     * 设置实体类
     *
     * @param terId    终端树ID
     * @param creator  创建人
     * @param maps     激活码
     * @param activate 激活码对象
     * @return
     */
    private Activate setActivate(String terId, String creator, Map<String, String> maps, Activate activate) {
        for (Map.Entry<String, String> map : maps.entrySet()) {
            // 终端树ID
            activate.setTerId(terId);
            // 硬件号
            activate.setName(map.getKey());
            // 激活码
            activate.setCode(map.getValue());
            // 创建人
            activate.setCreator(creator);
            // 创建时间
            activate.setTime(DateUtils.getDateTime());
            // 是否可用
        }            activate.setUsed("1");

        // 返回实体
        return activate;
    }
}
