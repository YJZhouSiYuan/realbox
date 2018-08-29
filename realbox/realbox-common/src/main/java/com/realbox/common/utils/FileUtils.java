/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.common.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author MJJ
 * @create Id: FileUtils.java v 0.1 2017年12月05日 下午10:06 MJJ Exp $
 **/
public class FileUtils {
    /**
     * 读取文件内容
     *
     * @param descFile 文件路径
     * @return JSON字符串
     */
    public static String read(String descFile) {
        // 存储json字符串
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            File file = new File(descFile);
            reader = new BufferedReader(new FileReader(file));
            // 临时存储一行字符串
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 一行一行的存储
                buffer.append(tempString);
            }
            // 返回字符串
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }


    /**
     * 字符串写入文件
     *
     * @param descFile 文件路径
     * @param body     字符内容
     */
    public static void writeFile(String descFile, String body) {
        // 检测路径
        String path = FileUtils.clipping(descFile, File.separator, 1);
        detection(path);

        // 写入
        FileOutputStream out = null;
        try {
            byte[] bytes = body.getBytes();
            out = new FileOutputStream(descFile);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取项目路径
     *
     * @param request 请求
     * @return
     */
    public static String path(HttpServletRequest request) {
        String descFile = request.getSession().getServletContext().getRealPath("/");
        if (descFile != null) {
            return clipping(descFile, File.separator, 2) + File.separator;
        }
        return null;
    }

    /**
     * 截取路径(.,/)
     *
     * @param path   路径
     * @param param  截取内容
     * @param number 截取次数
     * @return
     */
    public static String clipping(String path, String param, int number) {
        for (int i = 0; i < number; i++) {
            path = path.substring(0, path.lastIndexOf(param));
        }
        return path;
    }

    /**
     * 检测路径
     *
     * @param descFile 路径
     * @return
     */
    public static void detection(String descFile) {
        File file = new File(descFile);
        // 路径不存在创建
        if (!file.exists()) {
            // 获取当前系统(如果是Linux系统,添加创建文件夹权限)
            if (System.getProperty("os.name").startsWith("Linux")) {
                file.setWritable(true, false);
            }
            // 创建
            file.mkdirs();
        }
    }

    /**
     * 删除文件
     *
     * @param descFile 文件路径
     * @return
     */
    public static boolean deleteFile(String descFile) {
        File file = new File(descFile);
        if (!file.exists()) {
            return false;
        }
        boolean result = file.delete();
        while (!result) {
            System.gc();
            result = file.delete();
        }
        return result;
    }

    /**
     * 压缩字符串为ZIP
     *
     * @param data 数据
     * @param desc 存储路径
     * @param name 文件名
     * @return
     */
    public static void zip(List<String> data, String desc, String name) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // 循环遍历数据
        for (String str : data) {
            // 文件路径
            String fileName = str + File.separator + "active.bin";
            // 读取数据内容
            StringWriter sw = new StringWriter();
            sw.write(str);
            // 压缩
            try {
                zip.putNextEntry(new ZipEntry(fileName));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 关闭
        IOUtils.closeQuietly(zip);

        // 读取文件到指定目录
        OutputStream out = null;
        try {
            // 验证路径
            FileUtils.detection(desc);
            // 读取文件
            out = new FileOutputStream(desc + name);
            out.write(outputStream.toByteArray());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符串转ASCII
     *
     * @param value 字符串
     * @return
     */
    public static String toAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }
}
