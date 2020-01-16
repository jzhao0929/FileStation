package com.station.file.dao;

import com.github.pagehelper.PageHelper;
import com.station.file.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @描述：文件持久层
 * @创建人： 2020-1-13 16:39 企业平台事业部/jzhao1
 */
@Repository
public class FileDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public  List<FileEntity> findAllFileEntity(int page, int pageSize) {
        PageHelper.startPage(page, pageSize); //每页的大小为pageSize，查询第page页的结果
        String sql = "SELECT * FROM file_message";
        // 通过jdbcTemplate查询数据库
        List<FileEntity>  list=  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(FileEntity.class));
        return list;
    }

    public void save(FileEntity fileEntity){
        String sql = "INSERT INTO file_message VALUES (?, ?, ?,?)";
        jdbcTemplate.update(sql,new Object[]{fileEntity.getId(),fileEntity.getTrueName(),fileEntity.getAbsolutePath(),fileEntity.getType()});
    }

    public FileEntity findFileEntityById(String id){
        String sql = "SELECT * FROM file_message WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(FileEntity.class));
    }
}
