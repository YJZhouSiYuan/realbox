/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.mapper;

import com.realbox.model.Tree;
import com.realbox.repository.mongodb.dao.impl.MongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: TreeMapper.java v 0.1 2017年12月16日 19:48 MJJ Exp $
 **/
@Repository
public class TreeMapper extends MongoDao<Tree> {

    @Autowired
    private MongoOperations operations;

    /**
     * 查询(名称、 批量)
     *
     * @param names name(List集合)
     * @return
     */
    public List<Tree> batchQueryByProperty(List<String> names) {
        // 查询
        return operations.find(new Query(Criteria.where("Property").in(names)), Tree.class);
    }

    /**
     * 修改
     *
     * @param cond 条件
     * @param tree 结构树对象
     */
    public void update(Map<String, Object> cond, Tree tree) {
        // 修改
        super.update(cond, getUpdate(tree), Tree.class);
    }

    /**
     * 修改(ID)
     *
     * @param id   ID
     * @param tree 结构树对象
     */
    public void update(String id, Tree tree) {
        // 修改
        super.update(id, getUpdate(tree), Tree.class);
    }

    /**
     * 设置修改参数
     *
     * @param tree 结构树对象
     * @return
     */
    private Map<String, Object> getUpdate(Tree tree) {
        Map<String, Object> entity = new HashMap<String, Object>();
        // 权限名
        entity.put("label", tree.getLabel());
        // 创建人
        if (!StringUtils.isEmpty(tree.getCreator())) {
            entity.put("creator", tree.getCreator());
        }
        // 图标地址
        if (!StringUtils.isEmpty(tree.getIconUrl())) {
            entity.put("iconUrl", tree.getIconUrl());
        }
        // 图标类型
        if (!StringUtils.isEmpty(tree.getIconType())) {
            entity.put("iconType", tree.getIconType());
        }
        //  创建时间
        if (!StringUtils.isEmpty(tree.getCreateTime())) {
            entity.put("createTime", tree.getCreateTime());
        }
        // 创建人
        if (!StringUtils.isEmpty(tree.getUpdaterCreator())) {
            entity.put("updaterCreator", tree.getUpdaterCreator());
        }
        // 更新时间
        if (!StringUtils.isEmpty(tree.getUpdateTime())) {
            entity.put("updateTime", tree.getUpdateTime());
        }
        // 父ID
        if (!StringUtils.isEmpty(tree.getParentId())) {
            entity.put("parentId", tree.getParentId());
        }
        // 子ID
        entity.put("children", tree.getChildren());
        // 属性
        if (!StringUtils.isEmpty(tree.getProperty())) {
            entity.put("property", tree.getProperty());
        }
        // 扩展数据
        if (!StringUtils.isEmpty(tree.getCustomData())) {
            entity.put("customData", tree.getCustomData());
        }
        // 是否可操作
        if (!StringUtils.isEmpty(tree.getOperate())) {
            entity.put("operate", tree.getOperate());
        }
        // 返回
        return entity;
    }
}
