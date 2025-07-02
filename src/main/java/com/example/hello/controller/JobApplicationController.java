package com.example.hello.controller;

import com.example.hello.dto.ApiResponse;
import com.example.hello.dto.JobApplicationRequest;
import com.example.hello.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 职位申请控制器（一键申请）
 */
@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {
    
    @Autowired
    private JobApplicationService jobApplicationService;
    
    /**
     * 一键申请职位
     */
    @PostMapping("/{job_id}/apply")
    public ApiResponse<Map<String, Object>> applyForJob(
            @PathVariable("job_id") Integer jobId,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone_number") String phoneNumber,
            @RequestParam(value = "resume_file", required = false) org.springframework.web.multipart.MultipartFile resumeFile) {
        
        // 构建请求对象
        JobApplicationRequest request = new JobApplicationRequest();
        request.setName(name);
        request.setEmail(email);
        request.setPhoneNumber(phoneNumber);
        request.setResumeFile(resumeFile);
        
        Map<String, Object> result = jobApplicationService.applyForJob(jobId, request);
        
        if (result.containsKey("message") && 
            (result.get("message").toString().contains("失败") || 
             result.get("message").toString().contains("已被") ||
             result.get("message").toString().contains("请上传"))) {
            return ApiResponse.error(result.get("message").toString());
        }
        
        return ApiResponse.success(result.get("message").toString(), result);
    }
} 