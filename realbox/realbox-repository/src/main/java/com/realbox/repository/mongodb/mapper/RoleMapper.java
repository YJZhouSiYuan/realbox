/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.Role;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: RoleMapper.java v 0.1 2017年12月17日 10:22 MJJ Exp $
 **/
@Repository
public class RoleMapper extends MongoDao<Role> {

    /**
     * 修改
     *
     * @param cond 条件
     * @param role 角色对象
     */
    public void update(Map<String, Object> cond, Role role) {
        // 修改
        super.update(cond, getUpdate(role), Role.class);
    }

    /**
     * 修改(ID)
     *
     * @param id   ID
     * @param role 角色对象
     */
    public void update(String id, Role role) {
        // 修改
        super.update(id, getUpdate(role), Role.class);
    }

    /**
     * 获取修改参数
     *
     * @param role 角色对象
     * @return
     */
    private Map<String, Object> getUpdate(Role role) {
        return BeanUtils.beanToMap(role);
    }
}
