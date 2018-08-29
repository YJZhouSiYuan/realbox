/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service;

import com.realbox.model.entity.RespEntity;
import com.realbox.model.Tree;

import java.util.Map;

/**
 * @author MJJ
 * @create Id: TreeService.java v 0.1 2017年12月04日 上午2:03 MJJ Exp $
 **/
public interface TreeService {

    /**
     * 查询结构树
     *
     * @param id ID
     * @return
     */
    RespEntity<Map<String, Object>> queryTree(String id);

    /**
     * 添加结构树
     *
     * @param tree 结构树对象
     * @return
     */
    RespEntity<String> createTree(Tree tree);

    /**
     * 编辑结构树
     *
     * @param tree 结构树对象
     * @return
     */
    RespEntity<String> updateTree(Tree tree);

    /**
     * 删除结构树
     *
     * @param id ID
     * @return
     */
    RespEntity<String> deleteTree(String id);
}
