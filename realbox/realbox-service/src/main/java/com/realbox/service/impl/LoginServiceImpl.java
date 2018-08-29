/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.google.code.kaptcha.Producer;
import com.realbox.common.utils.Maps;
import com.realbox.common.utils.TokenUtils;
import com.realbox.model.Role;
import com.realbox.model.User;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.repository.mongodb.mapper.RoleMapper;
import com.realbox.repository.mongodb.mapper.UserMapper;
import com.realbox.service.LoginService;
import com.realbox.service.utils.LogUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author MJJ
 * @create Id: LoginServiceImpl.java v 0.1 2017年11月04日 上午3:55 MJJ Exp $
 **/
@Service("loginService")
public class LoginServiceImpl extends BaseService implements LoginService {

    private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private LogUtils logUtils;

    @Autowired
    private Producer producer;

    /**
     * 登录
     *
     * @param user    用户对象
     * @param request 请求
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> login(User user, HttpServletRequest request) {
        if (StringUtils.isEmpty(user.getCode())) {
            return error(RealBox.LOGIN000);
        }
        if (StringUtils.isEmpty(user.getName())) {
            return error(RealBox.USER001);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return error(RealBox.USER002);
        }
        if (StringUtils.isEmpty(user.getLanguage())) {
            return error(RealBox.USER007);
        }

        // 检测验证码
        String code = (String) request.getSession().getAttribute("code");
        if (code == null || !user.getCode().equalsIgnoreCase(code)) {
            return error(RealBox.LOGIN001);
        }

        // 检测用户
        user.setPassword(new Sha256Hash(user.getName(), user.getPassword()).toHex());
        Map<String, Object> cond = Maps.create().add("name", user.getName()).add("password", user.getPassword()).get();
        User result = userMapper.query(cond, User.class);
        if (result == null) {
            return error(RealBox.LOGIN002);
        }

        // 设置用户语言
        request.getSession().setAttribute(user.getName(), user.getLanguage());

        // 查询用户权限
        Role role = roleMapper.queryById(result.getRoleId(), Role.class);

        // 存储token到redis
        String token = TokenUtils.createToken();
        redisTemplate.opsForValue().set(user.getName(), token, 1, TimeUnit.DAYS);

        // 记录日志
        logUtils.userLog("用户登录", request);

        // 返回(权限ID、Token、用户名)
        Map<String, Object> map = Maps.create()
                .add("privId", role.getPrivId())
                .add("userId", result.getId())
                .add("name", user.getName())
                .add("token", token)
                .get();
        return success(map);
    }

    /**
     * 生成验证码
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    @Override
    public void code(HttpServletRequest request, HttpServletResponse response) {
        try {
            //生成文字验证码
            String code = producer.createText();

            // 存储验证码
            request.getSession().setAttribute("code", code);

            logger.info("================================" + code + "================================");

            //生成图片验证码
            BufferedImage image = producer.createImage(code);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            response.setContentType("image/jpeg");
            response.setHeader("Cache-Control", "no-store, no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
