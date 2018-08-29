/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: Res.java v 0.1 2017年12月06日 下午8:28 MJJ Exp $
 **/
public class Resource implements Serializable {

    private static final long serialVersionUID = 7443932910480908431L;

    // ID
    private String id;
    // 资源名称
    private String name;
    // 资源类型
    private String type;
    // 文件下载地址
    private String url;
    // 缩略图的下载地址
    private String thumbnail;
    // 绝对路径
    private String absolute;
    // 分辨率
    private String resolution;
    // 大小
    private Long size;
    // 上传者ID
    private String uploader;
    // 上传时间
    private String uploadTime;
    // 更新者ID
    private String upgrader;
    // 最近更新时间
    private String upgradeTime;
    // 下载方式
    private String protocol;
    // 权限ID
    private String privId;
    // 组ID(资源管理树ID)
    private String groupId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAbsolute() {
        return absolute;
    }

    public void setAbsolute(String absolute) {
        this.absolute = absolute;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUpgrader() {
        return upgrader;
    }

    public void setUpgrader(String upgrader) {
        this.upgrader = upgrader;
    }

    public String getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(String upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", absolute='" + absolute + '\'' +
                ", resolution='" + resolution + '\'' +
                ", size=" + size +
                ", uploader='" + uploader + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", upgrader='" + upgrader + '\'' +
                ", upgradeTime='" + upgradeTime + '\'' +
                ", protocol='" + protocol + '\'' +
                ", privId='" + privId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
