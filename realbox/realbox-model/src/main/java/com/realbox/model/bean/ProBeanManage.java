/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.bean;

import com.alibaba.fastjson.JSON;
import com.realbox.model.bean.program.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MJJ
 * @create Id: ProBeanManage.java v 0.1 2017年12月31日 2:49 MJJ Exp $
 **/
public class ProBeanManage {

    // 实体
    private ProgramBean bean;
    // 信息
    private InformationBean information;
    // 任务
    private AdvTaskBean AdvTask;
    // 属性
    private PropertyBean property;
    // 节目数据
    private DataBean data;
    // 节目列表
    private ProgrammeListBean programmeList;
    // 节目数据
    private List<ProgrammesDataBean> programmesData;
    // 节目资源
    private  List<ResourcesBean> resources = new ArrayList<ResourcesBean>();
    // 节目资源集合
    private List<ResListBean> resList = new ArrayList<ResListBean>();
    // 节目元素
    private List<ItemsBean> items = new ArrayList<ItemsBean>();
    // 元素数据实体
    private DataEntityBean dataEntity;
    // 尺寸控制
    private GeometryBean geometry;
    // 节目的发布属性
    private DistributePropertyBean distributeProperty;
    // 播放时间范围
    private PlayTimeRangeBean playTimeRange;
    // 时间
    private DatetimeBean datetime;

    // JSON转实体
    public ProBeanManage(String json) {
        ProgramBean bean = JSON.parseObject(json, ProgramBean.class);
        setBeans(bean);
    }

    // 设置所有实体参数
    public void setBeans(ProgramBean bean) {
        this.bean = bean;
        this.information = this.bean.getInformation();
        this.AdvTask = this.information.getAdvTask();
        this.property = this.AdvTask.getProperty();
        this.data = this.AdvTask.getData();
        this.programmeList = this.data.getProgrammeList();
        this.programmesData = this.programmeList.getProgrammesData();
        for (ProgrammesDataBean programData : this.programmesData) {
            this.resources.addAll(programData.getResources());
            this.items.addAll(programData.getItems());
            for (ItemsBean items : this.items) {
                this.dataEntity = items.getDataEntity();
                this.geometry = items.getGeometry();
            }
        }
        for (ResourcesBean resources : this.getResources()) {
            this.resList.addAll(resources.getResList());
        }
        this.distributeProperty = this.data.getDistributeProperty();
        this.playTimeRange = this.distributeProperty.getPlayTimeRange();
        this.datetime = this.playTimeRange.getDatetime();
    }

    public ProgramBean getBean() {
        return bean;
    }

    public void setBean(ProgramBean bean) {
        this.bean = bean;
    }

    public InformationBean getInformation() {
        return information;
    }

    public void setInformation(InformationBean information) {
        this.information = information;
    }

    public AdvTaskBean getAdvTask() {
        return AdvTask;
    }

    public void setAdvTask(AdvTaskBean advTask) {
        AdvTask = advTask;
    }

    public PropertyBean getProperty() {
        return property;
    }

    public void setProperty(PropertyBean property) {
        this.property = property;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ProgrammeListBean getProgrammeList() {
        return programmeList;
    }

    public void setProgrammeList(ProgrammeListBean programmeList) {
        this.programmeList = programmeList;
    }

    public List<ProgrammesDataBean> getProgrammesData() {
        return programmesData;
    }

    public void setProgrammesData(List<ProgrammesDataBean> programmesData) {
        this.programmesData = programmesData;
    }

    public List<ResourcesBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesBean> resources) {
        this.resources = resources;
    }

    public List<ResListBean> getResList() {
        return resList;
    }

    public void setResList(List<ResListBean> resList) {
        this.resList = resList;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public DataEntityBean getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(DataEntityBean dataEntity) {
        this.dataEntity = dataEntity;
    }

    public GeometryBean getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryBean geometry) {
        this.geometry = geometry;
    }

    public DistributePropertyBean getDistributeProperty() {
        return distributeProperty;
    }

    public void setDistributeProperty(DistributePropertyBean distributeProperty) {
        this.distributeProperty = distributeProperty;
    }

    public PlayTimeRangeBean getPlayTimeRange() {
        return playTimeRange;
    }

    public void setPlayTimeRange(PlayTimeRangeBean playTimeRange) {
        this.playTimeRange = playTimeRange;
    }

    public DatetimeBean getDatetime() {
        return datetime;
    }

    public void setDatetime(DatetimeBean datetime) {
        this.datetime = datetime;
    }
}
