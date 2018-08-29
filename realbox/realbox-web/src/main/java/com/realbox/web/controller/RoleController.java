/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.Role;
import com.realbox.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: RoleController.java v 0.1 2017年11月28日 上午10:10 MJJ Exp $
 **/
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询角色
     *
     * @param name      角色名
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryRole(@RequestParam(required = false) String name,
                                                     @RequestParam Integer pageNo,
                                                     @RequestParam Integer pageCount) {
        return roleService.queryRole(name, pageNo, pageCount);
    }

    /**
     * 新增角色
     *
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<String> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    /**
     * 编辑角色
     *
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    /**
     * 删除角色(批量)
     *
     * @param ids ID(List集合)
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteRole(@RequestParam String ids) {
        return roleService.deleteRole(ids);
    }
}
