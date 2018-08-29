/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.RolePrivilege;
import com.realbox.model.entity.RespEntity;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: PrivilegeService.java v 0.1 2017年12月07日 下午4:21 MJJ Exp $
 **/
public interface PrivilegeService {

    /**
     * 查询权限
     *
     * @param userId 登录用户权限ID
     * @param roleId 编辑角色权限ID
     * @return
     */
    RespEntity<Map<String, Object>> queryPrivilege(String userId, String roleId);

    /**
     * 新增权限
     *
     * @param privilege 权限对象
     * @return
     */
    RespEntity<Map<String, Object>> createPrivilege(RolePrivilege privilege);

    /**
     * 编辑权限
     *
     * @param privilege 权限对象
     * @return
     */
    RespEntity<String> updatePrivilege(RolePrivilege privilege);
}
