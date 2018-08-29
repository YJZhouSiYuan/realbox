/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.web.controller;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.User;
import com.realbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: UserController.java v 0.1 2017年12月01日 上午10:09 MJJ Exp $
 **/
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

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
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public RespEntity<Map<String, Object>> queryUser(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String groupId,
                                                     @RequestParam(required = false) String type,
                                                     @RequestParam Integer pageNo,
                                                     @RequestParam Integer pageCount) {
        return userService.queryUser(name, groupId, type, pageNo, pageCount);
    }

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public RespEntity<String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * 编辑用户
     *
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public RespEntity<String> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @param ids ID(List集合)
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public RespEntity<String> deleteUser(@RequestParam String ids) {
        return userService.deleteUser(ids);
    }

}
