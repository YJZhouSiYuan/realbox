/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.RolePrivilege;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: PrivilegeMapper.java v 0.1 2017年12月17日 11:03 MJJ Exp $
 **/
@Repository
public class PrivilegeMapper extends MongoDao<RolePrivilege> {

    /**
     * 修改
     *
     * @param cond      条件
     * @param privilege 权限对象
     */
    public void update(Map<String, Object> cond, RolePrivilege privilege) {
        // 修改
        super.update(cond, getUpdate(privilege), RolePrivilege.class);
    }

    /**
     * 修改(ID)
     *
     * @param id        ID
     * @param privilege 权限对象
     */
    public void update(String id, RolePrivilege privilege) {
        // 修改
        super.update(id, getUpdate(privilege), RolePrivilege.class);
    }

    /**
     * 获取修改参数
     *
     * @param privilege 权限对象
     * @return
     */
    private Map<String, Object> getUpdate(RolePrivilege privilege) {
        return BeanUtils.beanToMap(privilege);
    }
}
