package com.example.hello.controller;

import com.example.hello.dto.ApiResponse;
import com.example.hello.dto.JobDetailResponse;
import com.example.hello.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 职位控制器
 */
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    /**
     * 获取职位列表
     */
    @GetMapping
    public ApiResponse<Object> getJobList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ApiResponse.success(jobService.getJobList(page, size));
    }
    
    /**
     * 获取职位详情
     */
    @GetMapping("/{job_id}")
    public ApiResponse<JobDetailResponse> getJobDetail(@PathVariable("job_id") Integer jobId) {
        Optional<JobDetailResponse> jobDetail = jobService.getJobDetail(jobId);
        
        if (jobDetail.isPresent()) {
            return ApiResponse.success(jobDetail.get());
        } else {
            return ApiResponse.error(404, "职位不存在");
        }
    }
} 