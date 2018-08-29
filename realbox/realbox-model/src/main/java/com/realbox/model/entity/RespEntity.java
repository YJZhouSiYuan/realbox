/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.entity;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: RespEntity.java v 0.1 2017年11月04日 上午4:00 MJJ Exp $
 **/
public class RespEntity<T> implements Serializable {
    private static final long serialVersionUID = 7739979424806069482L;

    // 响应编码
    private String code;
    // 响应信息
    private String infor;
    // 请求人ID
    private String userId;
    // 响应数据
    private T cust;

    public RespEntity() {
        this.code = "";
        this.infor = "";
        this.userId = " ";
    }

    public RespEntity(String code) {
        this.code = (code == null ? "" : code);
        this.infor = "";
        this.userId = " ";
    }

    public RespEntity(String code, String infor) {
        this.code = (code == null ? "" : code);
        this.infor = (infor == null ? "" : infor);
        this.userId = " ";
    }

    public RespEntity(String code, String infor, T cust) {
        this.code = (code == null ? "" : code);
        this.infor = (infor == null ? "" : infor);
        this.userId = " ";
        this.cust = cust;
    }

    public RespEntity(String code, String infor, String userId, T cust) {
        this.code = (code == null ? "" : code);
        this.infor = (infor == null ? "" : infor);
        this.userId = (userId == null ? " " : userId);
        this.cust = cust;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public T getCust() {
        return cust;
    }

    public void setCust(T cust) {
        this.cust = cust;
    }
}
