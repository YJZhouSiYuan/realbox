/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.alibaba.fastjson.JSON;
import com.realbox.common.utils.DateUtils;
import com.realbox.model.bean.terminal.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author MJJ
 * @create Id: TerminalUtils.java v 0.1 2018年01月14日 23:33 MJJ Exp $
 **/
@Component
public class TerminalUtils {

    /**
     *
     *
     * @param programs
     * @return
     */
    public String getPublishData(Map.Entry<String, List<String>> programs) {
        AdvPropertyBean property = getAdvPropertyBean();
        AdvDataBean data = getAdvDataBean(programs);
        AdvAdvtask task = getAdvAdvtask(data, property);
        AdvInformationBean information = getAdvInformationBean(task);
        AdvBean bean = getAdvBean(information);
        return JSON.toJSONString(bean);
    }

    private AdvDataBean getAdvDataBean(Map.Entry<String, List<String>> programs) {
        AdvDataBean data = new AdvDataBean();
        data.setProgrammelistbean(programs.getValue());
        data.setMethod("delete");
        return data;
    }

    private AdvPropertyBean getAdvPropertyBean() {
        AdvPropertyBean property = new AdvPropertyBean();
        property.setTaskId(UUID.randomUUID().toString());
        property.setTaskKind("notify");
        property.setTaskName("programmeDistribute");
        return property;
    }

    private AdvAdvtask getAdvAdvtask(AdvDataBean data, AdvPropertyBean property) {
        AdvAdvtask task = new AdvAdvtask();
        task.setData(data);
        task.setProperty(property);
        return task;
    }

    private AdvInformationBean getAdvInformationBean(AdvAdvtask task) {
        AdvInformationBean information = new AdvInformationBean();
        information.setAdvTask(task);
        return information;
    }

    private AdvBean getAdvBean(AdvInformationBean information) {
        AdvBean bean = new AdvBean();
        bean.setHead("REALTOPTV_WebCommunicationProtocol_V1.0");
        bean.setServerId("REALTOPTV_InformationPublishSystem_074532010");
        bean.setPublishTime(DateUtils.getDateTime());
        bean.setDescription("Advertising");
        bean.setLevel(1);
        bean.setUser("45364442534536444253453644425332");
        bean.setInformation(information);
        return bean;
    }
}
