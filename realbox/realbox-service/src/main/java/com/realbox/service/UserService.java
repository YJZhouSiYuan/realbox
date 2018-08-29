/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: UserService.java v 0.1 2017年11月05日 上午4:56 MJJ Exp $
 **/
public interface UserService {

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
    RespEntity<Map<String, Object>> queryUser(String name, String groupId, String type, Integer pageNo, Integer pageCount);

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return
     */
    RespEntity<String> createUser(User user);

    /**
     * 编辑用户
     *
     * @param user 用户对象
     * @return
     */
    RespEntity<String> updateUser(User user);

    /**
     * 删除用户
     *
     * @param id ID
     * @return
     */
    RespEntity<String> deleteUser(String id);
}
