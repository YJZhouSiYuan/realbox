/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.enums;

/**
 * @author MJJ
 * @create Id: Trees.java v 0.1 2017年12月17日 17:16 MJJ Exp $
 **/
public enum Trees {
    RESOURCE("资源管理", "0"),
    TEMPLATE("模板类型", "20"),
    PROGRAM("节目列表", "30"),
    TERMINAL("终端分组", "40"),
    USER("用户分组", "50"),
    PRIV("全部权限", "70");

    // 树名
    private String name;
    // ID
    private String value;

    Trees(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
