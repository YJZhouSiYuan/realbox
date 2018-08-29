/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import com.realbox.model.bean.terminal.AdvCustomerBean;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: Terminal.java v 0.1 2017年12月26日 11:22 MJJ Exp $
 **/
public class Terminal implements Serializable {

    private static final long serialVersionUID = 5903540489633422976L;

    // ID
    private String id;
    // 终端树ID
    private String groupId;
    // 用户ID
    private String userId;
    // 创建人
    private String creator;
    // 终端名称
    private String name;
    // IP
    private String ip;
    // MAC地址
    private String mac;
    // 终端类型
    private String type;
    // 分辩率
    private String resolution;
    // 硬件号
    private String terminalCode;
    // 磁盘空间
    private String diskSpace;
    // 驱动
    private String displayDriver;
    // 版卡类型
    private String boardType;
    // 版本
    private String version;
    // 服务版本
    private String sversion;
    // 下载状态
    private String downloadStatus;
    // 更新时间
    private String updateTime;
    // 最后打开时间
    private String lastManualOpeTime;
    // 最后发布时间
    private String lastPublishTime;
    // 最后更新时间
    private String lastUpdateTime;
    // 最后设置时间
    private String lastSettingTime;
    //
    private String bandLimit;
    //
    private AdvCustomerBean customer;
    //
    private String interGrate;
    // 在线状态(0:不在线,1:在线)
    private String status;
    // 激活状态
    private boolean actStatus;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(String diskSpace) {
        this.diskSpace = diskSpace;
    }

    public String getDisplayDriver() {
        return displayDriver;
    }

    public void setDisplayDriver(String displayDriver) {
        this.displayDriver = displayDriver;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSversion() {
        return sversion;
    }

    public void setSversion(String sversion) {
        this.sversion = sversion;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastManualOpeTime() {
        return lastManualOpeTime;
    }

    public void setLastManualOpeTime(String lastManualOpeTime) {
        this.lastManualOpeTime = lastManualOpeTime;
    }

    public String getLastPublishTime() {
        return lastPublishTime;
    }

    public void setLastPublishTime(String lastPublishTime) {
        this.lastPublishTime = lastPublishTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastSettingTime() {
        return lastSettingTime;
    }

    public void setLastSettingTime(String lastSettingTime) {
        this.lastSettingTime = lastSettingTime;
    }

    public String getBandLimit() {
        return bandLimit;
    }

    public void setBandLimit(String bandLimit) {
        this.bandLimit = bandLimit;
    }

    public AdvCustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(AdvCustomerBean customer) {
        this.customer = customer;
    }

    public String getInterGrate() {
        return interGrate;
    }

    public void setInterGrate(String interGrate) {
        this.interGrate = interGrate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActStatus() {
        return actStatus;
    }

    public void setActStatus(boolean actStatus) {
        this.actStatus = actStatus;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", userId='" + userId + '\'' +
                ", creator='" + creator + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", type='" + type + '\'' +
                ", resolution='" + resolution + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", diskSpace='" + diskSpace + '\'' +
                ", displayDriver='" + displayDriver + '\'' +
                ", boardType='" + boardType + '\'' +
                ", version='" + version + '\'' +
                ", sversion='" + sversion + '\'' +
                ", downloadStatus='" + downloadStatus + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", lastManualOpeTime='" + lastManualOpeTime + '\'' +
                ", lastPublishTime='" + lastPublishTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", lastSettingTime='" + lastSettingTime + '\'' +
                ", bandLimit='" + bandLimit + '\'' +
                ", customer=" + customer +
                ", interGrate='" + interGrate + '\'' +
                ", status='" + status + '\'' +
                ", actStatus=" + actStatus +
                '}';
    }
}
