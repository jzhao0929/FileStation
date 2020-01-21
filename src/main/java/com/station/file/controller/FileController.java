package com.station.file.controller;

import com.github.pagehelper.PageInfo;
import com.station.file.entity.FileEntity;
import com.station.file.service.serviceInterface.FileServiceInterface;
import com.station.file.util.JudgePathExist;
import com.station.file.util.ResponData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @描述：该类响应前端接口
 * @创建人： 2020-1-13 16:40 企业平台事业部/jzhao1
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${spring.servlet.multipart.maxFileSize}")
    private String fileLimitSize;
    @Resource
    private FileServiceInterface fileService;

    @GetMapping(value = "/getAll")
    public ResponData login(
            @RequestParam(value="page", required=false, defaultValue="1") int page,
            @RequestParam(value="page-size", required=false, defaultValue="5") int pageSize){
        List<FileEntity> result = fileService.findAllFileEntity(page, pageSize);
        // PageInfo包装结果，返回更多分页相关信息
        PageInfo<FileEntity> pageInfo = new PageInfo<>(result);
        Map<String,Object> map = new HashMap<>();
        map.put("page",pageInfo);
        return ResponData.success().setData(map);
    }

    @RequestMapping("/upload")
    public ResponData upload(MultipartFile fileUpload, HttpServletRequest request) throws IOException {
        //获取文件的原始名
        String fileName = fileUpload.getOriginalFilename();
        //生成文件ID
        String id = UUID.randomUUID().toString().replaceAll("-","");
        //上传文件至服务器
        fileService.transferToService(fileUpload,id,uploadFolder);
        //保存文件信息
        fileService.saveFileEntity(id,fileName,uploadFolder);
        return ResponData.success().setMsg("上传成功！");
    }

    @RequestMapping("/download")
    public ResponData download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        FileEntity fileEntity = fileService.findFileEntityById(id);
        if(fileEntity == null){
            return ResponData.failed().setMsg("文件Id不存在！");
        }
        //获取文件的绝对路径
        String realPath = fileEntity.getAbsolutePath();
        boolean flag = JudgePathExist.isJudgeFileExist(realPath);
        if(flag){
            return ResponData.failed().setMsg("文件不存在！");
        }
        String fileName = fileEntity.getTrueName();
        //获取输入流对象（用于读文件）
        FileInputStream fis = new FileInputStream(new File(realPath));
        if(fileName.contains(".")){
            //获取文件后缀
            String extendFileName = fileName.substring(fileName.lastIndexOf('.'));
            //动态设置响应类型，根据前台传递文件类型设置响应类型
            response.setContentType(request.getSession().getServletContext().getMimeType(extendFileName));
        }
        //设置响应头,attachment表示以附件的形式下载，inline表示在线打开
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        //获取输出流对象（用于写文件）
        ServletOutputStream os = response.getOutputStream();
        //下载文件,使用spring框架中的FileCopyUtils工具
        FileCopyUtils.copy(fis,os);
        return ResponData.success().setMsg("下载成功！");
    }

    @GetMapping(value = "/findFileEntityByTrueName")
    public ResponData findFileEntityByTrueName(String trueName){
        List<FileEntity> result = fileService.findFileEntityByTrueName(trueName);
        // PageInfo包装结果，返回更多分页相关信息
        PageInfo<FileEntity> pageInfo = new PageInfo<>(result);
        Map<String,Object> map = new HashMap<>();
        map.put("page",pageInfo);
        return ResponData.success().setData(map);
    }

}
