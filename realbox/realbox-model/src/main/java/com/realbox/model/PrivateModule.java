/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: PrivateModule.java v 0.1 2017年12月05日 上午11:46 MJJ Exp $
 * 角色私有模块
 **/
public class PrivateModule implements Serializable {

    private static final long serialVersionUID = 4323670831120553299L;
    // ID
    private String id;
    // 角色ID
    private String roleId;
    // 模块ID
    private String moduleId;
    // 模块名
    private String name;

    public PrivateModule() { }

    public PrivateModule(String roleId, String moduleId, String name) {
        this.roleId = roleId;
        this.moduleId = moduleId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
