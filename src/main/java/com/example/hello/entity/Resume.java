package com.example.hello.entity;

import java.time.LocalDateTime;

/**
 * 简历实体类
 */
public class Resume {
    private Integer id;
    private Integer userId;
    private String filePath;
    private String originalFilename;
    private Integer fileSize;
    private Boolean isDefault;
    private LocalDateTime uploadedAt;
    private String fileHash; // 文件内容哈希值，用于去重

    // 构造函数
    public Resume() {}

    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", userId=" + userId +
                ", filePath='" + filePath + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", fileSize=" + fileSize +
                ", isDefault=" + isDefault +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
} 