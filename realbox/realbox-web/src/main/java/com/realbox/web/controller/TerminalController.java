/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TerminalController.java v 0.1 2017年12月11日 下午4:24 MJJ Exp $
 **/
@RestController
@RequestMapping("terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

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
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryTerminal(@RequestParam(required = false) String groupId,
                                                         @RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String code,
                                                         @RequestParam Integer pageNo,
                                                         @RequestParam Integer pageCount) {
        return terminalService.queryTerminal(groupId, name, code, pageNo, pageCount);
    }

    /**
     * 删除终端
     *
     * @param ids 终端ID
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteTerminal(@RequestParam String ids) {
        return terminalService.deleteTerminal(ids);
    }

    /**
     * 激活终端
     *
     * @param code 激活码
     * @return
     */
    @RequestMapping(value = "activate", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> activate(@RequestParam String code) {
        return terminalService.activate(code);
    }

    /**
     * 服务器强制终端同步
     *
     * @param ids 终端ID
     * @return
     */
    @RequestMapping(value = "syn", method = RequestMethod.GET)
    public RespEntity<String> aynPublish(String ids) {
        return terminalService.aynTerminal(ids);
    }

}
