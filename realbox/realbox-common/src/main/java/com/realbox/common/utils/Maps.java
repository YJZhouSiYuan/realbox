/**
 * 代码归 MJJ 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.common.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MJJ
 * @version Id: Maps.java, v 0.1 2017年10月19日 上午13:24:25 MJJ Exp $
 */
public class Maps {

    private Map<String, Object> params;

    private Maps() {
        this.params = new HashMap();
    }

    public Maps add(String key, Object value) {
        if (value == null) {
            this.params.remove(key);
        } else {
            if (!StringUtils.isEmpty(key)) {
                this.params.put(key, value);
            }
        }
        return this;
    }

    public Maps add(Map<String, Object> values) {
        if (values != null) {
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                add(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    public Maps remove(String key) {
        if (!StringUtils.isEmpty(key)) {
            this.params.remove(key);
        }
        return this;
    }

    public Map<String, Object> get() {
        return this.params;
    }

    public static Maps create() {
        Maps builder = new Maps();
        return builder;
    }

    public static Maps create(String key, Object value) {
        Maps builder = new Maps();
        builder.add(key, value);
        return builder;
    }

    public static Maps create(Map<String, Object> values) {
        Maps builder = new Maps();
        builder.add(values);
        return builder;
    }
}
