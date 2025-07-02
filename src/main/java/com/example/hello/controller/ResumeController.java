package com.example.hello.controller;

import com.example.hello.dto.ApiResponse;
import com.example.hello.entity.Resume;
import com.example.hello.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 简历控制器
 */
@RestController
@RequestMapping("/api")
public class ResumeController {
    
    @Autowired
    private ResumeService resumeService;
    
    /**
     * 上传简历
     */
    @PostMapping("/resumes")
    public ApiResponse<Map<String, Object>> uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("user_id") Integer userId,
            @RequestParam(value = "is_default", required = false, defaultValue = "false") Boolean isDefault) {
        
        Map<String, Object> result = resumeService.uploadResume(file, userId, isDefault);
        
        if (result.containsKey("message") && result.get("message").toString().contains("失败")) {
            return ApiResponse.error(result.get("message").toString());
        }
        
        return ApiResponse.success(result.get("message").toString(), result);
    }
    
    /**
     * 上传简历（兼容接口文档路径）
     */
    @PostMapping("/resumes/upload")
    public ApiResponse<Map<String, Object>> uploadResumeByDoc(
            @RequestParam("user_id") Integer userId,
            @RequestParam("file") MultipartFile file) {
        // 复用已有的上传逻辑，默认 isDefault=false
        Map<String, Object> result = resumeService.uploadResume(file, userId, false);
        if (result.containsKey("message") && result.get("message").toString().contains("失败")) {
            return ApiResponse.error(result.get("message").toString());
        }
        // 返回格式与接口文档保持一致
        return ApiResponse.success(result.get("message").toString(), result);
    }
    
    /**
     * 获取用户简历列表
     */
    @GetMapping("/users/{user_id}/resumes")
    public ApiResponse<List<Map<String, Object>>> getUserResumes(@PathVariable("user_id") Integer userId) {
        List<Resume> resumes = resumeService.getUserResumes(userId);
        
        List<Map<String, Object>> resumeList = resumes.stream()
            .map(resume -> {
                Map<String, Object> resumeMap = Map.of(
                    "resume_id", resume.getId(),
                    "filename", resume.getOriginalFilename(),
                    "uploaded_at", resume.getUploadedAt().toString(),
                    "is_default", resume.getIsDefault()
                );
                return resumeMap;
            })
            .collect(Collectors.toList());
        
        return ApiResponse.success(resumeList);
    }
} 