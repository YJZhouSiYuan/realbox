/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.DateUtils;
import com.realbox.common.utils.FileUtils;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.Pages;
import com.realbox.model.Activate;
import com.realbox.model.Terminal;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.ActivateMapper;
import com.realbox.repository.mongodb.mapper.TerminalMapper;
import com.realbox.service.ActivateService;
import com.realbox.service.utils.AsyncUtils;
import com.realbox.service.utils.LogUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipOutputStream;

/**
 * @author MJJ
 * @create Id: ActivateServiceImpl.java v 0.1 2017年12月12日 11:53 MJJ Exp $
 **/
@Service("terminalActivateService")
public class ActivateServiceImpl extends BaseService implements ActivateService {

    @Value("${nginx.host}")
    String host;

    @Value("${nginx.port}")
    String port;

    @Value("${comKey}")
    String comKey;

    @Autowired
    private ActivateMapper activateMapper;

    @Autowired
    private TerminalMapper terminalMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogUtils logUtils;

    @Autowired
    private AsyncUtils async;

    /**
     * 查询激活码
     *
     * @param id        ID
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryActivation(String id, Integer pageNo, Integer pageCount) {
        // 查询数量
        Map<String, Object> cond = Maps.create().add("id", id).get();
        long count = activateMapper.count(cond, Activate.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询激活码
        List<Activate> activates = activateMapper.queryList(cond, pages, Activate.class);

        // 返回
        Map<String, Object> map = Maps.create()
                .add("activates", activates)
                .add("pages", pages)
                .get();
        return success(map);
    }

    /**
     * 新增激活码
     *
     * @param terId   终端树ID
     * @param creator 创建人
     * @param number  生成数量
     * @return
     */
    @Override
    public RespEntity<String> createActivation(String terId, String creator, Integer number) {
        if (StringUtils.isEmpty(terId)) {
            return errors(RealBox.TRETER000);
        }
        if (StringUtils.isEmpty(creator)) {
            return errors(RealBox.ACTIV005);
        }
        if (StringUtils.isEmpty(number)) {
            return errors(RealBox.ACTIV001);
        }

        // 异步生成激活码
        async.createActivation(terId, creator, number);

        // 记录日志
        logUtils.userLog("新增激活码", request);

        // 返回
        return success();
    }

    /**
     * 删除激活码(批量)
     *
     * @param ids     ID
     * @param request 请求
     * @return
     */
    @Override
    public RespEntity<String> deleteActivation(String ids, HttpServletRequest request) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.ACTIV000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 查询
        List<Activate> activates = activateMapper.batchQueryById(arrayId, Activate.class);
        if (activates.isEmpty()) {
            return errors(RealBox.ACTIV002);
        }

        // 删除激活码
        activateMapper.batchDelete(arrayId, Activate.class);

        // 记录日志
        logUtils.userLog("删除激活码", request);

        // 返回
        return success();
    }

    /**
     * 激活码解绑
     *
     * @param ids 激活码ID(批量解绑)
     * @return
     */
    @Override
    public RespEntity<String> unbundledActivate(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.ACTIV000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证激活码
        List<Activate> activate = activateMapper.batchQueryById(arrayId, Activate.class);
        if (activate.isEmpty()) {
            return errors(RealBox.ACTIV003);
        }

        for (Activate act : activate) {
            // 验证终端
            Map<String, Object> cond = Maps.create().add("terminalCode", act.getName()).get();
            Terminal terminal = terminalMapper.query(cond, Terminal.class);
            if (terminal == null) {
                return errors(RealBox.TERMINAL003);
            }

            // 删除终端信息
            terminalMapper.delete(terminal.getId(), Terminal.class);

            // 修改激活状态
            cond = Maps.create().add("used", "1").get();
            activateMapper.update(act.getId(), cond, Activate.class);
        }

        // 记录日志
        logUtils.userLog("激活码解绑", request);

        return success();
    }

    /**
     * 激活码导出
     *
     * @param ids     激活码ID(一个或多个)
     * @param request 请求
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> exportActivate(String ids, HttpServletRequest request) {
        if (StringUtils.isEmpty(ids)) {
            return error(RealBox.ACTIV000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 验证激活码
        List<Activate> activates = activateMapper.batchQueryById(arrayId, Activate.class);
        if (activates.isEmpty()) {
            return error(RealBox.ACTIV003);
        }

        // 激活码字符串传ASCII
        List<String> code = new ArrayList<String>();
        for (Activate activate : activates) {
            String ascii = FileUtils.toAscii(activate.getCode());
            code.add(ascii);
        }

        // 压缩文件
        String desc = "E:" + File.separator + "resources" + File.separator + "zip" + File.separator;
        String name = "tactivationcode" + DateUtils.getDateString() + ".zip";
        FileUtils.zip(code, desc, name);

        // 记录日志
        logUtils.userLog("激活码导出", request);

        // 返回
        String address = "http://" + host + ":" + port + "/zip/" + name;
        Map<String, Object> map = Maps.create().add("desc", address).get();
        return success(map);
    }
}
