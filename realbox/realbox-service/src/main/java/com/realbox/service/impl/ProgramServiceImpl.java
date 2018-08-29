/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.DateUtils;
import com.realbox.model.ProItems;
import com.realbox.model.Program;
import com.realbox.model.Template;
import com.realbox.model.entity.ProgramEntity;
import com.realbox.model.entity.RespEntity;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.enums.RealBox;
import com.realbox.model.enums.Trees;
import com.realbox.repository.mongodb.mapper.ProItemsMapper;
import com.realbox.repository.mongodb.mapper.ProgramMapper;
import com.realbox.repository.mongodb.mapper.TemplateMapper;
import com.realbox.service.ProgramService;
import com.realbox.service.utils.LogUtils;
import com.realbox.service.utils.ProgramUtils;
import com.realbox.service.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author MJJ
 * @create Id: ProgramServiceImpl.java v 0.1 2017年12月20日 20:59 MJJ Exp $
 **/
@Service("programService")
public class ProgramServiceImpl extends BaseService implements ProgramService {

    @Autowired
    private ProItemsMapper proItemsMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProgramUtils programUtils;

    @Autowired
    private TreeUtils treeUtils;

    @Autowired
    private LogUtils logUtils;

    /**
     * 查询节目
     *
     * @param groupId      组ID(节目列表ID)
     * @param name         节目名称
     * @param resolution   分辨率
     * @param terminalType 终端状态
     * @param pageNo       当前页数
     * @param pageCount    页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryProgram(String id,
                                                        String groupId,
                                                        String name,
                                                        String resolution,
                                                        String terminalType,
                                                        Integer pageNo,
                                                        Integer pageCount) {
        if (StringUtils.isEmpty(pageNo)) {
            return error(RealBox.PAGE000);
        }
        if (StringUtils.isEmpty(pageCount)) {
            return error(RealBox.PAGE001);
        }

        // 查询数量
        Map<String, Object> cond = Maps.create()
                .add("id", id)
                .add("groupId", groupId)
                .add("name", name)
                .add("resolution", resolution)
                .add("terminalType", terminalType).get();
        long count = programMapper.count(cond, Program.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询节目
        List<Program> programs = programMapper.queryList(cond, pages, Program.class);

        // 设置自定义返回类型
        List<ProgramEntity> entities = programUtils.programs(programs);

        // 返回
        Map<String, Object> map = Maps.create()
                .add("programs", entities)
                .add("pages", pages)
                .get();
        return success(map);
    }

    /**
     * 新增节目
     *
     * @param entity 节目对象
     * @return
     */
    @Override
    public RespEntity<String> createProgram(ProgramEntity entity) {
        if (StringUtils.isEmpty(entity.getModelId())) {
            return errors(RealBox.TEMPLATE000);
        }
        if (StringUtils.isEmpty(entity.getName())) {
            return errors(RealBox.PROGRAM001);
        }
        if (StringUtils.isEmpty(entity.getPreview())) {
            return errors(RealBox.PROGRAM002);
        }
        if (entity.getProItems().isEmpty()) {
            return errors(RealBox.PROGRAM003);
        }
        if (StringUtils.isEmpty(entity.getGroupId())) {
            entity.setGroupId("31");
        }

        // 验证节目列表树
        boolean flag = treeUtils.checkTree(Trees.PROGRAM.getValue(), entity.getGroupId());
        if (flag) {
            return errors(RealBox.TREPRO001);
        }

        // 验证模板
        Template template = templateMapper.queryById(entity.getModelId(), Template.class);
        if (template == null) {
            return errors(RealBox.TEMPLATE012);
        }

        // 验证节目
        Map<String, Object> cond = Maps.create().add("name", entity.getName()).add("groupId", entity.getGroupId()).get();
        Program result = programMapper.query(cond, Program.class);
        if (result != null) {
            return errors(RealBox.PROGRAM006);
        }

        // 新增节目元素
        entity.setId(UUID.randomUUID().toString());
        setProElement(entity.getId(), entity.getProItems());
        List<ProItems> proItems = proItemsMapper.batchCreate(entity.getProItems(), ProItems.class);

        // 新增节目
        Program program = new Program();
        setProgram(template, entity, proItems, program);
        programMapper.create(program);

        // 记录日志
        logUtils.userLog("新增节目", request);

        // 返回
        return success();
    }

    /**
     * 编辑节目
     *
     * @param entity 节目对象
     * @return
     */
    @Override
    public RespEntity<String> updateProgram(ProgramEntity entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            return errors(RealBox.PROGRAM000);
        }
        if (StringUtils.isEmpty(entity.getGroupId())) {
            return errors(RealBox.TREPRO000);
        }
        if (StringUtils.isEmpty(entity.getModelId())) {
            return errors(RealBox.TEMPLATE000);
        }
        if (StringUtils.isEmpty(entity.getName())) {
            return errors(RealBox.PROGRAM001);
        }
        if (StringUtils.isEmpty(entity.getPreview())) {
            return errors(RealBox.PROGRAM002);
        }
        if (entity.getProItems().isEmpty()) {
            return errors(RealBox.PROGRAM003);
        }

        // 验证节目列表树
        boolean flag = treeUtils.checkTree(Trees.PROGRAM.getValue(), entity.getGroupId());
        if (flag) {
            return errors(RealBox.TREPRO001);
        }

        // 验证模板
        Template template = templateMapper.queryById(entity.getModelId(), Template.class);
        if (template == null) {
            return errors(RealBox.TEMPLATE012);
        }

        // 验证节目
        Map<String, Object> cond = Maps.create().add("name", entity.getName()).add("groupId", entity.getGroupId()).get();
        Program result1 = programMapper.query(cond, Program.class);
        if (result1 != null) {
            if (!result1.getId().equals(entity.getId())) {
                return errors(RealBox.PROGRAM006);
            }
        } else {
            Program result2 = programMapper.queryById(entity.getId(), Program.class);
            if (result2 == null) {
                return errors(RealBox.PROGRAM007);
            }
        }

        // 编辑节目元素
        List<ProItems> proItems = editProElement(entity);

        // 编辑节目
        Program program = new Program();
        setProgram(template, entity, proItems, program);
        programMapper.update(entity.getId(), program);

        // 记录日志
        logUtils.userLog("编辑节目", request);

        // 返回
        return success();
    }

    /**
     * 删除节目
     *
     * @param ids ID(List集合)
     * @return
     */
    @Override
    public RespEntity<String> deleteProgram(String ids) {

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证节目
        List<Program> programs = programMapper.batchQueryById(arrayId, Program.class);
        if (programs.isEmpty()) {
            return errors(RealBox.PROGRAM007);
        }

        // 删除节目
        programMapper.batchDelete(arrayId, Program.class);

        // 删除节目元素
        for (Program program : programs) {
            proItemsMapper.batchDelete(program.getElemIds(), ProItems.class);
        }

        // 记录日志
        logUtils.userLog("删除节目", request);

        // 返回
        return success();
    }

    /**
     * 编辑节目元素
     *
     * @param entity 自定义节目对象
     */
    private List<ProItems> editProElement(ProgramEntity entity) {
        // 删除节目下所有元素
        Map<String, Object> cond = Maps.create().add("proId", entity.getId()).get();
        proItemsMapper.delete(cond, ProItems.class);

        // 新增节目元素
        setProElement(entity.getId(), entity.getProItems());
        return proItemsMapper.batchCreate(entity.getProItems(), ProItems.class);
    }

    /**
     * 设置节目参数
     *
     * @param template 模板对象
     * @param entity   自定义节目对象
     * @param proItems 节目元素
     * @param program  节目对象
     */
    private void setProgram(Template template, ProgramEntity entity, List<ProItems> proItems, Program program) {
        if (!StringUtils.isEmpty(entity.getId())) {
            program.setId(entity.getId());                      // ID
        }
        program.setGroupId(entity.getGroupId());                // 组ID(节目结构树ID)
        program.setPrivId(entity.getPrivId());                  // 权限ID
        program.setModelId(entity.getModelId());                // 模板ID
        program.setName(entity.getName());                      // 节目名称
        program.setPreview(entity.getPreview());                // 预览图
        program.setResolution(template.getResolution());        // 分辨率
        program.setTerminalType(template.getTerminalType());    // 终端类型
        program.setStatus("正常");                                // 状态
        for (ProItems items : proItems) {
            program.setElemIds(items.getId());                  // 节目元素ID
        }
        program.setUpdateTime(DateUtils.getDateTime());         // 更新时间
        program.setBody(template.getBody());                    // 代码内容
    }

    /**
     * 设置新增节目元素参数
     *
     * @param proId    节目ID
     * @param proItems 节目元素
     */
    private void setProElement(String proId, List<ProItems> proItems) {
        // 元素设置节目ID
        for (ProItems pro : proItems) {
            pro.setProId(proId);
        }
    }
}
