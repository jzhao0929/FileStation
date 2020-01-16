package com.station.file.service;

import com.station.file.dao.FileDao;
import com.station.file.entity.FileEntity;
import com.station.file.service.serviceInterface.FileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @描述：
 * @创建人： 2020-1-13 16:47 企业平台事业部/jzhao1
 */
@Service
public class FileService implements FileServiceInterface {
    @Autowired
    private FileDao fileDao;

    @Override
    public List<FileEntity> findAllFileEntity(int page, int pageSize) {
        return fileDao.findAllFileEntity(page,pageSize);
    }

    @Override
    public void saveFileEntity(String id,String fileName,String absolutePath){
        String type[] = fileName.split("\\.");
        StringBuffer sb = new StringBuffer(absolutePath);
        sb.append(File.separator);
        sb.append(id + "." + type[type.length-1]);
        FileEntity entity = new FileEntity();
        entity.setId(id);
        entity.setTrueName(fileName);
        entity.setAbsolutePath(sb.toString());
        entity.setType(type[type.length-1]);
        fileDao.save(entity);
    }

    @Override
    public void transferToService(MultipartFile fileUpload,String id,String uploadFolder){
        //获取文件的原始名
        String type[] = fileUpload.getOriginalFilename().split("\\.");
        String fileName = id + "." + type[type.length-1];
        try {
            fileUpload.transferTo(new File(uploadFolder,fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileEntity findFileEntityById(String id) {
        return fileDao.findFileEntityById(id);
    }
}
