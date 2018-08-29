/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.utils;

import com.realbox.common.utils.BeanUtils;
import com.realbox.common.utils.Maps;
import com.realbox.model.Inter;
import com.realbox.model.RolePrivilege;
import com.realbox.model.Tree;
import com.realbox.model.entity.TreeEntity;
import com.realbox.repository.mongodb.mapper.InterMapper;
import com.realbox.repository.mongodb.mapper.TreeMapper;
import com.realbox.repository.mongodb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: TreeUtils.java v 0.1 2017年12月17日 17:08 MJJ Exp $
 **/
@Component
public class TreeUtils {

    @Autowired
    private TreeMapper treeMapper;

    @Autowired
    private InterMapper interMapper;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询树
     *
     * @param id 树ID
     * @return
     */
    public Tree tree(String id) {
        // 用户名
        String name = request.getHeader("name");
        // 查询用户语言
        Object language = request.getSession().getAttribute(name);
        // 获取对应语言树
        Map<String, Object> cond = Maps.create().add("language", language).get();
        Inter inter = interMapper.query(cond, Inter.class);
        // 遍历树
        for (Tree tree : inter.getTrees()) {
            if (id.equals(tree.getId())) {
                return tree;
            }
        }
        return null;
    }

    /**
     * 修改树
     *
     * @param id      父ID
     * @param childId 子ID
     */
    public void updateTree(String id, String childId) {
        List<Inter> inters = interMapper.queryList(Inter.class);
        for (Inter inter : inters) {
            // 遍历修改
            for (Tree tree : inter.getTrees()) {
                if (id.equals(tree.getId())) {
                    tree.setChildren(childId);
                }
            }
            // 修改
            interMapper.update(inter.getId(), inter);
        }
    }

    /**
     * 修改树
     *
     * @param id      　父ID
     * @param childId 　子ID集合
     */
    public void updateTree(String id, List<String> childId) {
        List<Inter> inters = interMapper.queryList(Inter.class);
        for (Inter inter : inters) {
            // 遍历修改
            for (Tree tree : inter.getTrees()) {
                if (id.equals(tree.getId())) {
                    tree.setChildrens(childId);
                }
            }
            // 修改
            interMapper.update(inter.getId(), inter);
        }
    }

    /**
     * 获取遍历树
     *
     * @param tree 树对象
     * @return
     */
    public List<TreeEntity> tree(Tree tree) {
        // 存储结构树
        TreeEntity entity = new TreeEntity();
        // 循环遍历所有树
        trees(tree, entity);
        // 返回
        List<TreeEntity> entities = new ArrayList<TreeEntity>();
        entities.add(entity);
        return entities;
    }

    /**
     * 遍历树
     *
     * @param tree   树对象
     * @param entity 自定义树对象
     */
    private void trees(Tree tree, TreeEntity entity) {
        // 设置自定义结构树类
        setTreeEntity(tree, entity);

        // 判断是否有子
        if (tree.getChildren() != null) {
            // 遍历子
            for (String id : tree.getChildren()) {
                // 查询子
                Tree result = treeMapper.queryById(id, Tree.class);
                if (result == null) {
                    result = tree(id);
                }
                // 创建子树对象
                TreeEntity child = new TreeEntity();
                // 循环执行
                trees(result, child);
                // 设置父子关系
                entity.setChildren(child);
            }
        }
    }


    /**
     * 获取角色权限
     *
     * @param privilege 权限对象
     * @return
     */
    public List<Tree> getRole(RolePrivilege privilege) {
        // 实体转Map
        List<String> names = new ArrayList<String>();
        // 循环遍历获取树ID
        Map<String, Object> maps = BeanUtils.beanToMap(privilege);
        for (Map.Entry<String, Object> map : maps.entrySet()) {
            if (map.getValue().equals(1)) {
                names.add(map.getKey());
            }
        }
        // 批量查询
        return treeMapper.batchQueryByProperty(names);
    }

    /**
     * 获取权限树(权限表(RolePrivilege)对比结构树表(tree))
     *
     * @param privilege 权限对象
     * @return
     */
    public List<TreeEntity> getPrivTree(RolePrivilege privilege) {
        // 查询公共权限树
        Tree tree = treeMapper.queryById("70", Tree.class);
        TreeEntity entity = new TreeEntity();
        // 实体转Map
        Map<String, Object> map = BeanUtils.beanToMap(privilege);
        // 获取权限对应的树
        privTree(tree, map, entity);
        // 返回
        List<TreeEntity> entities = new ArrayList<TreeEntity>();
        entities.add(entity);
        return entities;
    }

    /**
     * 遍历
     *
     * @param tree 树对象
     * @return
     */
    private void privTree(Tree tree, Map<String, Object> maps, TreeEntity entity) {
        for (Map.Entry<String, Object> map : maps.entrySet()) {
            if (tree.getProperty().equals(map.getKey()) && map.getValue().equals(1)) {

                if (tree.getChildren() != null) {
                    for (String id : tree.getChildren()) {
                        Tree result = treeMapper.queryById(id, Tree.class);
                        TreeEntity child = new TreeEntity();

                        privTree(result, maps, child);

                        for (Map.Entry<String, Object> m : maps.entrySet()) {
                            if (result.getProperty().equals(m.getKey()) && m.getValue().equals(1)) {
                                entity.setChildren(child);
                            }
                        }
                    }

                }
                setTreeEntity(tree, entity);
            }
        }
    }

    /**
     * 验证结构树是否存在(验证ID下所有子树，包含自己本身)
     *
     * @param id    根树ID
     * @param value 验证树ID
     * @return
     */
    public boolean checkTree(String id, String value) {
        Tree tree = getTrees(id);
        // 判断树是否存在
        if (tree == null) {
            return false;
        }
        // 获取结构树
        List<Tree> trees = new ArrayList<Tree>();
        trees(tree, trees);
        // 验证结构树
        for (Tree t : trees) {
            if (value.equals(t.getId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 遍历
     *
     * @param tree  结构树对象
     * @param trees 存储结构树对象(List集合)
     * @return
     */
    private void trees(Tree tree, List<Tree> trees) {
        // 存储结构树
        trees.add(tree);
        // 判断是否有子
        if (tree.getChildren() != null) {
            // 遍历子
            for (String id : tree.getChildren()) {
                // 查询子
                Tree child = getTrees(id);
                // 循环执行
                trees(child, trees);
            }
        }
    }

    /**
     * 获取树
     *
     * @param id 树ID
     * @return
     */
    private Tree getTrees(String id) {
        Tree tree = null;
        // 查询条件
        Map<String, Object> cond = Maps.create().add("language", "zh").get();
        // 查询树
        Inter inter = interMapper.query(cond, Inter.class);
        // 遍历树
        for (Tree t : inter.getTrees()) {
            if (id.equals(t.getId())) {
                tree = t;
                break;
            }
        }
        return tree;
    }

    /**
     * 设置自定义结构树类
     *
     * @param tree   树对象
     * @param entity 自定义树对象
     * @return
     */
    private void setTreeEntity(Tree tree, TreeEntity entity) {
        // ID
        entity.setId(tree.getId());
        // 名称
        entity.setLabel(tree.getLabel());
        // 图标地址
        entity.setIconUrl(tree.getIconUrl());
        // 图标类型
        entity.setIconType(tree.getIconType());
        // 结构树类型
        entity.setTreeType(tree.getTreeType());
        // 创建人
        entity.setCreator(tree.getCreator());
        // 创建时间
        entity.setCreateTime(tree.getCreateTime());
        // 更新人
        entity.setUpdaterCreator(tree.getUpdaterCreator());
        // 更新时间
        entity.setUpdateTime(tree.getUpdateTime());
        // 父
        entity.setParentId(tree.getParentId());
        // 父名
        entity.setParentName(tree.getParentName());
        // 属性
        entity.setProperty(tree.getProperty());
        // 扩展
        entity.setCustomData(tree.getCustomData());
        // 是否可删除
        entity.setOperate(tree.getOperate());
    }
}
