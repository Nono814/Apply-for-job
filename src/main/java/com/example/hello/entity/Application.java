package com.example.hello.entity;

import java.time.LocalDateTime;

/**
 * 职位申请实体类
 */
public class Application {
    private Integer id;
    private Integer jobId;
    private Integer userId;
    private Integer resumeId;
    private String status;
    private LocalDateTime submittedAt;
    
    // 关联的职位信息
    private Job job;
    private User applicant;
    private Resume resume;

    // 构造函数
    public Application() {}

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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", userId=" + userId +
                ", resumeId=" + resumeId +
                ", status='" + status + '\'' +
                ", submittedAt=" + submittedAt +
                ", job=" + job +
                ", applicant=" + applicant +
                ", resume=" + resume +
                '}';
    }
} 