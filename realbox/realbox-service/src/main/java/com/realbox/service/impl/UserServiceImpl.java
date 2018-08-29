/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.Role;
import com.realbox.model.Tree;
import com.realbox.model.User;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.RoleMapper;
import com.realbox.repository.mongodb.mapper.TreeMapper;
import com.realbox.repository.mongodb.mapper.UserMapper;
import com.realbox.service.UserService;
import com.realbox.service.utils.LogUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: UserServiceImpl.java v 0.1 2017年12月01日 上午10:13 MJJ Exp $
 **/
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private TreeMapper treeMapper;

    @Autowired
    private LogUtils logUtils;

    /**
     * 查询用户
     *
     * @param name      用户名
     * @param groupId   用户树ID
     * @param type      用户类型
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryUser(String name, String groupId, String type, Integer pageNo, Integer pageCount) {
        if (StringUtils.isEmpty(pageNo)) {
            return error(RealBox.PAGE000);
        }
        if (StringUtils.isEmpty(pageCount)) {
            return error(RealBox.PAGE001);
        }

        // 查询数量
        Map<String, Object> cond = Maps.create().add("name", name).add("groupId", groupId).add("type", type).get();
        Long count = userMapper.count(cond, User.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询用户
        List<User> users = userMapper.queryList(cond, pages, User.class);

        // 返回
        Map<String, Object> result = Maps.create().add("users", users).add("pages", pages).get();
        return success(result);
    }

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public RespEntity<String> createUser(User user) {
        if (StringUtils.isEmpty(user.getRoleId())) {
            return errors(RealBox.ROLE000);
        }
        if (StringUtils.isEmpty(user.getGroupId())) {
            return errors(RealBox.GROUP000);
        }
        if (StringUtils.isEmpty(user.getTerId())) {
            return errors(RealBox.TRETER000);
        }
        if (StringUtils.isEmpty(user.getName())) {
            return errors(RealBox.USER001);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return errors(RealBox.USER002);
        }
        if (StringUtils.isEmpty(user.getCreator())) {
            return errors(RealBox.USER003);
        }

        // 验证用户
        Map<String, Object> userCond = Maps.create().add("name", user.getName()).get();
        User result = userMapper.query(userCond, User.class);
        if (result != null) {
            return errors(RealBox.USER004);
        }

        // 验证角色
        Role role = roleMapper.queryById(user.getRoleId(), Role.class);
        if (role == null) {
            return errors(RealBox.ROLE002);
        }

        // 验证用户树
        Tree group = treeMapper.queryById(user.getGroupId(), Tree.class);
        if (group == null) {
            return errors(RealBox.GROUP001);
        }

        // 验证终端树
        Tree ter = treeMapper.queryById(user.getTerId(), Tree.class);
        if (ter == null) {
            return errors(RealBox.TRETER001);
        }

        // 设置新增用户参数
        setUser(user, role, group, ter);

        // 新增用户
        userMapper.create(user);

        // 记录日志
        logUtils.userLog("新增用户", request);

        // 返回
        return success();
    }

    /**
     * 编辑用户
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public RespEntity<String> updateUser(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            return errors(RealBox.USER000);
        }
        if (StringUtils.isEmpty(user.getRoleId())) {
            return errors(RealBox.ROLE000);
        }
        if (StringUtils.isEmpty(user.getGroupId())) {
            return errors(RealBox.GROUP000);
        }
        if (StringUtils.isEmpty(user.getTerId())) {
            return errors(RealBox.TRETER000);
        }
        if (StringUtils.isEmpty(user.getName())) {
            return errors(RealBox.USER001);
        }
        if (StringUtils.isEmpty(user.getCreator())) {
            return errors(RealBox.USER003);
        }

        // 验证用户
        Map<String, Object> cond = Maps.create().add("name", user.getName()).get();
        User result = userMapper.query(cond, User.class);
        // 已存在
        if (result != null) {
            if (!user.getId().equals(result.getId())) {
                return errors(RealBox.USER004);
            }
        } else {
            User entity = userMapper.queryById(user.getId(), User.class);
            if (entity == null) {
                return errors(RealBox.USER005);
            }
        }

        // 验证角色
        Role role = roleMapper.queryById(user.getRoleId(), Role.class);
        if (role == null) {
            return errors(RealBox.ROLE002);
        }

        // 验证用户树
        Tree group = treeMapper.queryById(user.getGroupId(), Tree.class);
        if (group == null) {
            return errors(RealBox.GROUP001);
        }

        // 验证终端树
        Tree ter = treeMapper.queryById(user.getTerId(), Tree.class);
        if (ter == null) {
            return errors(RealBox.DEPT001);
        }

        // 设置编辑用户参数
        setUser(user, role, group, ter);

        // 编辑用户
        userMapper.update(user.getId(), user);

        // 记录日志
        logUtils.userLog("编辑用户", request);

        // 返回
        return success();
    }

    /**
     * 删除用户
     *
     * @param ids ID
     * @return
     */
    @Override
    public RespEntity<String> deleteUser(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.USER000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证用户
        List<User> users = userMapper.batchQueryById(arrayId, User.class);
        if (users.isEmpty()) {
            return errors(RealBox.USER005);
        }
        for (User user : users) {
            if ("admin".equals(user.getName())) {
                return errors(RealBox.USER006);
            }
        }

        // 删除用户
        userMapper.batchDelete(arrayId, User.class);

        // 记录日志
        logUtils.userLog("删除用户", request);

        // 返回
        return success();
    }

    /**
     * 设置用户参数
     *
     * @param user  用户对象
     * @param role  角色对象
     * @param group 用户分组树对象
     * @param ter   终端分组树对象
     */
    private void setUser(User user, Role role, Tree group, Tree ter) {
        // 角色名
        user.setRoleName(role.getName());
        // 用户树名
        user.setGroupName(group.getLabel());
        // 终端树名
        user.setTerName(ter.getLabel());
        // 更新时间
        user.setUpdateTime(DateUtils.getDateTime());
        // 密码
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(new Sha256Hash(user.getName(), user.getPassword()).toHex());
        }
    }
}
