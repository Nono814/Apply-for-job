package com.example.hello.dto;

import jakarta.validation.constraints.NotNull;

/**
 * 申请职位请求DTO
 */
public class ApplicationRequest {
    
    @NotNull(message = "职位ID不能为空")
    private Integer jobId;
    
    @NotNull(message = "申请人ID不能为空")
    private Integer userId;
    
    @NotNull(message = "简历ID不能为空")
    private Integer resumeId;
    
    // 构造函数
    public ApplicationRequest() {}

    // Getter和Setter方法
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    @Override
    public String toString() {
        return "ApplicationRequest{" +
                "jobId=" + jobId +
                ", userId=" + userId +
                ", resumeId=" + resumeId +
                '}';
    }
} 