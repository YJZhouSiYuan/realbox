/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.RolePrivilege;
import com.realbox.model.entity.RespEntity;
import com.realbox.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: PrivilegeController.java v 0.1 2017年12月07日 下午4:18 MJJ Exp $
 **/
@RestController
@RequestMapping("privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    /**
     * 查询权限
     *
     * @param userId 登录用户权限ID
     * @param roleId 编辑角色权限ID(可选)
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryPrivilege(@RequestParam String userId,
                                                          @RequestParam(required = false) String roleId) {
        return privilegeService.queryPrivilege(userId, roleId);
    }

    /**
     * 新增权限
     *
     * @param privilege 权限对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<Map<String, Object>> createPrivilege(@RequestBody RolePrivilege privilege) {
        return privilegeService.createPrivilege(privilege);
    }

    /**
     * 编辑权限
     *
     * @param privilege 权限对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updatePrivilege(@RequestBody RolePrivilege privilege) {
        return privilegeService.updatePrivilege(privilege);
    }
}
