/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: ResourceController.java v 0.1 2017年12月05日 下午9:48 MJJ Exp $
 **/
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 查询资源
     *
     * @param groupId   组ID(资源管理树ID)
     * @param name      资源名
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryResource(@RequestParam String groupId,
                                                         @RequestParam(required = false) String name,
                                                         @RequestParam Integer pageNo,
                                                         @RequestParam Integer pageCount) {
        return resourceService.queryResource(groupId, name, pageNo, pageCount);
    }

    /**
     * 上传资源
     *
     * @param groupId  组ID(资源管理树ID)
     * @param uploader 上传者ID
     * @param type     资源类型
     * @param file     上传文件
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public RespEntity<String> uploadResource(@RequestParam String groupId,
                                             @RequestParam String uploader,
                                             @RequestParam String type,
                                             MultipartFile file) {
        return resourceService.uploadResource(groupId, uploader, type, file);
    }

    /**
     * 删除资源
     *
     * @param ids     ID(List集合)
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteResource(String ids, HttpServletRequest request) {
        return resourceService.deleteResource(ids, request);
    }
}
