# Apifox 申请职位接口测试指南

## 环境配置

### 1. 基础配置
- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json`

### 2. 测试数据准备
在开始测试前，请确保数据库中有以下测试数据：
- 用户数据（用于申请人和HR）
- 职位数据
- 简历数据

## 接口测试顺序

### 第一步：用户注册/登录
**接口**: `POST /api/users/register`
```json
{
  "name": "张三",
  "email": "zhangsan@example.com",
  "phoneNumber": "13800138000"
}
```

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "name": "张三",
    "email": "zhangsan@example.com",
    "phoneNumber": "13800138000"
  }
}
```

### 第二步：上传简历
**接口**: `POST /api/resumes/upload`
- **请求方式**: `multipart/form-data`
- **参数**:
  - `file`: 选择PDF或Word文档
  - `userId`: `1`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "filename": "resume.pdf",
    "userId": 1
  }
}
```

### 第三步：查看职位列表
**接口**: `GET /api/jobs`
- **参数**: 
  - `page`: `1`
  - `size`: `10`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "title": "Java开发工程师",
        "companyName": "腾讯科技有限公司",
        "salary": "15k-25k"
      }
    ],
    "total": 1
  }
}
```

### 第四步：查看职位详情
**接口**: `GET /api/jobs/{jobId}`
- **路径参数**: `jobId = 1`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "title": "Java开发工程师",
    "companyName": "腾讯科技有限公司",
    "description": "负责公司核心业务系统开发...",
    "salary": "15k-25k",
    "requirements": "3年以上Java开发经验..."
  }
}
```

## 申请职位接口测试

### 1. 提交职位申请
**接口**: `POST /api/applications`
```json
{
  "jobId": 1,
  "applicantId": 1,
  "resumeId": 1,
  "coverLetter": "我对这个职位很感兴趣，希望有机会加入贵公司。我有3年Java开发经验，熟悉Spring Boot、MyBatis等技术栈。"
}
```

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "application_id": 1,
    "status": "pending",
    "message": "申请提交成功"
  }
}
```

**测试场景**:
- ✅ 正常申请
- ❌ 重复申请同一职位（应该返回错误）
- ❌ 缺少必填参数（应该返回验证错误）

### 2. 获取申请状态
**接口**: `GET /api/applications/{applicationId}`
- **路径参数**: `applicationId = 1`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "jobId": 1,
    "jobTitle": "Java开发工程师",
    "companyName": "腾讯科技有限公司",
    "applicantId": 1,
    "applicantName": "张三",
    "resumeId": 1,
    "resumeFilename": "resume.pdf",
    "status": "pending",
    "submittedAt": "2025-06-29T16:30:00",
    "coverLetter": "我对这个职位很感兴趣..."
  }
}
```

### 3. 根据申请人查询申请列表
**接口**: `GET /api/applications/applicant/{applicantId}`
- **路径参数**: `applicantId = 1`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "jobTitle": "Java开发工程师",
      "companyName": "腾讯科技有限公司",
      "status": "pending",
      "submittedAt": "2025-06-29T16:30:00"
    }
  ]
}
```

### 4. 根据职位查询申请列表
**接口**: `GET /api/applications/job/{jobId}`
- **路径参数**: `jobId = 1`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "applicantName": "张三",
      "applicantEmail": "zhangsan@example.com",
      "status": "pending",
      "submittedAt": "2025-06-29T16:30:00"
    }
  ]
}
```

### 5. 根据状态查询申请列表
**接口**: `GET /api/applications/status/{status}`
- **路径参数**: `status = pending`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "jobTitle": "Java开发工程师",
      "applicantName": "张三",
      "status": "pending",
      "submittedAt": "2025-06-29T16:30:00"
    }
  ]
}
```

### 6. 更新申请状态（HR操作）
**接口**: `PUT /api/applications/{applicationId}/status`
- **路径参数**: `applicationId = 1`
```json
{
  "status": "reviewing"
}
```

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "application_id": 1,
    "status": "reviewing",
    "message": "状态更新成功"
  }
}
```

**状态值说明**:
- `pending`: 待处理
- `reviewing`: 审核中
- `accepted`: 已通过
- `rejected`: 已拒绝
- `cancelled`: 已取消

### 7. 分页查询所有申请
**接口**: `GET /api/applications`
- **查询参数**:
  - `page`: `1`
  - `size`: `10`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "jobTitle": "Java开发工程师",
        "applicantName": "张三",
        "status": "reviewing",
        "submittedAt": "2025-06-29T16:30:00"
      }
    ],
    "total": 1,
    "page": 1,
    "size": 10
  }
}
```

### 8. 取消申请
**接口**: `DELETE /api/applications/{applicationId}`
- **路径参数**: `applicationId = 1`

**预期响应**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "application_id": 1,
    "message": "申请已取消"
  }
}
```

## 错误测试场景

### 1. 重复申请测试
**请求**: 对同一职位重复提交申请
**预期**: 返回400错误，提示"您已经申请过该职位"

### 2. 参数验证测试
**请求**: 缺少必填参数
**预期**: 返回400错误，提示参数验证失败

### 3. 无效ID测试
**请求**: 使用不存在的ID
**预期**: 返回404错误，提示资源不存在

### 4. 状态值验证测试
**请求**: 使用无效的状态值
**预期**: 返回400错误，提示状态值无效

## Apifox 测试技巧

### 1. 环境变量设置
在Apifox中设置环境变量：
- `baseUrl`: `http://localhost:8080`
- `userId`: `1`
- `jobId`: `1`
- `resumeId`: `1`
- `applicationId`: `1`

### 2. 前置脚本
在需要用户ID的接口中添加前置脚本：
```javascript
// 自动获取最新的用户ID
pm.environment.set("userId", pm.response.json().data.id);
```

### 3. 测试断言
为每个接口添加断言：
```javascript
pm.test("状态码为200", function () {
    pm.response.to.have.status(200);
});

pm.test("响应包含success", function () {
    pm.expect(pm.response.json().message).to.eql("success");
});
```

### 4. 测试集合
将所有接口添加到测试集合中，按顺序执行：
1. 用户注册
2. 简历上传
3. 职位查询
4. 申请提交
5. 申请查询
6. 状态更新
7. 申请取消

## 注意事项

1. **测试顺序**: 严格按照上述顺序测试，确保数据依赖关系
2. **数据清理**: 测试完成后清理测试数据
3. **错误处理**: 测试各种异常情况
4. **性能测试**: 对分页查询进行性能测试
5. **并发测试**: 测试并发申请同一职位的情况

按照这个指南，您就可以在Apifox中系统地测试所有申请职位接口了！ 