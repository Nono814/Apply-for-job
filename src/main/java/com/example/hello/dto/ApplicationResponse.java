package com.example.hello.dto;

import java.time.LocalDateTime;

/**
 * 申请详情响应DTO
 */
public class ApplicationResponse {
    
    private Integer id;
    private Integer jobId;
    private String jobTitle;
    private String companyName;
    private Integer userId;
    private String applicantName;
    private Integer resumeId;
    private String resumeFilename;
    private String status;
    private LocalDateTime submittedAt;

    // 构造函数
    public ApplicationResponse() {}

    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeFilename() {
        return resumeFilename;
    }

    public void setResumeFilename(String resumeFilename) {
        this.resumeFilename = resumeFilename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public String toString() {
        return "ApplicationResponse{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", userId=" + userId +
                ", applicantName='" + applicantName + '\'' +
                ", resumeId=" + resumeId +
                ", resumeFilename='" + resumeFilename + '\'' +
                ", status='" + status + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }
} 