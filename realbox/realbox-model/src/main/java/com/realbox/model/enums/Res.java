/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.enums;

/**
 * @author MJJ
 * @create Id: Res.java v 0.1 2017年12月27日 18:12 MJJ Exp $
 **/
public enum Res {
    // 滚动字幕
    SCROLL,
    // 文本
    TEXT,
    // 音乐
    AUDIO,
    // 视频
    VIDEO,
    // 图片
    IMAGE;

    public static Res getRes(String type) {
        if ("image".equals(type)) {
            return Res.IMAGE;
        }
        if ("video".equals(type)) {
            return Res.VIDEO;
        }
        if ("audio".equals(type)) {
            return Res.AUDIO;
        }
        if ("text".equals(type)) {
            return Res.TEXT;
        }
        if ("scroll".equals(type)) {
            return Res.SCROLL;
        }
        return null;
    }
}
