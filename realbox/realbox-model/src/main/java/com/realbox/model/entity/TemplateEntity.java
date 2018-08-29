/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.entity;

import com.realbox.model.TemItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MJJ
 * @create Id: TemplateEntity.java v 0.1 2017年12月28日 15:38 MJJ Exp $
 **/
public class TemplateEntity implements Serializable {

    private static final long serialVersionUID = 6655029854002541753L;

    // ID
    private String id;
    // 组ID(模板树ID)
    private String groupId;
    // 资源权限ID
    private String privId;
    // 模板名
    private String name;
    // 预览图
    private String preview;
    // 创建人
    private String creator;
    // 模板类型
    private String type;
    // 终端类型
    private String terminalType;
    // 更新时间
    private String updateTime;
    // 分辨率
    private String resolution;
    // 高
    private Long height;
    // 宽
    private Long width;
    // 元素
    private List<TemItems> temItems = new ArrayList<TemItems>();
    // 代码内容
    private String body;
    // 描述
    private String desc;

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

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
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

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public List<TemItems> getTemItems() {
        return temItems;
    }

    public void setTemItems(List<TemItems> temItems) {
        this.temItems = temItems;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
