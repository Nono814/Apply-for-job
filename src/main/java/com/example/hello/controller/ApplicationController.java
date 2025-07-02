package com.example.hello.controller;

import com.example.hello.dto.ApiResponse;
import com.example.hello.dto.ApplicationRequest;
import com.example.hello.dto.ApplicationResponse;
import com.example.hello.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 职位申请控制器
 */
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    
    @Autowired
    private ApplicationService applicationService;
    
    /**
     * 提交职位申请
     */
    @PostMapping
    public ApiResponse<Map<String, Object>> submitApplication(@Valid @RequestBody ApplicationRequest request) {
        Map<String, Object> result = applicationService.submitApplication(
            request.getJobId(), 
            request.getUserId(), 
            request.getResumeId()
        );
        
        if (result.containsKey("message") && result.get("message").toString().contains("已经申请过")) {
            return ApiResponse.error(400, result.get("message").toString());
        }
        
        return ApiResponse.success(result);
    }
    
    /**
     * 获取申请状态
     */
    @GetMapping("/{application_id}")
    public ApiResponse<Map<String, Object>> getApplicationStatus(@PathVariable("application_id") Integer applicationId) {
        Map<String, Object> result = applicationService.getApplicationStatus(applicationId);
        
        if (result.containsKey("message") && result.get("message").toString().contains("不存在")) {
            return ApiResponse.error(404, result.get("message").toString());
        }
        
        return ApiResponse.success(result);
    }
    
    /**
     * 根据申请人ID获取申请列表
     */
    @GetMapping("/user/{user_id}")
    public ApiResponse<List<ApplicationResponse>> getApplicationsByUserId(
            @PathVariable("user_id") Integer userId) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByUserId(userId);
        return ApiResponse.success(applications);
    }
    
    /**
     * 根据职位ID获取申请列表
     */
    @GetMapping("/job/{job_id}")
    public ApiResponse<List<ApplicationResponse>> getApplicationsByJobId(
            @PathVariable("job_id") Integer jobId) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByJobId(jobId);
        return ApiResponse.success(applications);
    }
    
    /**
     * 根据状态获取申请列表
     */
    @GetMapping("/status/{status}")
    public ApiResponse<List<ApplicationResponse>> getApplicationsByStatus(
            @PathVariable("status") String status) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByStatus(status);
        return ApiResponse.success(applications);
    }
    
    /**
     * 更新申请状态
     */
    @PutMapping("/{application_id}/status")
    public ApiResponse<Map<String, Object>> updateApplicationStatus(
            @PathVariable("application_id") Integer applicationId,
            @RequestParam("status") String status) {
        
        boolean success = applicationService.updateApplicationStatus(applicationId, status);
        
        if (success) {
            Map<String, Object> result = Map.of(
                "message", "状态更新成功",
                "application_id", applicationId,
                "status", status
            );
            return ApiResponse.success(result);
        } else {
            return ApiResponse.error(404, "申请记录不存在");
        }
    }
    
    /**
     * 分页获取申请列表
     */
    @GetMapping
    public ApiResponse<Map<String, Object>> getApplicationsWithPagination(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        
        Map<String, Object> result = applicationService.getApplicationsWithPagination(page, size);
        return ApiResponse.success(result);
    }
    
    /**
     * 取消申请
     */
    @DeleteMapping("/{application_id}")
    public ApiResponse<Map<String, Object>> cancelApplication(
            @PathVariable("application_id") Integer applicationId) {
        boolean success = applicationService.deleteApplicationById(applicationId);
        if (success) {
            Map<String, Object> result = Map.of(
                "message", "申请已删除",
                "application_id", applicationId
            );
            return ApiResponse.success(result);
        } else {
            return ApiResponse.error(404, "申请记录不存在");
        }
    }
} 