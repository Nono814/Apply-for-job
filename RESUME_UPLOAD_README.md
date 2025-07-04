# 简历上传功能说明文档

## 功能概述

本系统实现了基于对象存储的简历文件上传功能，支持PDF、DOCX、DOC格式的简历文件上传，文件存储在Sealos对象存储服务中。

## 技术架构

### 1. 对象存储服务
- **服务提供商**: Sealos对象存储
- **存储桶**: `tqd6953k-malaxiangguo`
- **内部端点**: `http://object-storage.objectstorage-system.svc.cluster.local`
- **外部端点**: `https://objectstorageapi.hzh.sealos.run`

### 2. 技术栈
- **后端框架**: Spring Boot 3.3.2
- **对象存储SDK**: AWS S3 SDK 2.24.12
- **数据库**: MySQL
- **ORM框架**: MyBatis

## 接口说明

### 上传简历接口

**接口地址**: `POST /api/resumes/upload`

**请求类型**: `multipart/form-data`

**请求参数**:
| 参数名   | 类型   | 必填 | 说明     |
|----------|--------|------|----------|
| user_id  | int    | 是   | 用户ID   |
| file     | file   | 是   | 简历文件 |

**支持的文件格式**:
- PDF (.pdf)
- Word文档 (.docx, .doc)

**文件大小限制**: 最大3MB

**返回示例**:
```json
{
  "code": 200,
  "message": "简历上传成功",
  "data": {
    "resume_id": 2,
    "file_url": "https://objectstorageapi.hzh.sealos.run/tqd6953k-malaxiangguo/resumes/xxx.pdf"
  }
}
```

## 核心组件

### 1. ObjectStorageConfig
对象存储配置类，负责配置S3客户端连接参数。

**主要功能**:
- 配置访问密钥和密钥
- 设置内部和外部端点
- 创建S3客户端实例

### 2. ObjectStorageService
对象存储服务类，提供文件操作的核心功能。

**主要方法**:
- `uploadFile()`: 上传文件到对象存储
- `deleteFile()`: 删除文件
- `fileExists()`: 检查文件是否存在
- `isValidFileType()`: 验证文件类型
- `isValidFileSize()`: 验证文件大小

### 3. ResumeService
简历服务类，整合对象存储和数据库操作。

**主要功能**:
- 文件类型和大小验证
- 调用对象存储服务上传文件
- 保存简历信息到数据库
- 管理简历的默认状态

### 4. ResumeController
简历控制器，提供REST API接口。

**主要接口**:
- `POST /api/resumes/upload`: 上传简历
- `GET /api/users/{user_id}/resumes`: 获取用户简历列表

## 配置说明

### application.yml配置
```yaml
# 对象存储配置
object:
  storage:
    access-key: tqd6953k
    secret-key: rlnqfq8c77tz9phq
    endpoint: http://object-storage.objectstorage-system.svc.cluster.local
    external-endpoint: https://objectstorageapi.hzh.sealos.run
    bucket: tqd6953k-malaxiangguo

# 文件上传配置
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
```

## 数据库表结构

### resumes表
```sql
CREATE TABLE resumes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    file_path VARCHAR(500) NOT NULL,  -- 存储文件URL
    original_filename VARCHAR(255) NOT NULL,
    file_size INT NOT NULL,
    is_default BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

## 使用示例

### 1. 使用curl上传文件
```bash
curl -X POST \
  -F "user_id=1" \
  -F "file=@resume.pdf" \
  http://localhost:8080/api/resumes/upload
```

### 2. 使用测试脚本
```bash
./test_resume_upload.sh
```

## 安全考虑

### 1. 文件类型验证
- 只允许PDF、DOCX、DOC格式
- 通过文件扩展名进行验证

### 2. 文件大小限制
- 最大文件大小限制为3MB
- 防止大文件上传攻击

### 3. 文件名安全
- 使用UUID生成唯一文件名
- 避免文件名冲突和路径遍历攻击

### 4. 访问控制
- 建议添加用户认证和授权
- 验证用户对简历的访问权限

## 错误处理

### 常见错误及解决方案

1. **文件类型不支持**
   - 错误: "不支持的文件类型，仅支持PDF、DOCX、DOC格式"
   - 解决: 确保上传文件格式正确

2. **文件大小超限**
   - 错误: "文件大小超过限制，最大支持3MB"
   - 解决: 压缩文件或选择更小的文件

3. **对象存储连接失败**
   - 错误: "文件上传到对象存储失败"
   - 解决: 检查网络连接和对象存储配置

## 最佳实践

### 1. 文件命名规范
- 使用UUID生成唯一文件名
- 保留原始文件扩展名
- 按功能分类存储（如resumes/目录）

### 2. 错误处理
- 提供详细的错误信息
- 记录操作日志
- 实现重试机制

### 3. 性能优化
- 使用流式处理大文件
- 实现文件上传进度显示
- 考虑异步处理

### 4. 监控和维护
- 监控存储空间使用情况
- 定期清理过期文件
- 备份重要数据

## 扩展功能

### 1. 文件预览
- 集成PDF预览功能
- 支持在线查看简历

### 2. 文件版本管理
- 支持简历版本控制
- 保留历史版本

### 3. 批量操作
- 支持批量上传
- 批量删除功能

### 4. 文件压缩
- 自动压缩大文件
- 优化存储空间

## 总结

本简历上传功能采用对象存储架构，具有以下优势：

1. **高可用性**: 对象存储服务提供99.9%的可用性
2. **可扩展性**: 支持海量文件存储
3. **安全性**: 提供访问控制和加密功能
4. **成本效益**: 按使用量计费，成本可控
5. **易维护**: 无需管理本地存储设备

通过合理的架构设计和错误处理，确保了系统的稳定性和用户体验。 