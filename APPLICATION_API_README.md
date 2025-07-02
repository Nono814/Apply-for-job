# 申请职位接口文档

## 概述

申请职位接口是职位申请系统的核心功能，提供了完整的职位申请管理功能，包括申请提交、状态查询、列表管理等功能。

## 功能特性

### 1. 申请管理
- ✅ 提交职位申请
- ✅ 查询申请状态
- ✅ 取消申请
- ✅ 更新申请状态

### 2. 查询功能
- ✅ 根据申请人ID查询申请列表
- ✅ 根据职位ID查询申请列表
- ✅ 根据状态查询申请列表
- ✅ 分页查询所有申请

### 3. 数据验证
- ✅ 防止重复申请同一职位
- ✅ 参数验证和错误处理
- ✅ 状态值验证

## 接口列表

### 1. 提交职位申请
- **路径**: `POST /api/applications`
- **功能**: 提交新的职位申请
- **参数**: jobId, applicantId, resumeId, coverLetter(可选)

### 2. 获取申请状态
- **路径**: `GET /api/applications/{application_id}`
- **功能**: 根据申请ID获取申请状态和详情

### 3. 根据申请人查询
- **路径**: `GET /api/applications/applicant/{applicant_id}`
- **功能**: 获取指定用户的所有申请记录

### 4. 根据职位查询
- **路径**: `GET /api/applications/job/{job_id}`
- **功能**: 获取指定职位的所有申请记录

### 5. 根据状态查询
- **路径**: `GET /api/applications/status/{status}`
- **功能**: 获取指定状态的所有申请记录

### 6. 更新申请状态
- **路径**: `PUT /api/applications/{application_id}/status`
- **功能**: 更新申请状态（HR操作）

### 7. 分页查询
- **路径**: `GET /api/applications?page=1&size=10`
- **功能**: 分页获取所有申请记录

### 8. 取消申请
- **路径**: `DELETE /api/applications/{application_id}`
- **功能**: 取消申请（申请人操作）

## 申请状态说明

| 状态 | 说明 | 操作权限 |
|------|------|----------|
| pending | 待处理 | 申请人可取消，HR可更新状态 |
| reviewing | 审核中 | HR可更新状态 |
| accepted | 已接受 | HR可更新状态 |
| rejected | 已拒绝 | HR可更新状态 |
| cancelled | 已取消 | 申请人操作 |

## 数据模型

### ApplicationRequest (申请请求)
```java
{
  "jobId": Integer,        // 职位ID（必填）
  "applicantId": Integer,  // 申请人ID（必填）
  "resumeId": Integer,     // 简历ID（必填）
  "coverLetter": String    // 求职信（可选）
}
```

### ApplicationResponse (申请响应)
```java
{
  "id": Integer,                    // 申请ID
  "jobId": Integer,                 // 职位ID
  "jobTitle": String,               // 职位标题
  "companyName": String,            // 公司名称
  "applicantId": Integer,           // 申请人ID
  "applicantName": String,          // 申请人姓名
  "resumeId": Integer,              // 简历ID
  "resumeFilename": String,         // 简历文件名
  "status": String,                 // 申请状态
  "submittedAt": LocalDateTime,     // 提交时间
  "coverLetter": String             // 求职信
}
```

## 使用示例

### 1. 提交申请
```bash
curl -X POST http://localhost:8080/api/applications \
  -H "Content-Type: application/json" \
  -d '{
    "jobId": 1,
    "applicantId": 1,
    "resumeId": 1,
    "coverLetter": "我对这个职位很感兴趣，希望有机会加入贵公司。"
  }'
```

### 2. 查询申请状态
```bash
curl -X GET http://localhost:8080/api/applications/1
```

### 3. 获取用户申请列表
```bash
curl -X GET http://localhost:8080/api/applications/applicant/1
```

### 4. 更新申请状态
```bash
curl -X PUT "http://localhost:8080/api/applications/1/status?status=reviewing"
```

## 错误处理

### 常见错误码
- `400`: 参数错误或重复申请
- `404`: 申请记录不存在
- `500`: 服务器内部错误

### 错误响应格式
```json
{
  "code": 400,
  "message": "您已经申请过该职位",
  "data": null
}
```

## 业务规则

1. **防重复申请**: 同一用户对同一职位只能申请一次
2. **状态流转**: 申请状态只能按预设流程更新
3. **权限控制**: 不同角色有不同的操作权限
4. **数据完整性**: 申请必须关联有效的职位、用户和简历

## 技术实现

### 技术栈
- **框架**: Spring Boot 3.3.2
- **ORM**: MyBatis 3.0.3
- **数据库**: MySQL 8.0
- **分页**: PageHelper 1.4.7
- **验证**: Spring Validation

### 核心组件
- `ApplicationController`: 控制器层
- `ApplicationService`: 业务逻辑层
- `ApplicationMapper`: 数据访问层
- `Application`: 实体类
- `ApplicationRequest/Response`: 数据传输对象

### 数据库表结构
```sql
CREATE TABLE applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    job_id INT NOT NULL,
    user_id INT NOT NULL,
    resume_id INT NOT NULL,
    status VARCHAR(20) DEFAULT 'pending',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (resume_id) REFERENCES resumes(id),
    UNIQUE KEY unique_application (job_id, user_id)
);
```

## 测试

运行测试脚本：
```bash
./test_application_api.sh
```

## 部署说明

1. 确保MySQL数据库已启动
2. 检查数据库连接配置
3. 启动Spring Boot应用
4. 验证接口可用性

## 更新日志

### v1.0.0 (2025-06-29)
- ✅ 实现基础申请功能
- ✅ 添加状态管理
- ✅ 实现查询接口
- ✅ 添加分页支持
- ✅ 完善错误处理
- ✅ 创建API文档 