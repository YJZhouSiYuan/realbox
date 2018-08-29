/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.ProgramEntity;
import com.realbox.model.entity.RespEntity;
import com.realbox.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: ProgramController.java v 0.1 2017年12月11日 下午4:26 MJJ Exp $
 **/
@RestController
@RequestMapping("program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

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
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryProgram(@RequestParam(required = false) String id,
                                                        @RequestParam(required = false) String groupId,
                                                        @RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String resolution,
                                                        @RequestParam(required = false) String terminalType,
                                                        @RequestParam Integer pageNo,
                                                        @RequestParam Integer pageCount) {
        return programService.queryProgram(id, groupId, name, resolution, terminalType, pageNo, pageCount);
    }

    /**
     * 新增节目
     *
     * @param entity 节目对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<String> createProgram(@RequestBody ProgramEntity entity) {
        return programService.createProgram(entity);
    }

    /**
     * 编辑节目
     *
     * @param entity 节目对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updateProgram(@RequestBody ProgramEntity entity) {
        return programService.updateProgram(entity);
    }

    /**
     * 删除节目
     *
     * @param ids ID(List集合)
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteProgram(@RequestParam String ids) {
        return programService.deleteProgram(ids);
    }

}
