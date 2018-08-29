/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TerminalService.java v 0.1 2017年12月12日 10:49 MJJ Exp $
 **/
public interface TerminalService {

    /**
     * 查询终端
     *
     * @param groupId   终端树ID
     * @param name      终端名称
     * @param code      硬件号
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> queryTerminal(String groupId, String name, String code, Integer pageNo, Integer pageCount);

    /**
     * 删除终端
     *
     * @param ids 终端ID
     * @return
     */
    RespEntity<String> deleteTerminal(String ids);

    /**
     * 激活终端
     *
     * @param code 激活码
     * @return
     */
    RespEntity<Map<String, Object>> activate(String code);

    /**
     * 服务器强制终端同步
     *
     * @param ids 终端ID
     * @return
     */
    RespEntity<String> aynTerminal(String ids);
}
