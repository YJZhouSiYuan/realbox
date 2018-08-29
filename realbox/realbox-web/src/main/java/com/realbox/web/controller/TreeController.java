/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.Tree;
import com.realbox.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TreeController.java v 0.1 2017年12月04日 上午12:00 MJJ Exp $
 **/
@RestController
@RequestMapping("tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    /**
     * 查询结构树
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryTree(@RequestParam String id) {
        return treeService.queryTree(id);
    }

    /**
     * 新增结构树
     *
     * @param tree 结构树对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<String> createTree(@RequestBody Tree tree) {
        return treeService.createTree(tree);
    }

    /**
     * 编辑结构树
     *
     * @param tree 结构树对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updateTree(@RequestBody Tree tree) {
        return treeService.updateTree(tree);
    }

    /**
     * 删除结构树
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteTree(@RequestParam String id) {
        return treeService.deleteTree(id);
    }
}
