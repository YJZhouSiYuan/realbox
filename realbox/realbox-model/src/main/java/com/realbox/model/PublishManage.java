/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: PublishManage.java v 0.1 2017年12月31日 20:22 MJJ Exp $
 **/
public class PublishManage implements Serializable {

    private static final long serialVersionUID = 7959611284270939082L;

    // ID(发布单号)
    private String id;
    // 节目ID
    private String proId;
    // 节目名称
    private String proName;
    // 节目类型
    private String proType;
    // 节目状态
    private String proStatus;
    // 终端ID
    private String terminalId;
    // 发布人
    private String publisher;
    // 发布状态
    private String status;
    // 发布策略
    private String disStrategy;
    // 发布类型
    private String disType;
    // 发布时间
    private String dateTime;
    // 预约时间
    private String makeTime;
    // 失效时期
    private String invalidTime;
    // 播放模式
    private String playMode;
    // 播放时间类型
    private String type;
    // 日期(1、2、3、4、5、6、0 或 yyyy-mm-dd yyyy-mm-dd)
    private String date;
    // 时间(00:00:00 00:00:00)
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisStrategy() {
        return disStrategy;
    }

    public void setDisStrategy(String disStrategy) {
        this.disStrategy = disStrategy;
    }

    public String getDisType() {
        return disType;
    }

    public void setDisType(String disType) {
        this.disType = disType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getPlayMode() {
        return playMode;
    }

    public void setPlayMode(String playMode) {
        this.playMode = playMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}



