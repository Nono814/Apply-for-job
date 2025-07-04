# 申请职位系统

基于Spring Boot + MyBatis + PageHelper技术栈开发的职位申请系统。

## 技术栈

- **Spring Boot 3.3.2** - 主框架
- **MyBatis 3.0.3** - ORM框架
- **MySQL 8.0** - 数据库
- **PageHelper 1.4.7** - 分页插件
- **Java 17** - 编程语言

## 数据库配置

- 主机：<your-mysql-host>
- 端口：<your-mysql-port>
- 用户名：<your-username>
- 密码：<your-password>
- 数据库：<your-database>

## 用户注册和申请流程

### 1. 用户注册流程
用户只需要使用**手机号**进行注册，注册时不收集姓名和邮箱信息。

### 2. 申请职位流程
申请职位时，用户需要：
- 输入姓名
- 输入邮箱
- 上传简历文件
- 系统会自动处理用户注册/登录和申请提交

## 接口文档

### 1. 获取职位详情
- **请求路径**：`GET /api/jobs/{job_id}`
- **说明**：根据职位ID获取职位详细信息
- **示例**：`GET /api/jobs/1`

### 2. 用户注册/登录（完整信息）
- **请求路径**：`POST /api/users`
- **请求体**：
```json
{
  "name": "张三",
  "email": "zhangsan@example.com",
  "phone_number": "13800138000"
}
```

### 3. 简历上传
- **请求路径**：`POST /api/resumes`
- **请求方式**：multipart/form-data
- **参数**：
  - `file`: 简历文件
  - `user_id`: 用户ID
  - `is_default`: 是否设为默认（可选）

### 4. 获取用户简历列表
- **请求路径**：`GET /api/users/{user_id}/resumes`
- **说明**：获取指定用户的所有简历

### 5. 提交职位申请
- **请求路径**：`POST /api/applications`
- **请求方式**：application/x-www-form-urlencoded
- **参数**：
  - `job_id`: 职位ID
  - `applicant_id`: 申请人ID
  - `resume_id`: 简历ID

### 6. 获取申请状态
- **请求路径**：`GET /api/applications/{application_id}`
- **说明**：根据申请ID获取申请状态

### 7. 🆕 一键申请职位（推荐使用）
- **请求路径**：`POST /api/jobs/{job_id}/apply`
- **请求方式**：multipart/form-data
- **参数**：
  - `name`: 用户姓名
  - `email`: 用户邮箱
  - `phone_number`: 用户手机号
  - `resume_file`: 简历文件
- **说明**：一个接口完成用户注册/登录、信息补充、简历上传和职位申请
- **示例**：
```bash
curl -X POST http://localhost:8080/api/jobs/1/apply \
  -F "name=张三" \
  -F "email=zhangsan@example.com" \
  -F "phone_number=13800138000" \
  -F "resume_file=@resume.pdf"
```

## 系统功能

1. **职位管理**：查看职位详情，包含公司信息、薪资范围等
2. **用户管理**：简化注册（仅手机号）、信息补充、登录功能
3. **简历管理**：简历上传、管理、设置默认简历
4. **申请管理**：职位申请、申请状态查询
5. **一键申请**：整合用户注册、信息补充、简历上传、职位申请
6. **数据验证**：输入参数验证，确保数据完整性

## 启动方式

```bash
# 编译项目
mvn clean compile

# 启动项目
mvn spring-boot:run
```

## 访问地址

- 本地访问：http://localhost:8080
- 公网访问：https://xyyjfypjlsbg.sealosbja.site

## 项目结构

```
src/main/java/com/example/hello/
├── controller/          # 控制器层
│   ├── JobController.java
│   ├── JobApplicationController.java  # 🆕 一键申请控制器
│   ├── UserController.java
│   ├── ResumeController.java
│   ├── ApplicationController.java
│   └── TestController.java
├── service/            # 服务层
│   ├── JobService.java
│   ├── JobApplicationService.java     # 🆕 一键申请服务
│   ├── UserService.java
│   ├── ResumeService.java
│   └── ApplicationService.java
├── mapper/             # 数据访问层
│   ├── JobMapper.java
│   ├── UserMapper.java
│   ├── ResumeMapper.java
│   └── ApplicationMapper.java
├── entity/             # 实体类
│   ├── User.java
│   ├── Company.java
│   ├── Job.java
│   ├── Resume.java
│   └── Application.java
├── dto/                # 数据传输对象
│   ├── ApiResponse.java
│   ├── UserRequest.java
│   ├── JobApplicationRequest.java     # 🆕 申请请求DTO
│   └── JobDetailResponse.java
└── HelloApplication.java
```

## 特性

- ✅ 使用JDK 1.8+新语法特性
- ✅ 完整的RESTful API设计
- ✅ 参数验证和错误处理
- ✅ 文件上传功能
- ✅ 数据库事务管理
- ✅ 分页查询支持
- ✅ 关联查询优化
- ✅ 🆕 简化用户注册流程
- ✅ 🆕 一键申请职位功能 