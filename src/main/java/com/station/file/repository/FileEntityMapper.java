package com.station.file.repository;

import com.station.file.entity.FileEntity;

public interface FileEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileEntity record);

    int insertSelective(FileEntity record);

    FileEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileEntity record);

    int updateByPrimaryKey(FileEntity record);
}