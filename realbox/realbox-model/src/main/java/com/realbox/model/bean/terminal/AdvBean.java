package com.realbox.model.bean.terminal;

/**
 * Created by zph on 2017/12/28 0028.
 */
public class AdvBean {
    private AdvInformationBean information;
    private int level;
    private String publishTime;
    private String description;
    private String serverId;
    private String head;
    private String user;

    public AdvInformationBean getInformation() {
        return information;
    }

    public void setInformation(AdvInformationBean information) {
        this.information = information;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getDescription() {
        return description;
    }

    public String getServerId() {
        return serverId;
    }

    public String getHead() {
        return head;
    }

    public String getUser() {
        return user;
    }


}
