package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class ProgramBean {
    private InformationBean information;
    private int level;
    private String publishTime;
    private String description;
    private String serverId;
    private String head;
    private String user;

    public void setInformation(InformationBean information) {
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

    public InformationBean getInformation() {
        return information;
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
