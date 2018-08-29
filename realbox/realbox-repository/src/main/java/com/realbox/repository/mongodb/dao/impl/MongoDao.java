/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.dao.impl;

import com.mongodb.BasicDBObject;
import com.realbox.common.utils.Pages;
import com.realbox.repository.mongodb.dao.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: MongoMapperDao.java v 0.1 2017年12月16日 15:43 MJJ Exp $
 **/
@Repository
public class MongoDao<T> implements Mongo<T> {

    @Autowired
    private MongoOperations operations;

    /**
     * 查询(列表、分页)
     *
     * @param cond  条件
     * @param pages 分页
     * @param var   返回类型
     * @return
     */
    @Override
    public List<T> queryList(Map<String, Object> cond, Pages pages, Class<T> var) {
        // 条件
        Criteria criteria = getCriteria(cond);

        // 排除字段
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("password", 0);
        Query query = new BasicQuery(new BasicDBObject(), fieldsObject);

        // 设置条件
        query.addCriteria(criteria);

        // 查询
        return operations.find(query.limit(pages.getPageCount()).skip(pages.getPageNum()), var);
    }

    /**
     * 查询(列表)
     *
     * @param cond 条件
     * @param var  返回类型
     * @return
     */
    @Override
    public List<T> queryList(Map<String, Object> cond, Class<T> var) {
        // 条件
        Criteria criteria = getCriteria(cond);
        // 查询
        return operations.find(new Query(criteria), var);
    }

    /**
     * 查询(列表)
     *
     * @param var 返回类型
     * @return
     */
    @Override
    public List<T> queryList(Class<T> var) {
        Criteria criteria = new Criteria();
        return operations.find(new Query(criteria), var);
    }

    /**
     * 查询(批量)
     *
     * @param ids ID(List集合)
     * @param var 查询类型
     * @returnw
     */
    @Override
    public List<T> batchQueryById(List<String> ids, Class<T> var) {
        // 查询
        return operations.find(new Query(Criteria.where("id").in(ids)), var);
    }

    /**
     * 查询(ID)
     *
     * @param id  ID
     * @param var 返回类型
     * @return
     */
    @Override
    public T queryById(String id, Class<T> var) {
        // 查询
        return operations.findOne(new Query(Criteria.where("id").is(id)), var);
    }

    /**
     * 查询
     *
     * @param cond 条件
     * @param var  返回类型
     * @return
     */
    @Override
    public T query(Map<String, Object> cond, Class<T> var) {
        // 条件
        Criteria criteria = getCriteria(cond);
        // 查询
        return operations.findOne(new Query(criteria), var);
    }

    /**
     * 统计(带条件)
     *
     * @param cond 条件
     * @param var  查询类型
     * @return
     */
    @Override
    public Long count(Map<String, Object> cond, Class<T> var) {
        // 条件
        Criteria criteria = getCriteria(cond);
        // 查询
        return operations.count(new Query(criteria), var);
    }

    /**
     * 新增
     *
     * @param entity 对象
     * @return
     */
    @Override
    public T create(T entity) {
        // 新增
        operations.insert(entity);
        // 返回
        return entity;
    }

    /**
     * 新增(批量)
     *
     * @param entitys 对象集合
     * @param var     新增类型
     * @return
     */
    @Override
    public List<T> batchCreate(List<T> entitys, Class<T> var) {
        // 新增
        operations.insert(entitys, var);
        // 返回
        return entitys;
    }

    /**
     * 修改
     *
     * @param cond   条件
     * @param entity 修改参数
     * @param var    修改类型
     */
    @Override
    public void update(Map<String, Object> cond, Map<String, Object> entity, Class<T> var) {
        // 条件
        Criteria criteria = getCriteria(cond);

        // 参数
        Update update = getUpdate(entity);

        // 修改
        operations.updateFirst(new Query(criteria), update, var);
    }

    /**
     * 修改(ID)
     *
     * @param id     ID
     * @param entity 修改参数
     * @param var    修改类型
     */
    @Override
    public void update(String id, Map<String, Object> entity, Class<T> var) {
        // 参数
        Update update = getUpdate(entity);
        // 修改
        operations.updateFirst(new Query(Criteria.where("id").is(id)), update, var);
    }

    /**
     * 修改(批量)
     *
     * @param ids    ID(List集合)
     * @param entity 修改参数
     * @param var    修改类型
     */
    @Override
    public void batchUpdate(List<String> ids, Map<String, Object> entity, Class<T> var) {
        // 参数
        Update update = getUpdate(entity);
        // 修改
        operations.updateMulti(new Query(Criteria.where("id").in(ids)), update, var);
    }

    /**
     * 删除(ID)
     *
     * @param id ID
     */
    @Override
    public void delete(String id, Class<T> var) {
        // 删除
        operations.remove(new Query(Criteria.where("id").is(id)), var);
    }

    /**
     * 删除
     *
     * @param cond 条件
     * @param var  删除类型
     */
    @Override
    public void delete(Map<String, Object> cond, Class<T> var) {
        // 条件
        Criteria criteria = getCriteria(cond);

        // 删除
        operations.remove(new Query(criteria), var);
    }

    /**
     * 删除(批量)
     *
     * @param ids ID集合
     */
    @Override
    public void batchDelete(List<String> ids, Class<T> var) {
        // 删除
        operations.remove(new Query(Criteria.where("id").in(ids)), var);
    }

    /**
     * 获取查询条件
     *
     * @param cond 条件
     * @return
     */
    private Criteria getCriteria(Map<String, Object> cond) {
        // 存储条件
        Criteria criteria = new Criteria();
        // 遍历查询条件
        for (Map.Entry<String, Object> map : cond.entrySet()) {
            // 条件不为空存储
            if (!StringUtils.isEmpty(map.getValue())) {
                criteria.and(map.getKey()).is(map.getValue());
            }
        }
        // 返回条件
        return criteria;
    }

    /**
     * 获取修改条件
     *
     * @param entity 对象
     * @return
     */
    private Update getUpdate(Map<String, Object> entity) {
        // 存储条件
        Update update = new Update();
        // 遍遍历修改条件
        for (Map.Entry<String, Object> map : entity.entrySet()) {
            // 设置参数
            update.set(map.getKey(), map.getValue());
        }
        // 返回条件
        return update;
    }
}
