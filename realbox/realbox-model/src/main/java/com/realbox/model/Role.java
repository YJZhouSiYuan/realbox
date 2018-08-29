/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MJJ
 * @create Id: Role.java v 0.1 2017年11月28日 上午10:42 MJJ Exp $
 * 角色
 **/
public class Role implements Serializable {

    private static final long serialVersionUID = 3842267767709071230L;

    // ID
    private String id;
    // 权限ID
    private String privId;
    // 角色名
    private String name;
    // 创建人
    private String creator;
    // 更新时间
    private String updateTime;
    // 角色描述
    private String desc;

    public Role() { }

    public Role(String id, String privId, String name, String creator, String updateTime, String desc) {
        this.id = id;
        this.privId = privId;
        this.name = name;
        this.creator = creator;
        this.updateTime = updateTime;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
