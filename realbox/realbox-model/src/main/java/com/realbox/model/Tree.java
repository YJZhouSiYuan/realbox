/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MJJ
 * @create Id: Trees.java v 0.1 2017年11月27日 下午9:59 MJJ Exp $
 * 结构树
 **/
public class Tree implements Serializable {

    private static final long serialVersionUID = -5176974797448200376L;

    // ID
    private String id;
    // 名称
    private String label;
    // 图标地址
    private String iconUrl;
    // 图标类型
    private String iconType;
    // 树类型
    private String treeType;
    // 创建人
    private String creator;
    // 创建时间
    private String createTime;
    // 更新人
    private String updaterCreator;
    // 更新时间
    private String updateTime;
    // 父
    private String parentId;
    // 新父ID
    private String newId;
    // 父名
    private String parentName;
    // 子
    private List<String> children = new ArrayList<String>();
    // 属性
    private String property;
    // 扩展
    private String customData;
    // 是否可操作(YES:可操作,NO:不可操作)
    private String operate;

    public Tree() {
    }

    public Tree(String id, String label, String treeType, String creator, String createTime, String parentId, String parentName, List<String> children, String operate) {
        this.id = id;
        this.label = label;
        this.treeType = treeType;
        this.creator = creator;
        this.createTime = createTime;
        this.parentId = parentId;
        this.parentName = parentName;
        this.children = children;
        this.operate = operate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterCreator() {
        return updaterCreator;
    }

    public void setUpdaterCreator(String updaterCreator) {
        this.updaterCreator = updaterCreator;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(String id) {
        this.children.add(id);
    }

    public void setChildrens(List<String> ids) {
        this.children = ids;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
