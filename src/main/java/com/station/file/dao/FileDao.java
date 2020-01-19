package com.station.file.dao;

import com.github.pagehelper.PageHelper;
import com.station.file.entity.FileEntity;
import com.station.file.repository.FileEntityMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * @描述：文件持久层
 * @创建人： 2020-1-13 16:39 企业平台事业部/jzhao1
 */
@Repository
public class FileDao {
    @Resource
    private FileEntityMapper fileEntityMapper;

    public  List<FileEntity> findAllFileEntity(int page, int pageSize) {
        PageHelper.startPage(page, pageSize); //每页的大小为pageSize，查询第page页的结果
        List<FileEntity>  list = fileEntityMapper.selectAllFileEntity();
        return list;
    }

    public void save(FileEntity fileEntity){
        fileEntityMapper.insertSelective(fileEntity);
    }

    public FileEntity findFileEntityById(String id){
        return fileEntityMapper.selectByPrimaryKey(id);
    }
}
