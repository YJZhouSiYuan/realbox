/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.PublishManage;
import com.realbox.model.entity.RespEntity;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: PublishService.java v 0.1 2017年12月31日 20:43 MJJ Exp $
 **/
public interface PublishService {

    /**
     * 查询节目管理
     *
     * @param proName   节目名称
     * @param proStatus 节目状态
     * @param status    发布状态
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> queryPublish(String proName,
                                                 String proStatus,
                                                 String status,
                                                 String startTime,
                                                 String endTime,
                                                 Integer pageNo,
                                                 Integer pageCount);

    /**
     * 新增节目管理
     *
     * @param publish 节目管理对象
     * @return
     */
    RespEntity<String> createPublish(PublishManage publish);

    /**
     * 编辑节目管理
     *
     * @param publish 节目管理对象
     * @return
     */
    RespEntity<String> updatePublish(PublishManage publish);

    /**
     * 删除节日管理
     *
     * @param ids ID(List集合)
     * @return
     */
    RespEntity<String> deletePublish(String ids);

    /**
     * 审核节目
     *
     * @param id   ID(发布单号)
     * @param type 审核状态
     * @return
     */
    RespEntity<String> auditProgram(String id, String type);
}
