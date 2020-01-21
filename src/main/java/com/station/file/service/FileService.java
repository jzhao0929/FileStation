package com.station.file.service;

import com.station.file.dao.FileDao;
import com.station.file.entity.FileEntity;
import com.station.file.service.serviceInterface.FileServiceInterface;
import com.station.file.util.JudgePathExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @描述：
 * @创建人： 2020-1-13 16:47 企业平台事业部/jzhao1
 */
@Service
@Transactional
public class FileService implements FileServiceInterface {
    @Autowired
    private FileDao fileDao;

    @Override
    public List<FileEntity> findAllFileEntity(int page, int pageSize) {
        return fileDao.findAllFileEntity(page,pageSize);
    }

    @Override
    public void saveFileEntity(String id,String fileName,String absolutePath){
        boolean flag = fileName.contains(".");
        String type[] = null;
        StringBuffer sb = new StringBuffer(absolutePath);
        sb.append(File.separator);
        FileEntity entity = new FileEntity();
        entity.setId(id);
        entity.setTrueName(fileName);
        if(flag){
            type = fileName.split("\\.");
            sb.append(id + "." + type[type.length-1]);
            entity.setType(type[type.length-1]);
        }else{
            sb.append(id);
        }
        entity.setUploadTime(new Date());
        entity.setAbsolutePath(sb.toString());
        fileDao.save(entity);
    }

    @Override
    public void transferToService(MultipartFile fileUpload,String id,String uploadFolder){
        //获取文件的原始名
        String type[] = null;
        String fileName = null;
        boolean flag = fileUpload.getOriginalFilename().contains(".");
        if(flag){
            type = fileUpload.getOriginalFilename().split("\\.");
            fileName = id + "." + type[type.length-1];
        }else{
            fileName = id;
        }
        try {
            JudgePathExist.isJudgePathExist(uploadFolder);
            fileUpload.transferTo(new File(uploadFolder,fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileEntity findFileEntityById(String id) {
        return fileDao.findFileEntityById(id);
    }

    @Override
    public List<FileEntity> findFileEntityByTrueName(String trueName){
        List<FileEntity> list = fileDao.findFileEntityByTrueName(trueName);
        return list;
    }
}
