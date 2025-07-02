# 招聘系统接口文档（详细版）

---

## 1. 用户相关

### 1.1 用户注册

- **接口**：`POST /api/users`
- **描述**：注册新用户
- **请求参数**（JSON）：

| 字段         | 类型   | 必填 | 说明         |
| ------------ | ------ | ---- | ------------ |
| name         | string | 是   | 用户姓名     |
| email        | string | 是   | 邮箱         |
| phone_number | string | 是   | 手机号       |

- **请求示例**：
```json
{
  "name": "张三",
  "email": "test@example.com",
  "phone_number": "13800138000"
}
```

- **返回示例**：
```json
{
  "code": 200,
  "message": "用户注册成功",
  "data": {
    "user_id": 1
  }
}
```

---

### 1.2 获取用户信息

- **接口**：`GET /api/users/{user_id}`
- **描述**：获取用户基本信息
- **路径参数**：

| 字段    | 类型 | 必填 | 说明   |
| ------- | ---- | ---- | ------ |
| user_id | int  | 是   | 用户ID |

- **返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "user_id": 1,
    "name": "张三",
    "email": "test@example.com",
    "phone_number": "13800138000"
  }
}
```

---

## 2. 简历相关

### 2.1 上传简历

- **接口**：`POST /api/resumes/upload`
- **描述**：上传简历文件
- **请求类型**：`multipart/form-data`
- **请求参数**：

| 字段    | 类型   | 必填 | 说明                 |
| ------- | ------ | ---- | -------------------- |
| user_id | int    | 是   | 用户ID               |
| file    | file   | 是   | 简历文件（PDF/DOCX） |

- **返回示例**：
```json
{
  "code": 200,
  "message": "简历上传成功",
  "data": {
    "resume_id": 2,
    "file_url": "https://xxx/xxx.pdf"
  }
}
```

---

### 2.2 获取用户简历列表

- **接口**：`GET /api/users/{user_id}/resumes`
- **描述**：获取用户所有简历
- **路径参数**：

| 字段    | 类型 | 必填 | 说明   |
| ------- | ---- | ---- | ------ |
| user_id | int  | 是   | 用户ID |

- **返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "resume_id": 2,
      "file_url": "https://xxx/xxx.pdf",
      "uploaded_at": "2024-05-01T12:00:00Z"
    }
  ]
}
```

---

## 3. 职位相关

### 3.1 获取职位详情

- **接口**：`GET /api/jobs/{job_id}`
- **描述**：获取职位详细信息
- **路径参数**：

| 字段   | 类型 | 必填 | 说明   |
| ------ | ---- | ---- | ------ |
| job_id | int  | 是   | 职位ID |

- **返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "job_id": 1,
    "title": "产品经理",
    "type": "全职",
    "location": "远程",
    "salary": "¥2,000-¥3,000/年",
    "description": "职位描述..."
  }
}
```

---

## 4. 申请相关

### 4.1 提交职位申请

- **接口**：`POST /api/applications`
- **描述**：提交职位申请
- **请求参数**（JSON）：

| 字段         | 类型   | 必填 | 说明         |
| ------------ | ------ | ---- | ------------ |
| job_id       | int    | 是   | 职位ID       |
| user_id      | int    | 是   | 用户ID       |
| resume_id    | int    | 是   | 简历ID       |

- **请求示例**：
```json
{
  "job_id": 1,
  "user_id": 1,
  "resume_id": 2
}
```

- **返回示例**：
```json
{
  "code": 200,
  "message": "申请提交成功",
  "data": {
    "application_id": 3
  }
}
```

---

### 4.2 获取申请详情

- **接口**：`GET /api/applications/{application_id}`
- **描述**：获取单个申请详情
- **路径参数**：

| 字段           | 类型 | 必填 | 说明     |
| -------------- | ---- | ---- | -------- |
| application_id | int  | 是   | 申请ID   |

- **返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "application_id": 3,
    "job_id": 1,
    "user_id": 1,
    "resume_id": 2,
    "status": "已提交",
    "created_at": "2024-05-01T12:00:00Z"
  }
}
```

---

### 4.3 获取用户所有申请

- **接口**：`GET /api/applications/user/{user_id}`
- **描述**：获取用户所有申请
- **路径参数**：

| 字段    | 类型 | 必填 | 说明   |
| ------- | ---- | ---- | ------ |
| user_id | int  | 是   | 用户ID |

- **返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "application_id": 3,
      "job_id": 1,
      "status": "已提交"
    }
  ]
}
```

---

### 4.4 获取职位所有申请

- **接口**：`GET /api/applications/job/{job_id}`
- **描述**：获取某职位下所有申请
- **路径参数**：

| 字段   | 类型 | 必填 | 说明   |
| ------ | ---- | ---- | ------ |
| job_id | int  | 是   | 职位ID |

- **返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "application_id": 3,
      "user_id": 1,
      "status": "已提交"
    }
  ]
}
```

---

### 4.5 更新申请状态

- **接口**：`PUT /api/applications/{application_id}/status`
- **描述**：更新申请状态（如"已通过"、"已拒绝"等）
- **路径参数**：

| 字段           | 类型 | 必填 | 说明   |
| -------------- | ---- | ---- | ------ |
| application_id | int  | 是   | 申请ID |

- **请求参数**（JSON）：

| 字段   | 类型   | 必填 | 说明     |
| ------ | ------ | ---- | -------- |
| status | string | 是   | 新状态值 |

- **请求示例**：
```json
{
  "status": "已通过"
}
```

- **返回示例**：
```json
{
  "code": 200,
  "message": "状态更新成功"
}
```

---

### 4.6 删除申请

- **接口**：`DELETE /api/applications/{application_id}`
- **描述**：删除申请
- **路径参数**：

| 字段           | 类型 | 必填 | 说明   |
| -------------- | ---- | ---- | ------ |
| application_id | int  | 是   | 申请ID |

- **返回示例**：
```json
{
  "code": 200,
  "message": "删除成功"
}
```

---

## 5. 通用返回格式

所有接口建议统一返回如下结构，便于前端处理：
```json
{
  "code": 200,
  "message": "success",
  "data": { ... }
}
```

---

## 6. 其他说明

- 所有接口建议加鉴权（如JWT）。
- 文件上传接口要严格校验类型和大小（如只允许PDF、DOCX，最大3M）。
- 参数命名统一使用下划线（snake_case）。
- GET 查询列表时返回数组，详情时返回对象。
- 业务状态建议用 status 字段，且有文档说明所有可能的状态值（如"已提交"、"已通过"、"已拒绝"）。 