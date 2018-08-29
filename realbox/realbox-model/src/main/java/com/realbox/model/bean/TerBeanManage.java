/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.bean;

import com.alibaba.fastjson.JSON;
import com.realbox.model.bean.terminal.*;

/**
 * @author MJJ
 * @create Id: TerBeanManage.java v 0.1 2017年12月30日 2:35 MJJ Exp $
 **/
public class TerBeanManage {

    // 实体类
    private AdvBean bean;
    // 信息
    private AdvInformationBean information;
    // 任务
    private AdvAdvtask AdvTask;
    // 属性
    private AdvPropertyBean property;
    // 终端数据
    private AdvDataBean data;
    //
    private AdvCustomerBean customer;
    // 颜色
    private AdvColorBean color;
    // 声音
    private AdvVolumeBean volumne;
    // 系统
    private AdvSystemBean system;
    //
    private AdvStTimeBean sttime;

    public TerBeanManage() { }

    // JSON转实体
    public TerBeanManage(String json) {
        AdvBean bean = JSON.parseObject(json, AdvBean.class);
        setBeans(bean);
    }

    // 设置所有实体参数
    private void setBeans(AdvBean bean) {
        this.bean = bean;
        this.information = this.bean.getInformation();
        this.AdvTask = this.information.getAdvTask();
        this.property = this.AdvTask.getProperty();
        this.data = this.AdvTask.getData();
        this.customer = this.data.getCustomer();
        this.color = this.customer.getColor();
        this.volumne = this.customer.getVolumne();
        this.system = this.customer.getSystem();
        this.sttime = this.customer.getSttime();
    }

    public AdvBean getBean() {
        return bean;
    }

    public void setBean(AdvBean bean) {
        this.bean = bean;
    }

    public AdvInformationBean getInformation() {
        return information;
    }

    public void setInformation(AdvInformationBean information) {
        this.information = information;
    }

    public AdvAdvtask getAdvTask() {
        return AdvTask;
    }

    public void setAdvTask(AdvAdvtask advTask) {
        AdvTask = advTask;
    }

    public AdvPropertyBean getProperty() {
        return property;
    }

    public void setProperty(AdvPropertyBean property) {
        this.property = property;
    }

    public AdvDataBean getData() {
        return data;
    }

    public void setData(AdvDataBean data) {
        this.data = data;
    }

    public AdvCustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(AdvCustomerBean customer) {
        this.customer = customer;
    }

    public AdvColorBean getColor() {
        return color;
    }

    public void setColor(AdvColorBean color) {
        this.color = color;
    }

    public AdvVolumeBean getVolumne() {
        return volumne;
    }

    public void setVolumne(AdvVolumeBean volumne) {
        this.volumne = volumne;
    }

    public AdvSystemBean getSystem() {
        return system;
    }

    public void setSystem(AdvSystemBean system) {
        this.system = system;
    }

    public AdvStTimeBean getSttime() {
        return sttime;
    }

    public void setSttime(AdvStTimeBean sttime) {
        this.sttime = sttime;
    }
}
