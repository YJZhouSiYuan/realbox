/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.entity.ProgramEntity;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: ProgramService.java v 0.1 2017年12月20日 20:59 MJJ Exp $
 **/
public interface ProgramService {

    /**
     * 查询节目
     *
     * @param groupId      组ID(节目列表ID)
     * @param name         节目名称
     * @param resolution   分辨率
     * @param terminalType 终端状态
     * @param pageNo       当前页数
     * @param pageCount    页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> queryProgram(String id,
                                                 String groupId,
                                                 String name,
                                                 String resolution,
                                                 String terminalType,
                                                 Integer pageNo,
                                                 Integer pageCount);

    /**
     * 新增节目
     *
     * @param entity 节目对象
     * @return
     */
    RespEntity<String> createProgram(ProgramEntity entity);

    /**
     * 编辑节目
     *
     * @param entity 节目对象
     * @return
     */
    RespEntity<String> updateProgram(ProgramEntity entity);

    /**
     * 删除节目
     *
     * @param ids ID(List集合)
     * @return
     */
    RespEntity<String> deleteProgram(String ids);
}
