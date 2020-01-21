package com.station.file.config;

import com.station.file.util.ResponData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import java.io.IOException;

/**
 * @描述：
 * @创建人： 2020-1-21 14:58 企业平台事业部/jzhao1
 */
@RestControllerAdvice
public class FileExceptionHandler {
    private static Logger logger = LogManager.getLogger(FileExceptionHandler.class);
    @Value("${spring.servlet.multipart.maxFileSize}")
    private String fileLimitSize;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponData uploadException(MaxUploadSizeExceededException e) throws IOException {
        long limitSize = Long.parseLong(fileLimitSize)/1024/1024;
        logger.error("最大上传文件为"+ limitSize +"M，上传文件大小超出限制!");
        return ResponData.failed().setMsg("最大上传文件为"+ limitSize +"M，上传文件大小超出限制!");
    }

}
