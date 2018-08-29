package com.realbox.model.bean.terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zph on 2017/12/28 0028.
 */
public class AdvDataBean {
    private String updatetime;
    private String displaydriver;
    private String termialCode;
    private String bandlimit;
    private String mac;
    private String resolution;
    private AdvCustomerBean customer;
    private String type;
    private String intergrate;
    private String lastManualOpetime;
    private String lastpublishtime;
    private String version;
    private String creator;
    private String ip;
    private String lastupdatetime;
    private String boardtype;
    private String downloadstatus;
    private String lastsettingtime;
    private String sversion;
    private String diskspace;
    private List<String> programmelistbean = new ArrayList<String>();
    private List<String> programmeid = new ArrayList<String>();
    private String method;
    private String errCode;
    private String errMsg;

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public void setDisplaydriver(String displaydriver) {
        this.displaydriver = displaydriver;
    }

    public void setTermialCode(String termialCode) {
        this.termialCode = termialCode;
    }

    public void setBandlimit(String bandlimit) {
        this.bandlimit = bandlimit;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIntergrate(String intergrate) {
        this.intergrate = intergrate;
    }

    public void setLastManualOpetime(String lastManualOpetime) {
        this.lastManualOpetime = lastManualOpetime;
    }

    public void setLastpublishtime(String lastpublishtime) {
        this.lastpublishtime = lastpublishtime;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public void setBoardtype(String boardtype) {
        this.boardtype = boardtype;
    }

    public void setDownloadstatus(String downloadstatus) {
        this.downloadstatus = downloadstatus;
    }

    public void setLastsettingtime(String lastsettingtime) {
        this.lastsettingtime = lastsettingtime;
    }

    public void setSversion(String sversion) {
        this.sversion = sversion;
    }

    public void setDiskspace(String diskspace) {
        this.diskspace = diskspace;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public String getDisplaydriver() {
        return displaydriver;
    }

    public String getTermialCode() {
        return termialCode;
    }

    public String getBandlimit() {
        return bandlimit;
    }

    public String getMac() {
        return mac;
    }

    public String getResolution() {
        return resolution;
    }

    public String getType() {
        return type;
    }

    public String getIntergrate() {
        return intergrate;
    }

    public String getLastManualOpetime() {
        return lastManualOpetime;
    }

    public String getLastpublishtime() {
        return lastpublishtime;
    }

    public String getVersion() {
        return version;
    }

    public String getCreator() {
        return creator;
    }

    public String getIp() {
        return ip;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public String getBoardtype() {
        return boardtype;
    }

    public String getDownloadstatus() {
        return downloadstatus;
    }

    public String getLastsettingtime() {
        return lastsettingtime;
    }

    public String getSversion() {
        return sversion;
    }

    public String getDiskspace() {
        return diskspace;
    }

    public AdvCustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(AdvCustomerBean customer) {
        this.customer = customer;
    }

    public List<String> getProgrammelistbean() {
        return programmelistbean;
    }

    public void setProgrammelistbean(List<String> programmelistbean) {
        this.programmelistbean = programmelistbean;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<String> getProgrammeid() {
        return programmeid;
    }

    public void setProgrammeid(List<String> programmeid) {
        this.programmeid = programmeid;
    }
}
