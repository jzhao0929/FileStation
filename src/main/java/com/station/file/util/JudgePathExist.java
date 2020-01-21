package com.station.file.util;

import java.io.File;

/**
 * @描述：判断是否文件服务器文件夹是否存在，不存在则创建
 * @创建人： 2020-1-21 9:04 企业平台事业部/jzhao1
 */
public final class JudgePathExist {
    public static void isJudgePathExist(String dirPath) {
        File file =new File(dirPath);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        } else {
            System.out.println("文件服务器目录存在！");
        }
    }
}
