# Apifox 申请职位系统测试指南（生产环境）

## 🌐 环境信息

- **生产地址**: https://xyyjfypjlsbg.sealosbja.site
- **API Base URL**: https://xyyjfypjlsbg.sealosbja.site
- **Content-Type**: application/json

## 🚀 快速开始

### 1. 导入测试集合
1. 下载 `apifox_collection_production.json` 文件
2. 在Apifox中导入该文件
3. 设置环境变量

### 2. 环境变量配置
在Apifox中设置以下环境变量：
- `baseUrl`: `https://xyyjfypjlsbg.sealosbja.site`
- `userId`: `1` (会自动更新)
- `jobId`: `1` (会自动更新)
- `resumeId`: `1` (会自动更新)
- `applicationId`: `1` (会自动更新)

## 📋 接口测试清单

### 第一阶段：基础功能测试

#### 1. 用户注册
- **接口**: `POST /api/users/register`
- **测试数据**:
```json
{
  "name": "测试用户",
  "email": "test@example.com",
  "phoneNumber": "13800138000"
}
```
- **预期结果**: 返回用户ID，自动保存到环境变量

#### 2. 获取职位列表
- **接口**: `GET /api/jobs?page=1&size=10`
- **预期结果**: 返回职位列表，自动保存第一个职位ID

#### 3. 获取职位详情
- **接口**: `GET /api/jobs/{jobId}`
- **预期结果**: 返回职位详细信息

### 第二阶段：申请功能测试

#### 4. 提交职位申请
- **接口**: `POST /api/applications`
- **测试数据**:
```json
{
  "jobId": "{{jobId}}",
  "applicantId": "{{userId}}",
  "resumeId": "{{resumeId}}",
  "coverLetter": "我对这个职位很感兴趣，希望有机会加入贵公司。我有3年Java开发经验，熟悉Spring Boot、MyBatis等技术栈。"
}
```
- **预期结果**: 返回申请ID，自动保存到环境变量

#### 5. 获取申请详情
- **接口**: `GET /api/applications/{{applicationId}}`
- **预期结果**: 返回申请详细信息

#### 6. 根据申请人查询申请列表
- **接口**: `GET /api/applications/applicant/{{userId}}`
- **预期结果**: 返回该用户的所有申请

#### 7. 根据职位查询申请列表
- **接口**: `GET /api/applications/job/{{jobId}}`
- **预期结果**: 返回该职位的所有申请

#### 8. 根据状态查询申请列表
- **接口**: `GET /api/applications/status/pending`
- **预期结果**: 返回所有待处理的申请

### 第三阶段：管理功能测试

#### 9. 更新申请状态
- **接口**: `PUT /api/applications/{{applicationId}}/status`
- **测试数据**:
```json
{
  "status": "reviewing"
}
```
- **预期结果**: 状态更新成功

#### 10. 分页查询所有申请
- **接口**: `GET /api/applications?page=1&size=10`
- **预期结果**: 返回分页的申请列表

#### 11. 取消申请
- **接口**: `DELETE /api/applications/{{applicationId}}`
- **预期结果**: 申请取消成功

### 第四阶段：错误测试

#### 12. 重复申请测试
- **接口**: `POST /api/applications`
- **测试数据**: 使用相同的jobId和applicantId
- **预期结果**: 返回400错误，提示"您已经申请过该职位"

#### 13. 参数验证测试
- **接口**: `POST /api/applications`
- **测试数据**: 缺少必填参数
- **预期结果**: 返回400错误，提示参数验证失败

#### 14. 无效ID测试
- **接口**: `GET /api/applications/99999`
- **预期结果**: 返回404错误，提示资源不存在

## 🔧 测试脚本

### 前置脚本（Pre-request Script）
```javascript
// 设置请求头
pm.request.headers.add({
    key: 'Content-Type',
    value: 'application/json'
});
```

### 测试脚本（Tests）
```javascript
// 基础断言
pm.test("状态码为200", function () {
    pm.response.to.have.status(200);
});

pm.test("响应包含success", function () {
    pm.expect(pm.response.json().message).to.eql("success");
});

// 自动保存ID
pm.test("保存返回的ID", function () {
    const response = pm.response.json();
    if (response.data) {
        if (response.data.id) {
            pm.environment.set("userId", response.data.id);
        }
        if (response.data.application_id) {
            pm.environment.set("applicationId", response.data.application_id);
        }
        if (response.data.list && response.data.list.length > 0) {
            pm.environment.set("jobId", response.data.list[0].id);
        }
    }
});
```

## 📊 测试报告

### 成功指标
- ✅ 所有接口返回200状态码
- ✅ 响应格式正确
- ✅ 数据完整性验证通过
- ✅ 错误处理正常

### 性能指标
- 响应时间 < 2秒
- 并发请求处理正常
- 数据库连接稳定

## 🚨 注意事项

1. **HTTPS安全**: 使用HTTPS协议，数据传输安全
2. **跨域问题**: 如果遇到CORS问题，请检查服务器配置
3. **网络延迟**: 公网环境可能有网络延迟，建议适当调整超时时间
4. **数据清理**: 测试完成后建议清理测试数据
5. **并发测试**: 可以测试多个用户同时申请的情况

## 📞 技术支持

如果在测试过程中遇到问题，请检查：
1. 网络连接是否正常
2. 服务器是否正常运行
3. 接口地址是否正确
4. 请求参数是否符合要求

## 🎯 测试完成检查清单

- [ ] 用户注册功能正常
- [ ] 职位查询功能正常
- [ ] 申请提交功能正常
- [ ] 申请查询功能正常
- [ ] 状态更新功能正常
- [ ] 错误处理正常
- [ ] 性能表现良好

恭喜！您的申请职位系统已经成功部署到生产环境，现在可以开始全面的API测试了！🎉 