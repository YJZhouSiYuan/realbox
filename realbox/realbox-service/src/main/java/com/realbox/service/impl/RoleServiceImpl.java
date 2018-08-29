/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.model.RolePrivilege;
import com.realbox.model.entity.RespEntity;
import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.Role;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.PrivilegeMapper;
import com.realbox.repository.mongodb.mapper.RoleMapper;
import com.realbox.service.RoleService;
import com.realbox.service.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: RoleServiceImpl.java v 0.1 2017年11月28日 上午11:51 MJJ Exp $
 **/
@Service("roleService")
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private PrivilegeMapper privMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private LogUtils logUtils;

    /**
     * 查询角色
     *
     * @param name      角色名
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryRole(String name, Integer pageNo, Integer pageCount) {
        if (StringUtils.isEmpty(pageNo)) {
            return error(RealBox.PAGE000);
        }
        if (StringUtils.isEmpty(pageCount)) {
            return error(RealBox.PAGE001);
        }

        // 查询数量
        Map<String, Object> roleCond = Maps.create().add("name", name).get();
        Long count = roleMapper.count(roleCond, Role.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询角色
        List<Role> roles = roleMapper.queryList(roleCond, pages, Role.class);

        // 返回
        Map<String, Object> map = Maps.create().add("roles", roles).add("pages", pages).get();
        return success(map);
    }

    /**
     * 新增角色
     *
     * @param role 角色对象
     */
    @Override
    public RespEntity<String> createRole(Role role) {
        if (StringUtils.isEmpty(role.getName())) {
            return errors(RealBox.ROLE001);
        }
        if (StringUtils.isEmpty(role.getPrivId())) {
            return errors(RealBox.PRIV000);
        }
        if (StringUtils.isEmpty(role.getCreator())) {
            return errors(RealBox.ROLE003);
        }

        //  验证角色
        Map<String, Object> cond = Maps.create().add("name", role.getName()).get();
        Role result = roleMapper.query(cond, Role.class);
        if (result != null) {
            return errors(RealBox.ROLE004);
        }

        // 验证权限
        RolePrivilege privilege = privMapper.queryById(role.getPrivId(), RolePrivilege.class);
        if (privilege == null) {
            return errors(RealBox.PRIV001);
        }

        // 设置新增角色参数
        setRole(role);

        // 新增角色
        roleMapper.create(role);

        // 记录日志
        logUtils.userLog("新增角色", request);

        // 返回
        return success();
    }

    /**
     * 编辑角色
     *
     * @param role 角色对象
     * @return
     */
    @Override
    public RespEntity<String> updateRole(Role role) {
        if (StringUtils.isEmpty(role.getId())) {
            return errors(RealBox.ROLE000);
        }
        if (StringUtils.isEmpty(role.getPrivId())) {
            return errors(RealBox.PRIV000);
        }
        if (StringUtils.isEmpty(role.getName())) {
            return errors(RealBox.ROLE001);
        }
        if (StringUtils.isEmpty(role.getCreator())) {
            return errors(RealBox.ROLE003);
        }

        // 验证角色
        Map<String, Object> roleCond = Maps.create().add("name", role.getName()).get();
        Role result = roleMapper.query(roleCond, Role.class);
        // 已存在
        if (result != null) {
            if (!result.getId().equals(role.getId())) {
                return errors(RealBox.ROLE004);
            }
        }
        // 不存在
        if (result == null) {
            Role entity = roleMapper.queryById(role.getId(), Role.class);
            if (entity == null) {
                return errors(RealBox.ROLE002);
            }
        }

        // 验证权限
        RolePrivilege privilege = privMapper.queryById(role.getPrivId(), RolePrivilege.class);
        if (privilege == null) {
            return errors(RealBox.PRIV001);
        }

        // 设置编辑角色参数
        setRole(role);

        // 编辑角色
        roleMapper.update(role.getId(), role);

        // 记录日志
        logUtils.userLog("编辑角色", request);

        // 返回
        return success();
    }

    /**
     * 删除角色(批量)
     *
     * @param ids ID(List集合)
     * @return
     */
    @Override
    public RespEntity<String> deleteRole(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.ROLE000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证角色
        List<Role> roles = roleMapper.batchQueryById(arrayId, Role.class);
        if (roles.isEmpty()) {
            return errors(RealBox.ROLE002);
        }

        for (Role role : roles) {
            if ("admin".equals(role.getName())) {
                return errors(RealBox.USER006);
            }
        }

        // 删除角色
        roleMapper.batchDelete(arrayId, Role.class);

        // 记录日志
        logUtils.userLog("删除角色", request);

        // 遍历角色权限ID
        List<String> id = new ArrayList<String>();
        for (Role role : roles) {
            id.add(role.getPrivId());
        }

        // 删除权限
        privMapper.batchDelete(id, RolePrivilege.class);

        // 记录日志
        logUtils.userLog("删除权限", request);

        // 返回
        return success();
    }

    /**
     * 设置角色参数
     *
     * @param role 角色对象
     */
    private void setRole(Role role) {
        // 更新时间
        role.setUpdateTime(DateUtils.getDateTime());
    }
}
