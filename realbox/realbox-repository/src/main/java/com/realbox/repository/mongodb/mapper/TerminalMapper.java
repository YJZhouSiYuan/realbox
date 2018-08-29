/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.Template;
import com.realbox.model.Terminal;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TerminalMapper.java v 0.1 2017年12月26日 11:39 MJJ Exp $
 **/
@Repository
public class TerminalMapper extends MongoDao<Terminal> {

    /**
     * 修改(ID)
     *
     * @param id       终端ID
     * @param terminal 终端对象
     */
    public void update(String id, Terminal terminal) {
        super.update(id, getUpdate(terminal), Terminal.class);
    }

    /**
     * 获取修改参数
     *
     * @param terminal 终端对象
     * @return
     */
    private Map<String, Object> getUpdate(Terminal terminal) {
        // 实体转Map
        return BeanUtils.beanToMap(terminal);
    }
}
