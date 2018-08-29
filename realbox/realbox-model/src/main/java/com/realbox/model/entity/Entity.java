/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author MJJ
 * @create Id: Entity.java v 0.1 2018年01月09日 22:39 MJJ Exp $
 **/
public class Entity implements Serializable {

    private static final long serialVersionUID = -6595642255557217831L;

    private List<TemplateEntity> entity;

    public List<TemplateEntity> getEntity() {
        return entity;
    }

    public void setEntity(List<TemplateEntity> entity) {
        this.entity = entity;
    }
}
