package com.example.hello.service;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 对象存储服务类
 * 提供文件上传、下载、删除等功能
 * 基于MinIO SDK实现
 */
@Service
public class ObjectStorageService {

    @Autowired
    private MinioClient minioClient;

    @Value("${object.storage.bucket}")
    private String bucket;

    @Value("${object.storage.external-endpoint}")
    private String externalEndpoint;

    /**
     * 上传文件到对象存储
     * @param fileInputStream 文件输入流
     * @param originalFilename 原始文件名
     * @param contentType 文件类型
     * @return 文件URL
     */
    public String uploadFile(InputStream fileInputStream, String originalFilename, String contentType) throws IOException {
        try {
            // 生成唯一的文件名
            String fileExtension = getFileExtension(originalFilename);
            // 教学注释：将文件上传到"jianli"文件夹下，便于分类管理
            String objectKey = "jianli/" + UUID.randomUUID().toString() + fileExtension;

            // 读取文件内容到字节数组
            byte[] fileContent = fileInputStream.readAllBytes();

            // 上传文件到MinIO
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectKey)
                    .stream(new java.io.ByteArrayInputStream(fileContent), fileContent.length, -1)
                    .contentType(contentType)
                    .build()
            );

            // 返回文件的访问URL
            return generateFileUrl(objectKey);

        } catch (Exception e) {
            throw new IOException("文件上传到对象存储失败: " + e.getMessage(), e);
        }
    }

    /**
     * 删除文件
     * @param objectKey 对象键（文件路径）
     */
    public void deleteFile(String objectKey) {
        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectKey)
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("删除文件失败: " + e.getMessage(), e);
        }
    }

    /**
     * 检查文件是否存在
     * @param objectKey 对象键（文件路径）
     * @return 是否存在
     */
    public boolean fileExists(String objectKey) {
        try {
            minioClient.statObject(
                StatObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectKey)
                    .build()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从URL中提取对象键
     * @param fileUrl 文件URL
     * @return 对象键
     */
    public String extractObjectKeyFromUrl(String fileUrl) {
        // 从URL中提取对象键，例如从 https://xxx/jianli/xxx.pdf 提取 jianli/xxx.pdf
        if (fileUrl.contains("/jianli/")) {
            return fileUrl.substring(fileUrl.indexOf("/jianli/"));
        }
        return null;
    }

    /**
     * 生成文件访问URL
     * @param objectKey 对象键
     * @return 文件URL
     */
    private String generateFileUrl(String objectKey) {
        // 使用外部端点生成可访问的URL
        return externalEndpoint + "/" + bucket + "/" + objectKey;
    }

    /**
     * 获取文件扩展名
     * @param filename 文件名
     * @return 文件扩展名
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex);
    }

    /**
     * 验证文件类型
     * @param filename 文件名
     * @return 是否为允许的文件类型
     */
    public boolean isValidFileType(String filename) {
        if (filename == null) {
            return false;
        }
        String extension = getFileExtension(filename).toLowerCase();
        return extension.equals(".pdf") || extension.equals(".docx") || extension.equals(".doc");
    }

    /**
     * 验证文件大小
     * @param fileSize 文件大小（字节）
     * @param maxSizeMB 最大大小（MB）
     * @return 是否为允许的文件大小
     */
    public boolean isValidFileSize(long fileSize, int maxSizeMB) {
        long maxSizeBytes = maxSizeMB * 1024 * 1024L;
        return fileSize <= maxSizeBytes;
    }
} 