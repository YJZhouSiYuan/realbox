/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.alibaba.fastjson.JSON;
import com.realbox.common.utils.DateUtils;
import com.realbox.model.*;
import com.realbox.model.bean.program.*;
import com.realbox.repository.mongodb.mapper.ProItemsMapper;
import com.realbox.repository.mongodb.mapper.ResourceMapper;
import com.realbox.repository.mongodb.mapper.TemItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author MJJ
 * @create Id: PublishUtils.java v 0.1 2018年01月14日 1:53 MJJ Exp $
 **/
@Component
public class PublishUtils {

    @Autowired
    private TemItemsMapper temItemsMapper;

    @Autowired
    private ProItemsMapper proItemsMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 发布节目数据处理
     *
     * @param publish 发布对象
     * @param program 节目对象
     * @return
     */
    public String getPublishData(PublishManage publish, Program program) {
        List<ProgrammesDataBean> programDatas = new ArrayList<ProgrammesDataBean>();
        List<ResourcesBean> resources = new ArrayList<ResourcesBean>();
        List<ItemsBean> items = new ArrayList<ItemsBean>();
        iteratorData(program, resources, items);
        setProgrammesDataBean(program, items, resources, programDatas);
        DistributePropertyBean distribute = getDistributePropertyBean(publish);
        ProgrammeListBean programList = getProgrammeListBean(publish, programDatas);
        PropertyBean property = getPropertyBean();
        DataBean data = getDataBean(programList, distribute);
        AdvTaskBean task = getAdvTaskBean(data, property);
        InformationBean information = getInformationBean(task);
        ProgramBean bean = getProgramBean(information);
        return JSON.toJSONString(bean);
    }

    private ProgramBean getProgramBean(InformationBean information) {
        ProgramBean bean = new ProgramBean();
        bean.setUser("45364442534536444253453644425332");
        bean.setHead("REALTOPTV_WebCommunicationProtocol_V1.0");
        bean.setLevel(0);
        bean.setServerId("REALTOPTV_InformationPublishSystem_074532010");
        bean.setPublishTime(DateUtils.getDateTime());
        bean.setDescription("Advertising");
        bean.setInformation(information);
        return bean;
    }

    private InformationBean getInformationBean(AdvTaskBean task) {
        InformationBean information = new InformationBean();
        information.setAdvTask(task);
        return information;
    }

    private AdvTaskBean getAdvTaskBean(DataBean data, PropertyBean property) {
        AdvTaskBean task = new AdvTaskBean();
        task.setData(data);
        task.setProperty(property);
        return task;
    }

    private DataBean getDataBean(ProgrammeListBean programList, DistributePropertyBean distribute) {
        DataBean data = new DataBean();
        data.setMethod("add");
        data.setProgrammeList(programList);
        data.setDistributeProperty(distribute);
        return data;
    }

    private PropertyBean getPropertyBean() {
        PropertyBean property = new PropertyBean();
        property.setTaskId(UUID.randomUUID().toString());
        property.setReplyId("45678");
        property.setTaskKind("notify");
        property.setTaskName("programmeDistribute");
        return property;
    }

    private DistributePropertyBean getDistributePropertyBean(PublishManage publish) {
        DistributePropertyBean distribute = new DistributePropertyBean();
        distribute.setDisType(publish.getDisType());            // 发布类型
        distribute.setDisStrategy(publish.getDisStrategy());    // 发布策略
        distribute.setInvalidTime(publish.getInvalidTime());    // 失效日期
        distribute.setPlayMode(publish.getPlayMode());          // 播放模式
        PlayTimeRangeBean range = new PlayTimeRangeBean();
        // 定时播放
        if ("time".equals(publish.getPlayMode())) {
            range.setType(publish.getType());
            // 设置播放时间
            DatetimeBean dateTime = new DatetimeBean();
            dateTime.setDate(publish.getDate());                // 日期(1、2、3、4、5、6、0 或 yyyy-mm-dd yyyy-mm-dd)
            dateTime.setTime(publish.getTime());                // 时间(00:00:00 00:00:00)
            range.setDatetime(dateTime);
        }
        distribute.setPlayTimeRange(range);                     // 播放时间类型
        return distribute;
    }

    private ProgrammeListBean getProgrammeListBean(PublishManage publish, List<ProgrammesDataBean> programDatas) {
        ProgrammeListBean programList = new ProgrammeListBean();
        // 发布ID
        programList.setId(publish.getId());
        programList.setProgrammesData(programDatas);
        return programList;
    }

    private void setProgrammesDataBean(Program program, List<ItemsBean> items, List<ResourcesBean> resources, List<ProgrammesDataBean> programDatas) {
        ProgrammesDataBean programData = new ProgrammesDataBean();
        // 节目ID
        programData.setId(program.getId());
        programData.setItems(items);
        programData.setResources(resources);
        programDatas.add(programData);
    }

    // 遍历节目数据
    private void iteratorData(Program program, List<ResourcesBean> resources, List<ItemsBean> items) {
        List<ProItems> proItems = proItemsMapper.batchQueryById(program.getElemIds(), ProItems.class);
        for (ProItems pro : proItems) {
            // 元素
            TemItems tem = temItemsMapper.queryById(pro.getItemsId(), TemItems.class);
            GeometryBean geometry = new GeometryBean();
            geometry.setX(tem.getX());
            geometry.setY(tem.getY());
            geometry.setWidth(tem.getWidth());
            geometry.setHeight(tem.getHeight());

            DataEntityBean entity = new DataEntityBean();
            if ("image".equals(tem.getType())) {
                if (!StringUtils.isEmpty(pro.getImagePlayTime())) {
                    if (pro.getImagePlayTime() < 1) {
                        pro.setImagePlayTime(10);
                    }
                    entity.setImagePlayTime(pro.getImagePlayTime());
                }
            }

            if ("scroll".equals(tem.getType())) {
                entity.setScrollSpeed(pro.getScrollSpeed());
                entity.setScrollFontSize(pro.getScrollFontSize());
                entity.setScrollDuration(pro.getScrollDuration());
                entity.setScrollDirection(pro.getScrollDirection());
                entity.setScrollColor(pro.getScrollColor());
                entity.setScrollFontFamily(pro.getScrollFontFamily());
                entity.setScrollFontTransparency(pro.getScrollFontTransparency());
                entity.setScrollBGTransparency(pro.getScrollBGTransparency());
                entity.setBgColor(pro.getScrollBGColor());
            }

            if ("txt".equals(tem.getType())) {
                entity.setAlign(pro.getAlign());
                entity.setBold(pro.getBold());
                entity.setFont(pro.getFont());
                entity.setFontColor(pro.getFontColor());
                entity.setItalic(pro.getItalic());
                entity.setFontSize(pro.getFontSize());
            }

            ItemsBean item = new ItemsBean();
            // 元素ID
            item.setId(pro.getId());
            item.setItemType(tem.getType());
            item.setDataEntity(entity);
            item.setGeometry(geometry);
            items.add(item);

            // 资源
            List<ResListBean> resLists = new ArrayList<ResListBean>();
            ResListBean resList = new ResListBean();
            Resource resourceList = resourceMapper.queryById(pro.getResId(), Resource.class);
            if (resourceList != null) {
                resList.setName(resourceList.getName());
                resList.setType(resourceList.getType());
                resList.setUrl(resourceList.getUrl());
                resList.setProtocol(resourceList.getProtocol());
                resLists.add(resList);
            }
            if ("scroll".equals(tem.getType()) || "txt".equals(tem.getType())) {
                resList.setName("");
                resList.setType(tem.getType());
                resList.setUrl(pro.getUrl());
                resList.setProtocol("http");
                resLists.add(resList);
            }

            ResourcesBean resource = new ResourcesBean();
            // 匹配元素ID
            resource.setItemId(pro.getId());
            resource.setResList(resLists);
            resources.add(resource);
        }
    }
}
