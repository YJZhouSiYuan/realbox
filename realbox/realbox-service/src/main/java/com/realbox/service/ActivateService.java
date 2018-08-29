/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: ActivateService.java v 0.1 2017年12月12日 11:49 MJJ Exp $
 **/
public interface ActivateService {

    /**
     * 查询激活码
     *
     * @param id        ID
     * @param pageNo    当前页面数量
     * @param pageCount
     * @return
     */
    RespEntity<Map<String, Object>> queryActivation(String id, Integer pageNo, Integer pageCount);

    /**
     * 新增激活码
     *
     * @param terId   终端树ID
     * @param creator 创建人
     * @param number  生成数量
     * @return
     */
    RespEntity<String> createActivation(String terId, String creator, Integer number);

    /**
     * 删除激活码
     *
     * @param ids     ID
     * @param request 请求
     * @return
     */
    RespEntity<String> deleteActivation(String ids, HttpServletRequest request);

    /**
     * 激活码解绑
     *
     * @param ids 激活码ID(批量解绑)
     * @return
     */
    RespEntity<String> unbundledActivate(String ids);

    /**
     * 激活码导出
     *
     * @param ids     激活码ID(一个或多个)
     * @param request 请求
     * @return
     */
    RespEntity<Map<String, Object>> exportActivate(String ids, HttpServletRequest request);
}
