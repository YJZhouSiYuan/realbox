/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: ResourceService.java v 0.1 2017年12月05日 下午9:58 MJJ Exp $
 **/
public interface ResourceService {

    /**
     * 查询资源
     *
     * @param groupId   组ID(资源管理树ID)
     * @param name      资源名
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> queryResource(String groupId, String name, Integer pageNo, Integer pageCount);

    /**
     * 上传资源
     *
     * @param groupId  组ID(资源管理树ID)
     * @param uploader 上传者ID
     * @param type     资源类型
     * @param file     上传文件
     * @return
     */
    RespEntity<String> uploadResource(String groupId, String uploader, String type, MultipartFile file);

    /**
     * 删除资源
     *
     * @param ids     ID(List集合)
     * @param request 请求
     * @return
     */
    RespEntity<String> deleteResource(String ids, HttpServletRequest request);
}
