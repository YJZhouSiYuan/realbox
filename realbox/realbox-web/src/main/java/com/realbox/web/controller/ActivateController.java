/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.service.ActivateService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author MJJ
 * @create Id: ActivateController.java v 0.1 2017年12月12日 11:43 MJJ Exp $
 **/
@RestController
@RequestMapping("activate")
public class ActivateController {

    @Autowired
    private ActivateService activateService;

    /**
     * 查询激活码
     *
     * @param id        ID
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryActivation(@RequestParam(required = false) String id,
                                                           @RequestParam Integer pageNo,
                                                           @RequestParam Integer pageCount) {
        return activateService.queryActivation(id, pageNo, pageCount);
    }

    /**
     * 新增激活码
     *
     * @param terId   终端树ID
     * @param creator 创建人
     * @param number  生成数量
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public RespEntity<String> createActivation(@RequestParam String terId,
                                               @RequestParam String creator,
                                               @RequestParam Integer number) {
        return activateService.createActivation(terId, creator, number);
    }

    /**
     * 删除激活码
     *
     * @param ids ID
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteActivation(@RequestParam String ids, HttpServletRequest request) {
        return activateService.deleteActivation(ids, request);
    }

    /**
     * 激活码解绑
     *
     * @param ids 激活码ID(批量解绑)
     * @return
     */
    @RequestMapping(value = "unbundled", method = RequestMethod.GET)
    public RespEntity<String> unbundledActivate(@RequestParam String ids) {
        return activateService.unbundledActivate(ids);
    }

    /**
     * 激活码导出
     *
     * @param ids     激活码ID(一个或多个)
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> exportActivate(@RequestParam String ids, HttpServletRequest request) {
        return activateService.exportActivate(ids, request);
    }
}
