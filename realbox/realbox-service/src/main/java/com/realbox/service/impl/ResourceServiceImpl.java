/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.common.utils.*;
import com.realbox.model.Resource;
import com.realbox.model.User;
import com.realbox.model.entity.RespEntity;
import com.realbox.model.enums.RealBox;
import com.realbox.model.enums.Res;
import com.realbox.repository.mongodb.mapper.ResourceMapper;
import com.realbox.repository.mongodb.mapper.UserMapper;
import com.realbox.service.ResourceService;
import com.realbox.service.utils.LogUtils;
import com.realbox.service.utils.TreeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MJJ
 * @create Id: ResourceServiceImpl.java v 0.1 2017年12月05日 下午10:00 MJJ Exp $
 **/
@Service("resourceService")
public class ResourceServiceImpl extends BaseService implements ResourceService {

    private static Logger logger = Logger.getLogger(ResourceServiceImpl.class);

    // Nginx服务器地址
    @Value("${nginx.host}")
    String host;

    // Nginx端口
    @Value("${nginx.port}")
    String port;

    // 图片存储路径
    @Value("${images}")
    String images;

    // 视频存储路径
    @Value("${video}")
    String video;

    // 音乐存储地址
    @Value("${audio}")
    String audio;

    // 文本存储地址
    @Value("${text}")
    String text;

    // 滚动字幕存储地址
    @Value("${scroll}")
    String scroll;

    // 缩略图存储路径
    @Value("${thumbnail}")
    String thumbnail;

    // ffmpeg执行文件路径
    @Value("${ffmpeg}")
    String ffmpeg;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TreeUtils treeUtils;

    @Autowired
    private LogUtils logUtils;

    /**
     * 查询资源
     *
     * @param groupId   组ID(资源管理树ID)
     * @param name      资源名
     * @param pageNo    当前页数
     * @param pageCount 页面显示数量
     * @return
     */
    @Override
    public RespEntity<Map<String, Object>> queryResource(String groupId, String name, Integer pageNo, Integer pageCount) {
        if (StringUtils.isEmpty(groupId)) {
            return error(RealBox.TRERES000);
        }
        if (StringUtils.isEmpty(pageNo)) {
            return error(RealBox.PAGE000);
        }
        if (StringUtils.isEmpty(pageCount)) {
            return error(RealBox.PAGE001);
        }

        // 查询数量
        Map<String, Object> cond = Maps.create().add("groupId", groupId).add("name", name).get();
        long count = resourceMapper.count(cond, Resource.class);

        // 分页
        Pages pages = new Pages(pageNo, pageCount, count);

        // 查询
        List<Resource> resources = resourceMapper.queryList(cond, pages, Resource.class);

        // 返回
        Map<String, Object> map = Maps.create().add("resources", resources).add("pages", pages).get();
        return success(map);
    }

    /**
     * 上传资源
     *
     * @param groupId  组ID(资源管理树ID)
     * @param uploader 上传者ID
     * @param type     资源类型
     * @param file     上传文件
     * @return
     */
    @Override
    public RespEntity<String> uploadResource(String groupId, String uploader, String type, MultipartFile file) {
        if (StringUtils.isEmpty(groupId)) {
            return errors(RealBox.TRERES000);
        }
        if (StringUtils.isEmpty(uploader)) {
            return errors(RealBox.RESOURCE001);
        }
        if (StringUtils.isEmpty(type)) {
            return errors(RealBox.RESOURCE003);
        }
        // 验证用户
        User user = userMapper.queryById(uploader, User.class);
        if (user == null) {
            return errors(RealBox.USER005);
        }
        // 验证资源结构树、验证资源、上传文件、视频截图、设置资源参数
        Resource resource = new Resource();
        RespEntity<String> value = checkResource(groupId, uploader, type, file, resource);
        if (value != null) {
            return value;
        }
        // 存储
        resourceMapper.create(resource);
        // 记录日志
        logUtils.userLog("上传资源", request);
        // 返回
        return success();
    }

    /**
     * 删除资源
     *
     * @param ids     ID(List集合)
     * @param request 请求
     * @return
     */
    @Override
    public RespEntity<String> deleteResource(String ids, HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        if (cookies.length > 1) {
            for (Cookie cookie : cookies){
                System.out.println(cookie.getName());
            }
        }
        if (StringUtils.isEmpty(ids)) {
            return errors(RealBox.RESOURCE000);
        }

        // 字符串转数组
        List<String> arrayId = Arrays.asList(ids.split(" "));

        // 查询资源
        List<Resource> resources = resourceMapper.batchQueryById(arrayId, Resource.class);
        if (resources.isEmpty()) {
            return errors(RealBox.RESOURCE002);
        }

        // 删除资源
        for (Resource resource : resources) {
            // 删除文件
            String path = resource.getAbsolute();
            FileUtils.deleteFile(path);

            // 删除缩略图
            Res res = Res.getRes(resource.getType());
            int index = resource.getThumbnail().lastIndexOf(File.separator) + 1;
            String fileName = resource.getThumbnail().substring(index, resource.getThumbnail().length());
            switch (res) {
                case VIDEO:
                    FileUtils.deleteFile(thumbnail + fileName);
                    break;
                default:
                    break;
            }
        }

        // 删除数据
        resourceMapper.batchDelete(arrayId, Resource.class);

        // 记录日志
        logUtils.userLog("删除资源", request);

        // 返回
        return success();
    }

    /**
     * 验证资源结构树、验证资源、上传文件、视频截图、设置资源参数
     *
     * @param groupId  组ID(资源管理树ID)
     * @param uploader 上传者ID
     * @param type     资源类型
     * @param file     上传文件
     * @param resource 资源对象
     * @return
     */
    private RespEntity<String> checkResource(String groupId, String uploader, String type, MultipartFile file, Resource resource) {
        // 名称(带后缀)
        String url = file.getOriginalFilename();
        // 名称
        String name = url.substring(0, url.lastIndexOf("."));

        // 验证资源类型
        Res res = Res.getRes(type);
        if (res == null) {
            return errors(RealBox.RESOURCE004);
        }

        boolean flag = true;        // 标记
        Resource result = null;     // 资源
        String absolute = null;     // 绝对路径
        Map<String, Object> cond = Maps.create().add("name", url).add("type", type).get();
        switch (res) {
            case IMAGE:
                // 验证结构树
                flag = treeUtils.checkTree("1", groupId);
                if (flag) {
                    return errors(RealBox.TRERES001);
                }

                // 验证资源
                result = resourceMapper.query(cond, Resource.class);
                if (result != null) {
                    return errors(RealBox.RESOURCE005);
                }

                // 设置资源参数
                String imageThumbnail = type + File.separator + url;    // 缩略图
                absolute = thumbnail + type + File.separator + url;     // 绝对路径
                setResource(groupId, uploader, type, url, imageThumbnail, absolute, file, resource);
                UDUtils.upload(file, images);
                break;
            case VIDEO:
                // 验证结构树
                flag = treeUtils.checkTree("2", groupId);
                if (flag) {
                    return errors(RealBox.TRERES001);
                }

                // 验证资源
                result = resourceMapper.query(cond, Resource.class);
                if (result != null) {
                    return errors(RealBox.RESOURCE005);
                }

                // 设置资源参数
                String videoName = name + "_VideoThumbnail.jpg";
                String videoThumbnail = "thumbnail" + File.separator + videoName;   // 缩略图
                absolute = thumbnail + type + File.separator + url;                 // 绝对路径
                setResource(groupId, uploader, type, url, videoThumbnail, absolute, file, resource);

                System.out.println(videoThumbnail);
                System.out.println(absolute);

                // 上传文件
                UDUtils.upload(file, video);

                // 视频路径
                String videoPath = video + file.getOriginalFilename();

                // 缩略图(存储到服务器)
                String thumbnailService = thumbnail + videoName;

                // 视频截图
                FFmpegUtils.makeScreenCut(ffmpeg, videoPath, thumbnailService);
                break;
            case AUDIO:
                // 验证结构树
                flag = treeUtils.checkTree("3", groupId);
                if (flag) {
                    return errors(RealBox.TRERES001);
                }

                // 验证资源
                result = resourceMapper.query(cond, Resource.class);
                if (result != null) {
                    return errors(RealBox.RESOURCE005);
                }

                // 设置资源参数
                String audioThumbnail = "thumbnail" + File.separator + "audioThumbnail.png";    // 缩略图
                absolute = thumbnail + type + File.separator + url;                 // 绝对路径
                setResource(groupId, uploader, type, url, audioThumbnail, absolute, file, resource);

                // 上传文件
                UDUtils.upload(file, audio);
                break;
            case TEXT:
                // 验证结构树
                flag = treeUtils.checkTree("4", groupId);
                if (flag) {
                    return errors(RealBox.TRERES001);
                }

                // 验证资源
                result = resourceMapper.query(cond, Resource.class);
                if (result != null) {
                    return errors(RealBox.RESOURCE005);
                }

                // 设置资源参数
                String textThumbnail = "thumbnail" + File.separator + "textThumbnail.png"; // 缩略图
                absolute = thumbnail + type + File.separator + url;                 // 绝对路径
                setResource(groupId, uploader, type, url, textThumbnail, absolute, file, resource);

                // 上传文件
                UDUtils.upload(file, text);
                break;
            case SCROLL:
                // 验证结构树
                flag = treeUtils.checkTree("5", groupId);
                if (flag) {
                    return errors(RealBox.TRERES001);
                }

                // 验证资源
                result = resourceMapper.query(cond, Resource.class);
                if (result != null) {
                    return errors(RealBox.RESOURCE005);
                }

                // 设置资源参数
                String scrollThumbnail = "thumbnail" + File.separator + "scrollThumbnail.png";  // 缩略图
                absolute = thumbnail + type + File.separator + url;                             // 绝对路径
                setResource(groupId, uploader, type, url, scrollThumbnail, absolute, file, resource);

                // 上传文件
                UDUtils.upload(file, scroll);
                break;
            default:
                return errors(RealBox.RESOURCE004);
        }
        return null;
    }

    /**
     * 设置资源参数
     *
     * @param groupId   组ID(资源管理树ID)
     * @param uploader  上传者ID
     * @param type      资源类型
     * @param url       名称(带后缀)
     * @param thumbnail 缩略图路径
     * @param absolute  绝对路径
     * @param file      上传文件
     * @param resource  资源对象
     */
    private void setResource(String groupId,
                             String uploader,
                             String type,
                             String url,
                             String thumbnail,
                             String absolute,
                             MultipartFile file,
                             Resource resource) {
        // 服务器地址
        String local = host + ":" + port + File.separator;
        // 分辨率
        String resolution = null;
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                resolution = width + "*" + height;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 名称
        resource.setName(url);
        // 资源类型
        resource.setType(type);
        // 文件下载地址
        resource.setUrl(local + type + File.separator + url);
        // 视频缩略图
        resource.setThumbnail(local + thumbnail);
        // 绝对路径
        resource.setAbsolute(absolute);
        // 分辨率
        resource.setResolution(resolution);
        // 大小
        resource.setSize(file.getSize() / 1024);
        // 上传者ID
        resource.setUpgrader(uploader);
        // 上传时间
        resource.setUploadTime(DateUtils.getDateTime());
        // 上传方式
        resource.setProtocol("http");
        // 树ID
        resource.setGroupId(groupId);
    }
}
