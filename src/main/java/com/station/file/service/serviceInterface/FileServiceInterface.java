package com.station.file.service.serviceInterface;

import com.station.file.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @描述：文件服务层
 * @创建人： 2020-1-13 16:49 企业平台事业部/jzhao1
 */
public interface FileServiceInterface {
    /**
     * 获取所有的文件列表
     * @param page
     * @param pageSize
     * @return
     */
    public List<FileEntity> findAllFileEntity(int page, int pageSize);

    /**
     * 保存文件信息
     * @param id
     * @param fileName
     * @param absolutePath
     */
    public void saveFileEntity(String id,String fileName,String absolutePath);

    /**
     * 通过ID获取文件信息
     * @param id
     * @return
     */
    public FileEntity findFileEntityById(String id);

    /**
     * 将文件上传到服务器中，并将id作为文件名以防文件名重名
     * @param fileUpload
     * @param id
     * @param uploadFolder
     */
    public void transferToService(MultipartFile fileUpload, String id, String uploadFolder);
}
