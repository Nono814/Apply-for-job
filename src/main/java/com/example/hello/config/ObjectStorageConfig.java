package com.example.hello.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对象存储配置类
 * 用于配置Sealos对象存储服务的连接
 * 基于MinIO SDK实现
 */
@Configuration
public class ObjectStorageConfig {

    @Value("${object.storage.access-key}")
    private String accessKey;

    @Value("${object.storage.secret-key}")
    private String secretKey;

    @Value("${object.storage.endpoint}")
    private String endpoint;

    @Value("${object.storage.bucket}")
    private String bucket;

    /**
     * 创建MinIO客户端
     * 用于与对象存储服务进行交互
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * 获取存储桶名称
     */
    public String getBucket() {
        return bucket;
    }
} 