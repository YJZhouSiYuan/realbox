/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.Role;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: RoleService.java v 0.1 2017年11月28日 上午11:50 MJJ Exp $
 **/
public interface RoleService {

    /**
     * 查询角色
     *
     * @param name      角色名
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    RespEntity<Map<String, Object>> queryRole(String name, Integer pageNo, Integer pageCount);

    /**
     * 添加角色
     *
     * @param role 角色对象
     */
    RespEntity<String> createRole(Role role);

    /**
     * 编辑角色
     *
     * @param role 角色对象
     * @return
     */
    RespEntity<String> updateRole(Role role);

    /**
     * 删除角色(批量)
     *
     * @param ids ID(List集合)
     * @return
     */
    RespEntity<String> deleteRole(String ids);
}
