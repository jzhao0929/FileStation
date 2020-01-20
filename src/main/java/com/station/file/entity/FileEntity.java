package com.station.file.entity;

import java.util.Date;

public class FileEntity {
    private String id;

    private String trueName;

    private String absolutePath;

    private String type;

    private Date uploadTime;

    public FileEntity(String id, String trueName, String absolutePath, String type, Date uploadTime) {
        this.id = id;
        this.trueName = trueName;
        this.absolutePath = absolutePath;
        this.type = type;
        this.uploadTime = uploadTime;
    }

    public FileEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath == null ? null : absolutePath.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}