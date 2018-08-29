/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: PublicModule.java v 0.1 2017年12月03日 下午10:44 MJJ Exp $
 * 角色公共模块
 **/
public class PublicModule implements Serializable {

    private static final long serialVersionUID = 11657490380698217L;

    // ID
    private String id;
    // 模块名
    private String name;
    // 结构树ID
    private String treeId;

    public PublicModule() { }

    public PublicModule(String id, String name, String treeId) {
        this.id = id;
        this.name = name;
        this.treeId = treeId;
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

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }
}
