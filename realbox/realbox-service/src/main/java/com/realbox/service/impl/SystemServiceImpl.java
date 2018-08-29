/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.alibaba.fastjson.JSON;
import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.FileUtils;
import com.realbox.model.*;
import com.realbox.model.entity.Entity;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.entity.TemplateEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.*;
import com.realbox.service.SystemService;
import com.realbox.service.utils.LogUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author MJJ
 * @create Id: SystemServiceImpl.java v 0.1 2017年12月03日 下午2:56 MJJ Exp $
 **/
@Service("systemService")
public class SystemServiceImpl extends BaseService implements SystemService {

    private static final String NAME = "admin";
    private static final String PASS = "123";
    private static final String DESC = "广告机初始化";

    @Value("${template}")
    String template;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemItemsMapper temItemsMapper;

    @Autowired
    private PrivilegeMapper privMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InterMapper interMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private LogUtils logUtils;

    /**
     * 检测Token
     *
     * @param request 请求
     * @return
     */
    @Override
    public RespEntity<String> check(HttpServletRequest request) {
        // 获取用户
        String name = request.getHeader("name");
        // 获取Token
        String token = request.getHeader("token");
        // 验证Token
        String result = redisTemplate.opsForValue().get(name);
        if (StringUtils.isEmpty(result)) {
            return errors(RealBox.TOKEN000);
        }
        if (!token.equals(result)) {
            return errors(RealBox.TOKEN000);
        }
        return success();
    }

    /**
     * 初始化系统
     *
     * @return
     */
    @Override
    public RespEntity<String> init() {
        // 树
        List<Inter> inters = new ArrayList<Inter>();
        setTree(inters);
        interMapper.batchCreate(inters, Inter.class);

        // 权限
        RolePrivilege privilege = new RolePrivilege(1);
        privilege = privMapper.create(privilege);

        // 角色
        Role role = new Role("0", privilege.getId(), "admin", "admin", DateUtils.getDateTime(), "超级管理员");
        role = roleMapper.create(role);

        // 用户
        String password = new Sha256Hash(NAME, PASS).toHex();
        User user = new User("0", "51", "默认分组", "41", "默认分组", role.getId(), role.getName(), "admin", password, "admin", DateUtils.getDateTime(), "zh", "超级管理员");
        userMapper.create(user);

        // 初始化系统模板
//        systemTemplate();

        // 记录日志
        logUtils.userLog("初始化系统", request);

        // 返回
        return success();
    }

    /**
     * 设置语言
     *
     * @param language 语言
     * @return
     */
    @Override
    public RespEntity<String> language(String language) {
        if (StringUtils.isEmpty(language)) {
            return errors(RealBox.LAN000);
        }

        // 用户名
        String name = request.getHeader("name");

        // 设置用户语言
        request.getSession().setAttribute(name, language);

        // 返回
        return success();
    }

    /**
     * 初始化系统模板
     *
     * @return
     */
    private void systemTemplate() {
        try {
            // 读取Json文件
            String descFile = ResourceUtils.getFile(template).toString();
            String json = FileUtils.read(descFile);

            // 角色模板JSON
            Entity entity = JSON.parseObject(json, Entity.class);
            if (entity != null) {
                List<TemItems> items = new ArrayList<TemItems>();
                List<Template> templates = new ArrayList<Template>();
                // 遍历模板JSON数据
                for (TemplateEntity tem : entity.getEntity()) {
                    // 设置模板参数
                    Template template = new Template();
                    setTemplate(tem, template);
                    templates.add(template);
                    items.addAll(tem.getTemItems());
                }
                // 批量添加模板
                templateMapper.batchCreate(templates, Template.class);

                // 批量添加元素
                temItemsMapper.batchCreate(items, TemItems.class);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置模板参数
     *
     * @param temp     自定义模板对象
     * @param template 模板对象
     */
    private void setTemplate(TemplateEntity temp, Template template) {
        template.setId(temp.getId());                       // 模板ID
        template.setGroupId(temp.getGroupId());             // 组ID(模板树ID)
        template.setPrivId(temp.getPrivId());               // 资源权限ID
        template.setName(temp.getName());                   // 模板名
        template.setPreview(temp.getPreview());             // 预览图
        template.setCreator(temp.getCreator());             // 创建人
        template.setType(temp.getType());                   // 模板类型
        template.setTerminalType(DateUtils.getDateTime());  // 终端类型
        template.setUpdateTime(temp.getUpdateTime());       // 更新时间
        template.setResolution(temp.getResolution());       // 分辨率
        template.setHeight(temp.getHeight());               // 高
        template.setWidth(temp.getWidth());                 // 宽
        for (TemItems items : temp.getTemItems())
            template.setElemIds(items.getId());             // 元素
        template.setBody(temp.getBody());                   // 代码内容
        template.setDesc(temp.getDesc());                   // 描述
    }

    // 设置元素

    /**
     * 结构树
     *
     * @return
     */
    private void setTree(List<Inter> inters) {
        // 中文
        /**
         * 资源管理树
         */
        List<Tree> zh = new ArrayList<Tree>();
        zh.add(new Tree("0", "资源管理", "supermen", "admin", DateUtils.getDateTime(), "0", "资源管理", Arrays.asList("1", "2"), "NO"));
        zh.add(new Tree("1", "图片管理", "image", "admin", DateUtils.getDateTime(), "0", "资源管理", null, "NO"));
        zh.add(new Tree("2", "视频管理", "video", "admin", DateUtils.getDateTime(), "0", "资源管理", null, "NO"));

        /**
         * 模板树
         */
        zh.add(new Tree("20", "模板管理", "supermen", "admin", DateUtils.getDateTime(), "20", "模板管理", Arrays.asList("21", "22"), "NO"));
        zh.add(new Tree("21", "系统模板", "system", "admin", DateUtils.getDateTime(), "20", "模板管理", null, "NO"));
        zh.add(new Tree("22", "用户模板", "user", "admin", DateUtils.getDateTime(), "20", "模板管理", null, "NO"));

        /**
         * 节目列表树
         */
        zh.add(new Tree("30", "节目列表", "supermen", "admin", DateUtils.getDateTime(), "30", "节目列表", Arrays.asList("31"), "NO"));
        zh.add(new Tree("31", "默认列表", "program", "admin", DateUtils.getDateTime(), "30", "节目列表", null, "NO"));

        /**
         * 终端分组树
         */
        zh.add(new Tree("40", "终端分组", "supermen", "admin", DateUtils.getDateTime(), "40", "终端分组", Arrays.asList("41"), "NO"));
        zh.add(new Tree("41", "默认分组", "terminal", "admin", DateUtils.getDateTime(), "40", "终端分组", null, "NO"));

        /**
         * 用户分组树
         */
        zh.add(new Tree("50", "用户分组", "supermen", "admin", DateUtils.getDateTime(), "50", "用户分组", Arrays.asList("51"), "NO"));
        zh.add(new Tree("51", "默认分组", "group", "admin", DateUtils.getDateTime(), "50", "用户分组", null, "NO"));

        // 英文
        /**
         * 资源管理树
         */
        List<Tree> en = new ArrayList<Tree>();
        en.add(new Tree("0", "ResourceManage", "supermen", "admin", DateUtils.getDateTime(), "0", "ResourceManage", Arrays.asList("1", "2"), "NO"));
        en.add(new Tree("1", "ImageManage", "image", "admin", DateUtils.getDateTime(), "0", "ResourceManage", null, "NO"));
        en.add(new Tree("2", "VideoManage", "video", "admin", DateUtils.getDateTime(), "0", "ResourceManage", null, "NO"));

        /**
         * 模板树
         */
        en.add(new Tree("20", "TemplateManage", "supermen", "admin", DateUtils.getDateTime(), "20", "TemplateManage", Arrays.asList("21", "22"), "NO"));
        en.add(new Tree("21", "SystemTemplate", "system", "admin", DateUtils.getDateTime(), "20", "TemplateManage", null, "NO"));
        en.add(new Tree("22", "UserTemplate", "user", "admin", DateUtils.getDateTime(), "20", "TemplateManage", null, "NO"));

        /**
         * 节目列表树
         */
        en.add(new Tree("30", "ProgramList", "supermen", "admin", DateUtils.getDateTime(), "30", "ProgramList", Arrays.asList("31"), "NO"));
        en.add(new Tree("31", "DefaultList", "program", "admin", DateUtils.getDateTime(), "30", "ProgramList", null, "NO"));

        /**
         * 终端分组树
         */
        en.add(new Tree("40", "TerminalGroup", "supermen", "admin", DateUtils.getDateTime(), "40", "TerminalGroup", Arrays.asList("41"), "NO"));
        en.add(new Tree("41", "DefaultGroup", "terminal", "admin", DateUtils.getDateTime(), "40", "TerminalGroup", null, "NO"));

        /**
         * 用户分组树
         */
        en.add(new Tree("50", "UserGroup", "supermen", "admin", DateUtils.getDateTime(), "50", "UserGroup", Arrays.asList("51"), "NO"));
        en.add(new Tree("51", "DefaultGroup", "group", "admin", DateUtils.getDateTime(), "50", "UserGroup", null, "NO"));

        inters.add(new Inter("zh", zh));
        inters.add(new Inter("en", en));
    }
}
