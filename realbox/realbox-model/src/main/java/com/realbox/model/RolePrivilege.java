/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: RolePrivilege.java v 0.1 2017年12月03日 上午11:52 MJJ Exp $
 * 角色权限
 **/
public class RolePrivilege implements Serializable {

    private static final long serialVersionUID = 1359457733527718538L;

    // ID
    private String id;
    // 全部权限
    private int all;

    // 首页
    private int home;
    // 快速发布
    private int quickRelease;

    // 资源管理
    private int resourceManage;
    // 图片管理
    private int imageManage;
    // 图片上传
    private int updateImage;
    // 图片下载
    private int downloadImage;
    // 图片删除
    private int deleteImage;

    // 视频管理
    private int videoManage;
    // 视频上传
    private int uploadVideo;
    // 视频下载
    private int downloadVideo;
    // 视频删除

    // 终端管理
    private int terminalManages;
    // 终端管理
    private int terminalManage;
    // 同步终端
    private int syncTerminal;
    // 删除终端
    private int deleteTerminal;

    // 激活码管理
    private int activateManage;
    // 激活码生成
    private int createActivate;
    // 激活码批量生成
    private int batchActivate;
    // 激活码导出
    private int exportActivate;
    // 激活码解绑
    private int unbundledActivate;
    // 激活码删除
    private int deleteActivate;

    // 模板管理
    private int templateManage;
    // 新建模板
    private int addTemplate;
    // 修改模板
    private int updateTemplate;
    // 删除模板
    private int deleteTemplate;

    // 节目管理
    private int programManage;
    // 节目列表
    private int programList;
    // 新建节目
    private int addProgram;
    // 修改节目
    private int updateProgram;
    // 删除节目
    private int deleteProgram;

    // 发布管理
    private int publishManage;
    // 新建发布
    private int addPublish;
    // 删除发布
    private int deletePublish;
    // 节目预览
    private int programPreview;

    // 审核列表
    private int auditList;
    // 审核
    private int audit;

    // 日志管理
    private int logManage;
    // 用户日志
    private int userLog;
    // 用户日志导出
    private int exportUserLog;

    // 系统日志
    private int systemLog;
    // 系统日志导出
    private int exportSystemLog;

    // 用户管理
    private int userManage;
    // 终端设置
    private int terminalSetting;

    // 用户设置
    private int userSetting;
    // 新增用户
    private int addUser;
    // 编辑用户
    private int updateUser;
    // 删除用户
    private int deleteUser;

    // 角色设置
    private int roleSetting;
    // 新建角色
    private int addRole;
    // 编辑角色
    private int updateRole;
    // 删除角色
    private int deleteRole;

    public RolePrivilege(int number) {
        this.home = number;
        this.quickRelease = number;
        this.resourceManage = number;
        this.imageManage = number;
        this.updateImage = number;
        this.downloadImage = number;
        this.deleteImage = number;
        this.videoManage = number;
        this.uploadVideo = number;
        this.downloadVideo = number;
        this.terminalManages = number;
        this.terminalManage = number;
        this.syncTerminal = number;
        this.deleteTerminal = number;
        this.activateManage = number;
        this.createActivate = number;
        this.batchActivate = number;
        this.exportActivate = number;
        this.unbundledActivate = number;
        this.deleteActivate = number;
        this.templateManage = number;
        this.addTemplate = number;
        this.updateTemplate = number;
        this.deleteTemplate = number;
        this.programManage = number;
        this.programList = number;
        this.addProgram = number;
        this.updateProgram = number;
        this.deleteProgram = number;
        this.publishManage = number;
        this.addPublish = number;
        this.deletePublish = number;
        this.programPreview = number;
        this.auditList = number;
        this.audit = number;
        this.logManage = number;
        this.userLog = number;
        this.exportUserLog = number;
        this.systemLog = number;
        this.exportSystemLog = number;
        this.userManage = number;
        this.terminalSetting = number;
        this.userSetting = number;
        this.addUser = number;
        this.updateUser = number;
        this.deleteUser = number;
        this.roleSetting = number;
        this.addRole = number;
        this.updateRole = number;
        this.deleteRole = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getQuickRelease() {
        return quickRelease;
    }

    public void setQuickRelease(int quickRelease) {
        this.quickRelease = quickRelease;
    }

    public int getResourceManage() {
        return resourceManage;
    }

    public void setResourceManage(int resourceManage) {
        this.resourceManage = resourceManage;
    }

    public int getImageManage() {
        return imageManage;
    }

    public void setImageManage(int imageManage) {
        this.imageManage = imageManage;
    }

    public int getUpdateImage() {
        return updateImage;
    }

    public void setUpdateImage(int updateImage) {
        this.updateImage = updateImage;
    }

    public int getDownloadImage() {
        return downloadImage;
    }

    public void setDownloadImage(int downloadImage) {
        this.downloadImage = downloadImage;
    }

    public int getDeleteImage() {
        return deleteImage;
    }

    public void setDeleteImage(int deleteImage) {
        this.deleteImage = deleteImage;
    }

    public int getVideoManage() {
        return videoManage;
    }

    public void setVideoManage(int videoManage) {
        this.videoManage = videoManage;
    }

    public int getUploadVideo() {
        return uploadVideo;
    }

    public void setUploadVideo(int uploadVideo) {
        this.uploadVideo = uploadVideo;
    }

    public int getDownloadVideo() {
        return downloadVideo;
    }

    public void setDownloadVideo(int downloadVideo) {
        this.downloadVideo = downloadVideo;
    }

    public int getTerminalManages() {
        return terminalManages;
    }

    public void setTerminalManages(int terminalManages) {
        this.terminalManages = terminalManages;
    }

    public int getTerminalManage() {
        return terminalManage;
    }

    public void setTerminalManage(int terminalManage) {
        this.terminalManage = terminalManage;
    }

    public int getSyncTerminal() {
        return syncTerminal;
    }

    public void setSyncTerminal(int syncTerminal) {
        this.syncTerminal = syncTerminal;
    }

    public int getDeleteTerminal() {
        return deleteTerminal;
    }

    public void setDeleteTerminal(int deleteTerminal) {
        this.deleteTerminal = deleteTerminal;
    }

    public int getActivateManage() {
        return activateManage;
    }

    public void setActivateManage(int activateManage) {
        this.activateManage = activateManage;
    }

    public int getCreateActivate() {
        return createActivate;
    }

    public void setCreateActivate(int createActivate) {
        this.createActivate = createActivate;
    }

    public int getBatchActivate() {
        return batchActivate;
    }

    public void setBatchActivate(int batchActivate) {
        this.batchActivate = batchActivate;
    }

    public int getExportActivate() {
        return exportActivate;
    }

    public void setExportActivate(int exportActivate) {
        this.exportActivate = exportActivate;
    }

    public int getUnbundledActivate() {
        return unbundledActivate;
    }

    public void setUnbundledActivate(int unbundledActivate) {
        this.unbundledActivate = unbundledActivate;
    }

    public int getDeleteActivate() {
        return deleteActivate;
    }

    public void setDeleteActivate(int deleteActivate) {
        this.deleteActivate = deleteActivate;
    }

    public int getTemplateManage() {
        return templateManage;
    }

    public void setTemplateManage(int templateManage) {
        this.templateManage = templateManage;
    }

    public int getAddTemplate() {
        return addTemplate;
    }

    public void setAddTemplate(int addTemplate) {
        this.addTemplate = addTemplate;
    }

    public int getUpdateTemplate() {
        return updateTemplate;
    }

    public void setUpdateTemplate(int updateTemplate) {
        this.updateTemplate = updateTemplate;
    }

    public int getDeleteTemplate() {
        return deleteTemplate;
    }

    public void setDeleteTemplate(int deleteTemplate) {
        this.deleteTemplate = deleteTemplate;
    }

    public int getProgramManage() {
        return programManage;
    }

    public void setProgramManage(int programManage) {
        this.programManage = programManage;
    }

    public int getProgramList() {
        return programList;
    }

    public void setProgramList(int programList) {
        this.programList = programList;
    }

    public int getAddProgram() {
        return addProgram;
    }

    public void setAddProgram(int addProgram) {
        this.addProgram = addProgram;
    }

    public int getUpdateProgram() {
        return updateProgram;
    }

    public void setUpdateProgram(int updateProgram) {
        this.updateProgram = updateProgram;
    }

    public int getDeleteProgram() {
        return deleteProgram;
    }

    public void setDeleteProgram(int deleteProgram) {
        this.deleteProgram = deleteProgram;
    }

    public int getPublishManage() {
        return publishManage;
    }

    public void setPublishManage(int publishManage) {
        this.publishManage = publishManage;
    }

    public int getAddPublish() {
        return addPublish;
    }

    public void setAddPublish(int addPublish) {
        this.addPublish = addPublish;
    }

    public int getDeletePublish() {
        return deletePublish;
    }

    public void setDeletePublish(int deletePublish) {
        this.deletePublish = deletePublish;
    }

    public int getProgramPreview() {
        return programPreview;
    }

    public void setProgramPreview(int programPreview) {
        this.programPreview = programPreview;
    }

    public int getAuditList() {
        return auditList;
    }

    public void setAuditList(int auditList) {
        this.auditList = auditList;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public int getLogManage() {
        return logManage;
    }

    public void setLogManage(int logManage) {
        this.logManage = logManage;
    }

    public int getUserLog() {
        return userLog;
    }

    public void setUserLog(int userLog) {
        this.userLog = userLog;
    }

    public int getExportUserLog() {
        return exportUserLog;
    }

    public void setExportUserLog(int exportUserLog) {
        this.exportUserLog = exportUserLog;
    }

    public int getSystemLog() {
        return systemLog;
    }

    public void setSystemLog(int systemLog) {
        this.systemLog = systemLog;
    }

    public int getExportSystemLog() {
        return exportSystemLog;
    }

    public void setExportSystemLog(int exportSystemLog) {
        this.exportSystemLog = exportSystemLog;
    }

    public int getUserManage() {
        return userManage;
    }

    public void setUserManage(int userManage) {
        this.userManage = userManage;
    }

    public int getTerminalSetting() {
        return terminalSetting;
    }

    public void setTerminalSetting(int terminalSetting) {
        this.terminalSetting = terminalSetting;
    }

    public int getUserSetting() {
        return userSetting;
    }

    public void setUserSetting(int userSetting) {
        this.userSetting = userSetting;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
        this.addUser = addUser;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(int deleteUser) {
        this.deleteUser = deleteUser;
    }

    public int getRoleSetting() {
        return roleSetting;
    }

    public void setRoleSetting(int roleSetting) {
        this.roleSetting = roleSetting;
    }

    public int getAddRole() {
        return addRole;
    }

    public void setAddRole(int addRole) {
        this.addRole = addRole;
    }

    public int getUpdateRole() {
        return updateRole;
    }

    public void setUpdateRole(int updateRole) {
        this.updateRole = updateRole;
    }

    public int getDeleteRole() {
        return deleteRole;
    }

    public void setDeleteRole(int deleteRole) {
        this.deleteRole = deleteRole;
    }
}
