/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author MJJ
 * @create Id: TokenUtils.java v 0.1 2017年12月08日 22:10 MJJ Exp $
 **/
public class TokenUtils {

    /**
     * 生成Token
     *
     * @return
     */
    public static String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成激活码
     *
     * @return
     */
    public static Map<String, String> generate() {
        // 硬件号
        String name = UUID.randomUUID().toString();
        // 激活码
        String code = UUID.randomUUID().toString();
        Map<String, String> map = new HashMap<String, String>();
        map.put(name, code);
        return map;
    }
}
