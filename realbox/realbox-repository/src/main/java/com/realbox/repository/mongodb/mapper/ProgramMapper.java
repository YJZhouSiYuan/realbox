/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.common.utils.BeanUtils;
import com.realbox.model.Program;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: ProgramMapper.java v 0.1 2017年12月20日 21:02 MJJ Exp $
 **/
@Repository
public class ProgramMapper extends MongoDao<Program> {

    /**
     * 修改(ID)
     *
     * @param id      ID
     * @param program 节目对象
     */
    public void update(String id, Program program) {
        // 修改
        super.update(id, getUpdate(program), Program.class);
    }

    /**
     * 获取修改参数
     *
     * @param program 节目对象
     * @return
     */
    private Map<String, Object> getUpdate(Program program) {
        // 设置修改参数
        return BeanUtils.beanToMap(program);
    }
}
