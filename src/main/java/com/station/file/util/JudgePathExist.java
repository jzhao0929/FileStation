package com.station.file.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

/**
 * @描述：判断是否文件服务器文件夹是否存在，不存在则创建
 * @创建人： 2020-1-21 9:04 企业平台事业部/jzhao1
 */
public final class JudgePathExist {
    private static Logger logger = LogManager.getLogger(JudgePathExist.class);

    public static void isJudgePathExist(String dirPath) {
        File file =new File(dirPath);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        } else {
            logger.error("文件服务器目录存在！");
        }
    }
}
