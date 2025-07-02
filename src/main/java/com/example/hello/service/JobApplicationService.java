package com.example.hello.service;

import com.example.hello.dto.JobApplicationRequest;
import com.example.hello.entity.Application;
import com.example.hello.entity.User;
import com.example.hello.entity.Resume;
import com.example.hello.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 职位申请服务类
 */
@Service
public class JobApplicationService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ResumeService resumeService;
    
    @Autowired
    private ApplicationMapper applicationMapper;
    
    /**
     * 一键申请职位
     */
    @Transactional
    public Map<String, Object> applyForJob(Integer jobId, JobApplicationRequest request) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 1. 检查用户是否存在，如果不存在则创建
            User user = userService.getUserByPhoneNumber(request.getPhoneNumber());
            if (user == null) {
                // 用户不存在，创建新用户（只有手机号）
                Map<String, Object> registerResult = userService.registerOrLoginByPhone(request.getPhoneNumber());
                Integer userId = (Integer) registerResult.get("user_id");
                user = userService.getUserById(userId);
            }
            
            // 2. 更新用户信息（补充姓名和邮箱）
            Map<String, Object> updateResult = userService.updateUserInfo(
                user.getId(), 
                request.getName(), 
                request.getEmail()
            );
            
            if (updateResult.containsKey("message") && 
                updateResult.get("message").toString().contains("已被其他用户使用")) {
                return updateResult;
            }
            
            // 3. 简历处理
            Integer resumeId = null;
            if (request.getResumeFile() != null && !request.getResumeFile().isEmpty()) {
                // 上传新简历
                Map<String, Object> uploadResult = resumeService.uploadResume(
                    request.getResumeFile(),
                    user.getId(),
                    true // 设为默认简历
                );
                if (uploadResult.containsKey("message") && 
                    uploadResult.get("message").toString().contains("失败")) {
                    return uploadResult;
                }
                resumeId = (Integer) uploadResult.get("resume_id");
            } else {
                // 查找默认简历
                Resume defaultResume = resumeService.getDefaultResume(user.getId());
                if (defaultResume == null) {
                    result.put("message", "请上传简历文件");
                    return result;
                }
                resumeId = defaultResume.getId();
            }
            
            // 4. 检查是否已经申请过该职位
            int existingCount = applicationMapper.countByJobIdAndUserId(jobId, user.getId());
            if (existingCount > 0) {
                result.put("message", "您已经申请过该职位");
                return result;
            }
            
            // 5. 创建申请记录
            Application application = new Application();
            application.setJobId(jobId);
            application.setUserId(user.getId());
            application.setResumeId(resumeId);
            application.setStatus("submitted");
            
            applicationMapper.insert(application);
            
            // 6. 返回成功结果
            result.put("application_id", application.getId());
            result.put("user_id", user.getId());
            result.put("resume_id", resumeId);
            result.put("status", application.getStatus());
            result.put("message", "职位申请提交成功");
            result.put("next_step", "/interview/" + application.getId());
            
        } catch (Exception e) {
            result.put("message", "申请失败: " + e.getMessage());
        }
        
        return result;
    }
} 