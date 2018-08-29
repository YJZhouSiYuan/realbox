/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.repository.mongodb.dao;

import com.realbox.common.utils.Pages;

import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: Mongo.java v 0.1 2017年12月16日 15:33 MJJ Exp $
 **/
public interface Mongo<T> {

    /**
     * 查询(列表、分页)
     *
     * @param cond  条件
     * @param pages 分页
     * @param var   返回类型
     * @return
     */
    List<T> queryList(Map<String, Object> cond, Pages pages, Class<T> var);

    /**
     * 查询(列表)
     *
     * @param cond 条件
     * @param var  返回类型
     * @return
     */
    List<T> queryList(Map<String, Object> cond, Class<T> var);

    /**
     * 查询(列表)
     *
     * @param var 返回类型
     * @return
     */
    List<T> queryList(Class<T> var);

    /**
     * 查询(ID、 批量)
     *
     * @param ids ID(List集合)
     * @param var 查询类型
     * @return
     */
    List<T> batchQueryById(List<String> ids, Class<T> var);

    /**
     * 查询(ID)
     *
     * @param id  条件
     * @param var 返回类型
     * @return
     */
    T queryById(String id, Class<T> var);

    /**
     * 查询
     *
     * @param cond 条件
     * @param var  返回类型
     * @return
     */
    T query(Map<String, Object> cond, Class<T> var);

    /**
     * 统计(带条件)
     *
     * @param cond 条件
     * @param var  查询类型
     * @return
     */
    Long count(Map<String, Object> cond, Class<T> var);

    /**
     * 新增
     *
     * @param entity 对象
     * @return
     */
    T create(T entity);

    /**
     * 新增(批量)
     *
     * @param entitys 对象集合
     * @param var     新增类型
     * @return
     */
    List<T> batchCreate(List<T> entitys, Class<T> var);

    /**
     * 修改
     *
     * @param cond   条件
     * @param entity 修改参数
     * @param var    修改类型
     */
    void update(Map<String, Object> cond, Map<String, Object> entity, Class<T> var);

    /**
     * 修改(ID)
     *
     * @param id     ID
     * @param entity 修改参数
     * @param var    修改类型
     */
    void update(String id, Map<String, Object> entity, Class<T> var);

    /**
     * 修改(批量)
     *
     * @param ids    ID(List集合)
     * @param entity 修改参数
     * @param var    修改类型
     */
    void batchUpdate(List<String> ids, Map<String, Object> entity, Class<T> var);

    /**
     * 删除(ID)
     *
     * @param id  条件
     * @param var 删除类型
     */
    void delete(String id, Class<T> var);

    /**
     * 删除
     *
     * @param cond 条件
     * @param var  删除类型
     */
    void delete(Map<String, Object> cond, Class<T> var);

    /**
     * 删除(批量)
     *
     * @param ids ID集合
     * @param var 删除类型
     */
    void batchDelete(List<String> ids, Class<T> var);
}