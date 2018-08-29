/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.entity;

import com.realbox.model.ProItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MJJ
 * @create Id: ProgramEntity.java v 0.1 2017年12月20日 21:04 MJJ Exp $
 **/
public class ProgramEntity implements Serializable {

    private static final long serialVersionUID = 2473261368433133355L;

    // ID
    private String id;
    // 组ID(节目列表树ID)
    private String groupId;
    // 权限ID
    private String privId;
    // 模板ID
    private String modelId;
    // 节目名称
    private String name;
    // 节目预览图
    private String preview;
    // 分辨率
    private String resolution;
    // 终端类型
    private String terminalType;
    // 节目状态
    private String status;
    // 节目元素
    private List<ProItems> proItems = new ArrayList<ProItems>();
    // 更新时间
    private String updateTime;
    // 代码内容
    private String body;

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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
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

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProItems> getProItems() {
        return proItems;
    }

    public void setProItems(List<ProItems> proItems) {
        this.proItems = proItems;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
