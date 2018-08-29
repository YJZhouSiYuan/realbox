/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: BaseService.java v 0.1 2017年11月27日 下午11:03 MJJ Exp $
 **/
public class BaseService {

    /**
     * 错误(String)
     *
     * @param realBox
     * @return
     */
    public RespEntity<String> errors(RealBox realBox) {
        return new RespEntity<String>(realBox.getKey() , realBox.getValue());
    }

    /**
     * 错误(Map)
     *
     * @param realBox
     * @return
     */
    public RespEntity<Map<String, Object>> error(RealBox realBox) {
        return new RespEntity<Map<String, Object>>(realBox.getKey() , realBox.getValue());
    }

    /**
     * 成功(Map)
     *
     * @param map Map键值对
     * @return
     */
    public RespEntity<Map<String, Object>> success(Map<String, Object> map) {
        return new RespEntity<Map<String, Object>>("0000", "成功", map);
    }

    /**
     * 成功(String)
     *
     * @return
     */
    public RespEntity<String> success() {
        return new RespEntity<String>("0000", "成功");
    }
}
