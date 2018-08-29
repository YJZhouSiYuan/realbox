/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.enums;

/**
 * @author MJJ
 * @create Id: RealBox.java v 0.1 2017年11月04日 上午3:57 MJJ Exp $
 **/
public enum RealBox {

    // 系统设置
    LAN000("LAN000", "系统语言不能为空"),

    // Token
    TOKEN000("TOKEN000", "Token失效"),

    // 登录
    LOGIN002("LOGIN002", "用户名或密码不正确"),
    LOGIN001("LOGIN001", "用户验证码不正确"),
    LOGIN000("LOGIN000", "用户验证码不能为空"),

    // 所属部门
    DEPT001("DEPT001", "所属部门不存在"),
    DEPT000("DEPT000", "所属部门ID不能为空"),

    // 结构树
    TREE010("TREE010", "旧父结构树不存在"),
    TREE009("TREE009", "新父结构树不存在"),
    TREE008("TREE008", "结构树不存在"),
    TREE007("TREE007", "结构树更新人"),
    TREE006("TREE006", "分组不能操作"),
    TREE005("TREE005", "结构树名已存在"),
    TREE004("TREE004", "父结构树不存在"),
    TREE003("TREE003", "父结构树ID不能为空"),
    TREE002("TREE002", "结构树创建人不能为空"),
    TREE001("TREE001", "结构树名不能为空"),
    TREE000("TREE000", "结构树ID不能为空"),

    // 发布管理
    PUBLISH009("PUBLISH009", "审核类型不能为空"),
    PUBLISH008("PUBLISH008", "发布节目管理不存在"),
    PUBLISH007("PUBLISH007", "失效时间不能小于结束时间"),
    PUBLISH006("PUBLISH006", "结束时间不能为空"),
    PUBLISH005("PUBLISH005", "开始时间不能为空"),
    PUBLISH004("PUBLISH004", "失效时间不能为空"),
    PUBLISH003("PUBLISH003", "播放类型不能为空"),
    PUBLISH002("PUBLISH002", "发布类型不能为空"),
    PUBLISH001("PUBLISH001", "发布人不能为空"),
    PUBLISH000("PUBLISH000", "发布ID不能为空"),

    // 激活码
    ACTIV005("ACTIV005", "激活码创建人不能为空"),
    ACTIV004("ACTIV004", "激活码已使用"),
    ACTIV003("ACTIV003", "激活码不存在"),
    ACTIV002("ACTIV002", "激活码不能为空"),
    ACTIV001("ACTIV001", "生成数量不能为空"),
    ACTIV000("ACTIV000", "激活码ID不能为空"),

    // 终端树
    TRETER001("TRETER001", "终端树不存在"),
    TRETER000("TRETER000", "终端树ID不能为空"),

    // 终端
    TERMINAL004("TERMINAL004", "终端未激活"),
    TERMINAL003("TERMINAL003", "终端不存在"),
    TERMINAL002("TERMINAL002", "终端类型不能为空"),
    TERMINAL001("TERMINAL001", "终端名不能为空"),
    TERMINAL000("TERMINAL000", "终端ID不能为空"),

    // 节目列表树
    TREPRO001("TREPRO001", "节目列表树不存在"),
    TREPRO000("TREPRO000", "节目列表树ID不能为空"),

    // 节目管理
    PROGRAM008("PROGRAM008", "节目类型不存在"),
    PROGRAM007("PROGRAM007", "节目不存在"),
    PROGRAM006("PROGRAM006", "节目名已存在"),
    PROGRAM005("PROGRAM005", "节目播放时长不能为空"),
    PROGRAM004("PROGRAM004", "节目状态不能为空"),
    PROGRAM003("PROGRAM003", "节目元素不能为空"),
    PROGRAM002("PROGRAM002", "节目预览图不能为空"),
    PROGRAM001("PROGRAM001", "节目名称不能为空"),
    PROGRAM000("PROGRAM000", "节目ID不能为空"),

    // 模板树
    TRETEM001("TRETEM001", "模板树不存在"),
    TRETEM000("TRETEM000", "模板树ID不能为空"),

    // 模板类型
    TEMPLATE012("TEMPLATE012", "模板不存在"),
    TEMPLATE011("TEMPLATE011", "模板名已存在"),
    TEMPLATE010("TEMPLATE0010", "模板不能删除"),
    TEMPLATE009("TEMPLATE009", "模板不能编辑"),
    TEMPLATE008("TEMPLATE008", "模板原代码不能空"),
    TEMPLATE007("TEMPLATE007", "模板元素不能为空"),
    TEMPLATE006("TEMPLATE006", "模板宽不能为空"),
    TEMPLATE005("TEMPLATE005", "模板高不能为空"),
    TEMPLATE004("TEMPLATE004", "模板类型不能为空"),
    TEMPLATE003("TEMPLATE003", "模板创建人不能为空"),
    TEMPLATE002("TEMPLATE002", "模板预览图不能为空"),
    TEMPLATE001("TEMPLATE001", "模板名不能为空"),
    TEMPLATE000("TEMPLATE000", "模板ID不能为空"),

    // 资源树
    TRERES001("TRERES001", "资源树不存"),
    TRERES000("TRERES000", "资源树ID不能为空"),

    // 资源管理
    RESOURCE005("RESOURCE005", "资源名已存在"),
    RESOURCE004("RESOURCE004", "资源类型不支持"),
    RESOURCE003("RESOURCE003", "资源类型不能为空"),
    RESOURCE002("RESOURCE002", "资源不存在"),
    RESOURCE001("RESOURCE001", "资源创建人ID不能为空"),
    RESOURCE000("RESOURCE000", "资源ID不能为空"),

    // 权限
    PRIV002("PRIV002", "登录角色权限ID不能为空"),
    PRIV001("PRIV001", "权限不存在"),
    PRIV000("PRIV000", "权限ID不能为空"),

    // 角色
    ROLE004("ROLE004", "角色名已存在"),
    ROLE003("ROLE003", "角色创建人不能为空"),
    ROLE002("ROLE002", "角色不存在"),
    ROLE001("ROLE001", "角色名不能为空"),
    ROLE000("ROLE000", "角色ID不能为空"),

    // 用户树
    GROUP001("GROUP001", "用户树不存在"),
    GROUP000("GROUP000", "用户树ID不能为空"),

    // 用户
    USER007("USER007", "用户语言不能为空"),
    USER006("USER006", "超级管理员不能删除"),
    USER005("USER005", "用户不存在"),
    USER004("USER004", "用户已存在"),
    USER003("USER003", "用户创建人不能为空"),
    USER002("USER002", "用户密码不能为空"),
    USER001("USER001", "用户名不能为空"),
    USER000("USER000", "用户ID不能为空"),

    // 分页
    PAGE001("PAGE001", "页面显示数量不能为空"),
    PAGE000("PAGE000", "当前页数不能为空");

    private String key;
    private String value;

    RealBox(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
