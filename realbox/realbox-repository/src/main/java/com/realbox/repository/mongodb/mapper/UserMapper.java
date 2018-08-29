/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.User;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: UserMapper.java v 0.1 2017年12月16日 19:16 MJJ Exp $
 **/
@Repository
public class UserMapper extends MongoDao<User> {

    /**
     * 修改
     *
     * @param cond 条件
     * @param user 用户对象
     */
    public void update(Map<String, Object> cond, User user) {
        // 修改
        super.update(cond, getUpdate(user), User.class);
    }

    /**
     * 修改(ID)
     *
     * @param id   ID
     * @param user 用户对象
     */
    public void update(String id, User user) {
        // 修改
        super.update(id, getUpdate(user), User.class);
    }

    /**
     * 获取修改参数
     *
     * @param user 用户对象
     * @return
     */
    private Map<String, Object> getUpdate(User user) {
        // 实体转Map
        Map<String, Object> entity = BeanUtils.beanToMap(user);

        // 移除密码
        entity.remove("password");

        // 返回
        return entity;
    }

}
