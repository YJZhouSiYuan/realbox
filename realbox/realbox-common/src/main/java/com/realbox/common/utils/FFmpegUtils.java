/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.common.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author MJJ
 * @create Id: FFmpegUtils.java v 0.1 2017年12月27日 19:50 MJJ Exp $
 **/
public class FFmpegUtils {

    private static final Logger logger = Logger.getLogger(FFmpegUtils.class);

    /**
     * 视频截取图片
     *
     * @param ffmpegPath    ffmepg执行文件
     * @param videoRealPath 视频路径
     * @param imageRealName 截取图片路径
     */
    public static void makeScreenCut(String ffmpegPath, String videoRealPath, String imageRealName) {
        List<String> commend = new java.util.ArrayList<String>();
        commend.add(ffmpegPath);
        commend.add("-i");
        commend.add(videoRealPath);
        commend.add(imageRealName);
        commend.add("-ss");
        commend.add("30");          //在视频的某个插入时间截图，例子为8秒后
        commend.add("-r");
        commend.add("1");
        commend.add("-vframes");    //音质设置，越大音质越好，但文件也会变大
        commend.add("1");
        commend.add("-an");
        commend.add("-vcodec");     //截图后的图片大小
        commend.add("mjpeg");
        commend.add("-y");
//        logger.info(commend.toString());
        try {
            // 验证路径
            String file = FileUtils.clipping(imageRealName, File.separator, 1);
            FileUtils.detection(file);

            // 截取
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            process.waitFor();	// 等待子线程结束
            InputStream in = process.getInputStream();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        makeScreenCut("D:\\FFmpeg\\ffmpeg-3.4.1-win64-static\\bin\\ffmpeg", "E:\\resources\\video\\abc.mp4", "E:\\resources\\images\\humbnail\\1.jpg");
    }
}
