/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.model.RolePrivilege;
import com.realbox.model.entity.RespEntity;
import com.realbox.common.utils.Maps;
import com.realbox.model.Tree;
import com.realbox.model.entity.TreeEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.PrivilegeMapper;
import com.realbox.service.PrivilegeService;
import com.realbox.service.utils.LogUtils;
import com.realbox.service.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: PrivilegeServiceImpl.java v 0.1 2017年12月07日 下午4:24 MJJ Exp $
 **/
@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PrivilegeMapper privMapper;

    @Autowired
    private TreeUtils treeUtils;

    @Autowired
    private LogUtils logUtils;



    /**
     * 查询权限
     *
     * @param id1 登录角色权限ID
     * @param id2 编辑角色权限ID
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryPrivilege(String id1, String id2) {
        if (StringUtils.isEmpty(id1)) {
            return error(RealBox.PRIV002);
        }

        // 查询权限
        RolePrivilege privilege = privMapper.queryById(id1, RolePrivilege.class);

        // 查询权限树
        List<TreeEntity> trees = treeUtils.getPrivTree(privilege);

        // 存储数据
        Map<String, Object> map = Maps.create().add("trees", trees).get();

        // 登录角色权限 + 编辑角色权限 = 合并。
        if (!StringUtils.isEmpty(id2)) {
            privilege = privMapper.queryById(id2, RolePrivilege.class);
            List<Tree> tree = treeUtils.getRole(privilege);
            map.putAll(Maps.create().add("tree", tree).get());
        }

        // 返回
        return success(map);
    }

    /**
     * 新增权限
     *
     * @param privilege 权限对象
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> createPrivilege(RolePrivilege privilege) {

        // 新增权限
        RolePrivilege rolePrivilege = privMapper.create(privilege);

        // 记录日志
        logUtils.userLog("新增权限", request);

        // 返回
        Map<String, Object> map = Maps.create().add("id", rolePrivilege.getId()).get();
        return success(map);
    }

    /**
     * 编辑权限
     *
     * @param privilege 权限对象
     * @return
     */
    @Override
    public RespEntity<String> updatePrivilege(RolePrivilege privilege) {
        if (StringUtils.isEmpty(privilege.getId())) {
            return errors(RealBox.PRIV000);
        }

        // 验证权限
        RolePrivilege result = privMapper.queryById(privilege.getId(), RolePrivilege.class);
        if (result == null) {
            return errors(RealBox.PRIV001);
        }

        // 编辑权限
        privMapper.update(privilege.getId(), privilege);

        // 记录日志
        logUtils.userLog("编辑权限", request);

        // 返回
        return success();
    }
}
