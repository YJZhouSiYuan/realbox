/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author MJJ
 * @create Id: UDUtils.java v 0.1 2017年12月05日 下午10:02 MJJ Exp $
 * 上传、下载
 **/
public class UDUtils {

    /**
     * 上传
     *
     * @param multipartFile 资源文件
     * @param descFile      存储路径
     */
    public static void upload(MultipartFile multipartFile, String descFile) {
        try {
            // 验证路径
            FileUtils.detection(descFile);

            // 上传
            File path = new File(descFile, multipartFile.getOriginalFilename());
            multipartFile.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载
     *
     * @param descFile 下载路径
     * @param response 响应
     */
    public static void download(String descFile, HttpServletResponse response) {
        File file = new File(descFile);
        // 路径是否存储(不存在创建)
        if (!file.exists()) {
            // 获取当前系统(如果是Linux系统,添加创建文件夹权限)
            if (System.getProperty("os.name").startsWith("Linux")) {
                file.setWritable(true, false);
            }
            // 创建
            file.mkdirs();

            // 下载
            InputStream input = null;
            OutputStream out = null;
            try {
                // 读取
                input = new FileInputStream(file);
                int length = 0;
                byte[] bytes = new byte[1024];
                out = response.getOutputStream();
                while ((length = input.read(bytes)) > 0) {
                    out.write(bytes, 0, length);
                }

                // 响应
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
