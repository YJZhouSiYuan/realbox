/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MJJ
 * @create Id: TreeEntity.java v 0.1 2017年12月14日 15:25 MJJ Exp $
 **/
public class TreeEntity implements Serializable {

    private static final long serialVersionUID = -446698097697456268L;

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
    // 父名
    private String parentName;
    // 子
    private List<TreeEntity> children = new ArrayList<TreeEntity>();
    // 属性
    private String property;
    // 扩展
    private String customData;
    // 是否可操作(YES:可操作,NO:不可操作)
    private String operate;

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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<TreeEntity> getChildren() {
        return children;
    }

    public void setChildren(TreeEntity children) {
        this.children.add(children);
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
