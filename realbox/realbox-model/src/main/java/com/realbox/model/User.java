/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: User.java v 0.1 2017年11月27日 下午9:05 MJJ Exp $
 * 用户
 **/
public class User implements Serializable {

    private static final long serialVersionUID = -8023109499484346050L;

    // ID
    private String id;
    // 用户树ID
    private String groupId;
    // 用户树名称
    private String groupName;
    // 终端树ID
    private String terId;
    // 终端树名称
    private String terName;
    // 角色ID
    private String roleId;
    // 角色名称
    private String roleName;
    // 用户名
    private String name;
    // 密码
    private String password;
    // 创建人
    private String creator;
    // 用户类型
    private String type;
    // 终端数量
    private int number;
    // 更新时间
    private String updateTime;
    // 语言
    private String language;
    // 描述
    private String desc;
    // 验证码
    private String code;

    public User() { }

    public User(String id, String groupId, String groupName, String terId, String terName, String roleId, String roleName,
                String name, String password, String creator, String updateTime, String language, String desc) {
        this.id = id;
        this.groupId = groupId;
        this.groupName = groupName;
        this.terId = terId;
        this.terName = terName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.name = name;
        this.password = password;
        this.creator = creator;
        this.updateTime = updateTime;
        this.language = language;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTerId() {
        return terId;
    }

    public void setTerId(String terId) {
        this.terId = terId;
    }

    public String getTerName() {
        return terName;
    }

    public void setTerName(String terName) {
        this.terName = terName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
