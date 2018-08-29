/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.TemItems;
import com.realbox.model.Template;
import com.realbox.model.User;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.entity.TemplateEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.model.enums.Trees;
import com.realbox.repository.mongodb.mapper.TemItemsMapper;
import com.realbox.repository.mongodb.mapper.TemplateMapper;
import com.realbox.repository.mongodb.mapper.UserMapper;
import com.realbox.service.TemplateService;
import com.realbox.service.utils.LogUtils;
import com.realbox.service.utils.TemplateUtils;
import com.realbox.service.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author MJJ
 * @create Id: TemplateServiceImpl.java v 0.1 2017年12月08日 上午11:30 MJJ Exp $
 **/
@Service("templateService")
public class TemplateServiceImpl extends BaseService implements TemplateService {

    @Autowired
    private TemItemsMapper temItemsMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemplateUtils templateUtils;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TreeUtils treeUtils;

    @Autowired
    private LogUtils logUtils;

    /**
     * 查询模板
     *
     * @param groupId      组ID(模板结构树ID)
     * @param id           模板ID
     * @param name         模板名
     * @param resolution   分辨率
     * @param terminalType 终端类型
     * @param pageNo       当前页数
     * @param pageCount    页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryTemplate(String groupId, String id, String name, String resolution,
                                                         String terminalType, Integer pageNo, Integer pageCount) {
        if (StringUtils.isEmpty(pageNo)) {
            return error(RealBox.PAGE000);
        }
        if (StringUtils.isEmpty(pageCount)) {
            return error(RealBox.PAGE001);
        }

        // 查询数量
        Map<String, Object> cond = Maps.create()
                .add("groupId", groupId).add("id", id).add("name", name).add("resolution", resolution).add("terminalType", terminalType).get();
        long count = templateMapper.count(cond, Template.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询模板
        List<Template> templates = templateMapper.queryList(cond, pages, Template.class);

        // 设置自定义返回类型
        List<TemplateEntity> entities = templateUtils.templates(templates);

        // 返回
        Map<String, Object> map = Maps.create()
                .add("templates", entities)
                .add("pages", pages)
                .get();
        return success(map);
    }

    /**
     * 新增模板
     *
     * @param entity 模板对象
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> createTemplate(TemplateEntity entity) {
        if (StringUtils.isEmpty(entity.getGroupId())) {
            return error(RealBox.TRETEM000);
        }
        if (StringUtils.isEmpty(entity.getName())) {
            return error(RealBox.TEMPLATE001);
        }
        if (StringUtils.isEmpty(entity.getPreview())) {
            return error(RealBox.TEMPLATE002);
        }
        if (StringUtils.isEmpty(entity.getCreator())) {
            return error(RealBox.TEMPLATE003);
        }
        if (StringUtils.isEmpty(entity.getType())) {
            return error(RealBox.TEMPLATE004);
        }
        if (StringUtils.isEmpty(entity.getTerminalType())) {
            return error(RealBox.TERMINAL002);
        }
        if (StringUtils.isEmpty(entity.getHeight())) {
            return error(RealBox.TEMPLATE005);
        }
        if (StringUtils.isEmpty(entity.getWidth())) {
            return error(RealBox.TEMPLATE006);
        }
        if (entity.getTemItems().isEmpty()) {
            return error(RealBox.TEMPLATE007);
        }
        if (StringUtils.isEmpty(entity.getBody())) {
            return error(RealBox.TEMPLATE008);
        }

//        // 验证模板
//        if ("系统模板".equals(entity.getType())) {
//            return error(RealBox.TEMPLATE009);
//        }

        // 验证模板树
        boolean flag = treeUtils.checkTree(Trees.TEMPLATE.getValue(), entity.getGroupId());
        if (flag) {
            return error(RealBox.TRETEM001);
        }

        // 验证模板
        Map<String, Object> cond = Maps.create().add("name", entity.getName()).add("groupId", entity.getGroupId()).get();
        Template result = templateMapper.query(cond, Template.class);
        if (result != null) {
            return error(RealBox.TEMPLATE011);
        }

        // 验证用户
        cond = Maps.create().add("name", entity.getCreator()).get();
        User user = userMapper.query(cond, User.class);
        if (user == null) {
            return error(RealBox.USER005);
        }

        // 设置新增模板参数
        Template template = new Template();
        setTemplate(entity, template);

        // 新增模板
        template = templateMapper.create(template);

        // 新增模板元素
        setTemElement(template.getId(), entity.getTemItems());
        temItemsMapper.batchCreate(entity.getTemItems(), TemItems.class);

        // 记录日志
        logUtils.userLog("新增模板元素", request);

        // 记录日志
        logUtils.userLog("新增模板", request);

        // 返回
        Map<String, Object> map = Maps.create().add("id", template.getId()).get();
        return success(map);
    }

    /**
     * 编辑模板
     *
     * @param entity 模板对象
     * @return
     */
    @Override
    public RespEntity<String> updateTemplate(TemplateEntity entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            return errors(RealBox.TEMPLATE000);
        }
        if (StringUtils.isEmpty(entity.getGroupId())) {
            return errors(RealBox.TRETEM000);
        }
        if (StringUtils.isEmpty(entity.getName())) {
            return errors(RealBox.TEMPLATE001);
        }
        if (StringUtils.isEmpty(entity.getPreview())) {
            return errors(RealBox.TEMPLATE002);
        }
        if (StringUtils.isEmpty(entity.getCreator())) {
            return errors(RealBox.TEMPLATE003);
        }
        if (StringUtils.isEmpty(entity.getType())) {
            return errors(RealBox.TEMPLATE004);
        }
        if (StringUtils.isEmpty(entity.getTerminalType())) {
            return errors(RealBox.TERMINAL002);
        }
        if (StringUtils.isEmpty(entity.getHeight())) {
            return errors(RealBox.TEMPLATE005);
        }
        if (StringUtils.isEmpty(entity.getWidth())) {
            return errors(RealBox.TEMPLATE006);
        }
        if (entity.getTemItems().isEmpty()) {
            return errors(RealBox.TEMPLATE007);
        }
        if (StringUtils.isEmpty(entity.getBody())) {
            return errors(RealBox.TEMPLATE008);
        }

        // 验证模板
        if ("系统模板".equals(entity.getType())) {
            return errors(RealBox.TEMPLATE009);
        }

        // 验证模板树
        boolean flag = treeUtils.checkTree(Trees.TEMPLATE.getValue(), entity.getGroupId());
        if (flag) {
            return errors(RealBox.TRETEM001);
        }

        // 验证模板
        Map<String, Object> cond = Maps.create().add("name", entity.getName()).add("groupId", entity.getGroupId()).get();
        Template result1 = templateMapper.query(cond, Template.class);
        if (result1 != null) {
            if (!result1.getId().equals(entity.getId())) {
                return errors(RealBox.TEMPLATE011);
            }
        } else {
            Template result2 = templateMapper.queryById(entity.getId(), Template.class);
            if (result2 == null) {
                return errors(RealBox.TEMPLATE012);
            }
        }

        // 编辑模板元素
        editTemElement(entity);

        // 记录日志
        logUtils.userLog("编辑模板元素", request);


        // 编辑模板
        Template template = new Template();
        setTemplate(entity, template);
        templateMapper.update(entity.getId(), template);

        // 记录日志
        logUtils.userLog("编辑模板", request);

        // 返回
        return success();
    }

    /**
     * 删除模板
     *
     * @param ids ID(List集合)
     * @return
     */
    @Override
    public RespEntity<String> deleteTemplate(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.TEMPLATE000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证模板
        List<Template> templates = templateMapper.batchQueryById(arrayId, Template.class);
        if (templates.isEmpty()) {
            return errors(RealBox.TEMPLATE012);
        }
        // 遍历验证类型
        List<String> elemIds = new ArrayList<String>();
        for (Template template : templates) {
            if ("系统模板".equals(template.getType())) {
                return errors(RealBox.TEMPLATE010);
            }

            // 存储删除元素ID
            elemIds.addAll(template.getElemIds());
        }

        // 删除元素
        temItemsMapper.batchDelete(elemIds, TemItems.class);

        // 记录日志
        logUtils.userLog("删除元素", request);

        // 删除模板
        templateMapper.batchDelete(arrayId, Template.class);

        // 记录日志
        logUtils.userLog("删除模板", request);

        // 返回
        return success();
    }

    /**
     * 编辑模板元素
     *
     * @param entity 自定义模板对象
     */
    private void editTemElement(TemplateEntity entity) {
        // 删除模板下所有元素
        Map<String, Object> cond = Maps.create().add("modelId", entity.getId()).get();
        temItemsMapper.delete(cond, TemItems.class);

        // 新增模板元素
        setTemElement(entity.getId(), entity.getTemItems());
        temItemsMapper.batchCreate(entity.getTemItems(), TemItems.class);
    }

    /**
     * 设置模板参数
     *
     * @param entity   自定义模板对象
     * @param template 模板对象
     */
    private void setTemplate(TemplateEntity entity, Template template) {
        if (!StringUtils.isEmpty(entity.getId())) {
            template.setId(entity.getId());                     // ID
        }
        template.setGroupId(entity.getGroupId());               // 组ID(模板资源树ID)
        template.setPrivId(entity.getPrivId());                 // 资源权限ID
        template.setName(entity.getName());                     // 模板名
        template.setPreview(entity.getPreview());               // 预览图
        template.setCreator(entity.getCreator());               // 创建人
        template.setType(entity.getType());                     // 模板类型
        template.setTerminalType(entity.getTerminalType());     // 终端类型
        template.setUpdateTime(DateUtils.getDateTime());        // 更新时间
        String resolution = entity.getWidth() + "*" + entity.getHeight();
        template.setResolution(resolution);                     // 分辨率
        template.setHeight(entity.getHeight());                 // 高
        template.setWidth(entity.getWidth());                   // 宽
        for (TemItems temItems : entity.getTemItems()) {        // 元素
            template.setElemIds(temItems.getId());
        }
        template.setBody(entity.getBody());                     // 代码内容
        template.setDesc(entity.getDesc());                     // 描述
    }

    /**
     * 设置模板元素参数
     *
     * @param modelId     模板ID
     * @param temItems 元素对象
     */
    private void setTemElement(String modelId, List<TemItems> temItems) {
        // 元素设置模板ID
        for (TemItems tem : temItems) {
            tem.setModelId(modelId);
        }
    }
}
