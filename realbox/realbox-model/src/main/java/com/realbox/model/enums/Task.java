/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.enums;

/**
 * @author MJJ
 * @create Id: Task.java v 0.1 2017年12月30日 19:18 MJJ Exp $
 **/
public enum Task {
    // 查询节目信息
    INFO,
    // 心跳
    HEART,
    // 终端同步
    SYNC,
    // 登录
    LOGIN;

    /**
     * 验证任务类型
     *
     * @param task 任务名
     * @return
     */
    public static Task getTask(String task) {
        if ("login".equals(task))
            return Task.LOGIN;
        if ("programmelist".equals(task))
            return Task.SYNC;
        if ("heart".equals(task))
            return Task.HEART;
        if ("programmeinfo".equals(task))
            return Task.INFO;
        return null;
    }
}
