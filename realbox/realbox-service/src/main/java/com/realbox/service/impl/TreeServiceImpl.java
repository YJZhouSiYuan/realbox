/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.DateUtils;
import com.realbox.model.entity.RespEntity;
import com.realbox.common.utils.Maps;
import com.realbox.model.Tree;
import com.realbox.model.entity.TreeEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.TreeMapper;
import com.realbox.service.TreeService;
import com.realbox.service.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author MJJ
 * @create Id: TreeServiceImpl.java v 0.1 2017年12月04日 上午2:04 MJJ Exp $
 **/
@Service("groupService")
public class TreeServiceImpl extends BaseService implements TreeService {

    @Autowired
    private TreeMapper treeMapper;

    @Autowired
    private TreeUtils treeUtils;

    /**
     * 查询结构树
     *
     * @param id ID
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryTree(String id) {
        if (StringUtils.isEmpty(id)) {
            return error(RealBox.TREE000);
        }

        // 验证结构树
        Tree tree = treeUtils.tree(id);
        if (tree == null) {
            return error(RealBox.TREE004);
        }

        // 获取所有子树
        List<TreeEntity> trees = treeUtils.tree(tree);

        // 返回
        Map<String, Object> map = Maps.create().add("trees", trees).get();
        return success(map);
    }

    /**
     * 新增结构树
     *
     * @param tree 结构树对象
     * @return
     */
    @Override
    public RespEntity<String> createTree(Tree tree) {
        if (StringUtils.isEmpty(tree.getLabel())) {
            return errors(RealBox.TREE001);
        }
        if (StringUtils.isEmpty(tree.getCreator())) {
            return errors(RealBox.TREE002);
        }
        if (StringUtils.isEmpty(tree.getParentId())) {
            return errors(RealBox.TREE003);
        }

        // 验证父结构树
        boolean sign = false;
        Tree parent = treeMapper.queryById(tree.getParentId(), Tree.class);
        if (parent == null) {
            parent = treeUtils.tree(tree.getParentId());
            sign = true;
        }
        if (parent == null) {
            return errors(RealBox.TREE004);
        }

        // 验证新增结构树
        Map<String, Object> childCond = Maps.create().add("label", tree.getLabel()).add("parentId", tree.getParentId()).get();
        Tree child = treeMapper.query(childCond, Tree.class);
        if (child != null) {
            return errors(RealBox.TREE005);
        }

        // 设置结构树参数
        setTree(tree, parent.getLabel());

        // 新增结构树
        Tree childTree = treeMapper.create(tree);

        // 修改父结构树(父结构树添加子结构树ID)
        if (sign) {
            treeUtils.updateTree(parent.getId(), childTree.getId());
        } else {
            parent.setChildren(childTree.getId());
            treeMapper.update(parent.getId(), parent);
        }

        // 返回
        return success();
    }

    /**
     * 编辑结构树
     *
     * @param tree 结构树对象
     * @return
     */
    @Override
    public RespEntity<String> updateTree(Tree tree) {
        if (StringUtils.isEmpty(tree.getId())) {
            return errors(RealBox.TREE000);
        }
        if (StringUtils.isEmpty(tree.getLabel())) {
            return errors(RealBox.TREE001);
        }
        if (StringUtils.isEmpty(tree.getCreator())) {
            return errors(RealBox.TREE002);
        }
        if (StringUtils.isEmpty(tree.getParentId())) {
            return errors(RealBox.TREE003);
        }
        if (StringUtils.isEmpty(tree.getUpdaterCreator())) {
            return errors(RealBox.TREE007);
        }

        // 验证结构数是否可操作
        Tree tree1 = treeUtils.tree(tree.getId());
        if (tree1 != null) {
            return errors(RealBox.TREE006);
        }

        boolean sign = false;
        // 是否修改节点
        if (!StringUtils.isEmpty(tree.getNewId())) {
            // 验证新父结构树
            Tree newParent = treeMapper.queryById(tree.getNewId(), Tree.class);
            if (newParent == null) {
                newParent = treeUtils.tree(tree.getNewId());
                sign = true;
            }
            if (newParent == null) {
                return errors(RealBox.TREE009);
            }

            // 验证子树
            Map<String, Object> cond = Maps.create().add("parentId", tree.getNewId()).add("label", tree.getLabel()).get();
            Tree child = treeMapper.query(cond, Tree.class);
            if (child != null) {
                return errors(RealBox.TREE005);
            }

            // 修改子树父ID
            cond = Maps.create().add("parentId", tree.getNewId()).add("label", tree.getLabel()).add("parentName", newParent.getLabel()).get();
            treeMapper.update(tree.getId(), cond, Tree.class);

            // 新父结构树添加子树
            newParent.setChildren(tree.getId());
            if (sign) {
                treeUtils.updateTree(tree.getNewId(), newParent.getChildren());
            } else {
                cond = Maps.create().add("children", newParent.getChildren()).get();
                treeMapper.update(tree.getNewId(), cond, Tree.class);
            }

            // 验证旧父结构树
            sign = false;
            Tree oldParent = treeMapper.queryById(tree.getParentId(), Tree.class);
            if (oldParent == null) {
                oldParent = treeUtils.tree(tree.getParentId());
                sign = true;
            }
            if (oldParent == null) {
                return errors(RealBox.TREE010);
            }

            // 旧父结构树删除子树
            oldParent.getChildren().remove(tree.getId());
            if (sign) {
                treeUtils.updateTree(tree.getParentId(), oldParent.getChildren());
            } else {
                cond = Maps.create().add("children", oldParent.getChildren()).get();
                treeMapper.update(tree.getParentId(), cond, Tree.class);
            }
        } else {
            // 验证父结构树
            Tree parent = treeMapper.queryById(tree.getParentId(), Tree.class);
            if (parent == null) {
                parent = treeUtils.tree(tree.getParentId());
            }
            if (parent == null) {
                return errors(RealBox.TREE004);
            }

            // 验证编辑结构树
            Map<String, Object> cond = Maps.create().add("label", tree.getLabel()).add("parentId", tree.getParentId()).get();
            Tree result = treeMapper.query(cond, Tree.class);
            if (result != null) {
                if (!result.getId().equals(tree.getId())) {
                    return errors(RealBox.TREE005);
                }
            } else {
                result = treeMapper.queryById(tree.getId(), Tree.class);
                if (result == null) {
                    return errors(RealBox.TREE008);
                }
            }

            // 设置编辑结构树参数
            setTree(result, tree);

            // 编辑结构树
            treeMapper.update(tree.getId(), tree);

            // 如果修改的是父结构树，那他的所有子结构树都要修改父结构树名称
            if (!result.getLabel().equals(tree.getLabel())) {
                Map<String, Object> entity = Maps.create().add("parentName", tree.getLabel()).get();
                treeMapper.batchUpdate(result.getChildren(), entity, Tree.class);
            }
        }

        // 返回
        return success();
    }

    /**
     * 删除结构树
     *
     * @param id ID
     * @return
     */
    @Override
    public RespEntity<String> deleteTree(String id) {
        if (StringUtils.isEmpty(id)) {
            return errors(RealBox.TREE000);
        }

        // 验证结构树
        Tree tree = treeMapper.queryById(id, Tree.class);
        if (tree == null) {
            tree = treeUtils.tree(id);
        }
        if (tree == null) {
            return errors(RealBox.TREE008);
        }

        // 是否可删除
        if ("NO".equals(tree.getOperate())) {
            return errors(RealBox.TREE006);
        }

        // 删除树
        treeMapper.delete(id, Tree.class);

        // 修改父结构树(父结构树删除子当前子结构树ID)
        boolean sign = false;
        Tree parent = treeMapper.queryById(tree.getParentId(), Tree.class);
        if (parent == null) {
            parent = treeUtils.tree(tree.getParentId());
            sign = true;
        }

        if (parent != null) {
            // 删除子树ID
            parent.getChildren().remove(id);
            if (sign) {
                treeUtils.updateTree(tree.getParentId(), parent.getChildren());
            } else {
                // 修改
                treeMapper.update(tree.getParentId(), parent);
            }
        }

        // 返回
        return success();
    }

    /**
     * 设置新增结构树对象参数
     *
     * @param tree       结构树对象
     * @param parentName 结构树父名称
     */
    private void setTree(Tree tree, String parentName) {
        // 图标地址
        tree.setIconUrl("");
        // 图标类型
        tree.setIconType("");
        // 创建时间
        tree.setCreateTime(DateUtils.getDateTime());
        // 结构树父名称
        tree.setParentName(parentName);
        // 是否可操作
        tree.setOperate("YES");
    }

    /**
     * 设置编辑结构树对象参数
     *
     * @param tree1 修改结构树对象
     * @param tree2 查询结构树对象
     */
    private void setTree(Tree tree1, Tree tree2) {
        // 更新时间
        tree2.setUpdateTime(DateUtils.getDateTime());

        // 子元素
        for (String id : tree1.getChildren()) {
            tree2.setChildren(id);
        }
    }
}
