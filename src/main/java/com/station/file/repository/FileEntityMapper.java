package com.station.file.repository;

import com.station.file.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface FileEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileEntity record);

    int insertSelective(FileEntity record);

    FileEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileEntity record);

    int updateByPrimaryKey(FileEntity record);

    List<FileEntity> selectAllFileEntity();

    @Select("SELECT * FROM file_message WHERE true_name like concat('%',#{trueName},'%') ")
    List<FileEntity> findFileEntityByTrueName(String trueName);
}