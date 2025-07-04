package com.example.hello.service;

import com.example.hello.dto.ApplicationResponse;
import com.example.hello.entity.Application;
import com.example.hello.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 职位申请服务类
 */
@Service
public class ApplicationService {
    
    @Autowired
    private ApplicationMapper applicationMapper;
    
    /**
     * 提交职位申请
     */
    @Transactional
    public Map<String, Object> submitApplication(Integer jobId, Integer userId, Integer resumeId) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查是否已经申请过该职位
        int existingCount = applicationMapper.countByJobIdAndUserId(jobId, userId);
        if (existingCount > 0) {
            result.put("message", "您已经申请过该职位");
            return result;
        }
        
        // 创建新申请
        Application application = new Application();
        application.setJobId(jobId);
        application.setUserId(userId);
        application.setResumeId(resumeId);
        application.setStatus("submitted"); // 初始状态
        
        applicationMapper.insert(application);
        
        result.put("application_id", application.getId());
        result.put("status", application.getStatus());
        result.put("message", "申请提交成功");
        
        return result;
    }
    
    /**
     * 获取申请状态
     */
    public Map<String, Object> getApplicationStatus(Integer applicationId) {
        Map<String, Object> result = new HashMap<>();
        
        Application application = applicationMapper.selectApplicationWithDetailsById(applicationId);
        if (application == null) {
            result.put("message", "申请记录不存在");
            return result;
        }
        
        result.put("application_id", application.getId());
        result.put("job_title", application.getJob() != null ? application.getJob().getTitle() : "");
        result.put("company_name", application.getJob() != null && application.getJob().getCompany() != null 
            ? application.getJob().getCompany().getName() : "");
        result.put("status", application.getStatus());
        result.put("submitted_at", application.getSubmittedAt() != null 
            ? application.getSubmittedAt().toString() : "");
        
        return result;
    }
    
    /**
     * 根据ID获取申请详情
     */
    public Application getApplicationById(Integer applicationId) {
        return applicationMapper.selectApplicationWithDetailsById(applicationId);
    }
    
    /**
     * 根据申请人ID获取申请列表
     */
    public List<ApplicationResponse> getApplicationsByUserId(Integer userId) {
        List<Application> applications = applicationMapper.selectByUserId(userId);
        return applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * 根据职位ID获取申请列表
     */
    public List<ApplicationResponse> getApplicationsByJobId(Integer jobId) {
        List<Application> applications = applicationMapper.selectByJobId(jobId);
        return applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * 根据状态获取申请列表
     */
    public List<ApplicationResponse> getApplicationsByStatus(String status) {
        List<Application> applications = applicationMapper.selectByStatus(status);
        return applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * 更新申请状态
     */
    @Transactional
    public boolean updateApplicationStatus(Integer applicationId, String status) {
        int result = applicationMapper.updateStatus(applicationId, status);
        return result > 0;
    }
    
    /**
     * 分页获取申请列表
     */
    public Map<String, Object> getApplicationsWithPagination(int page, int size) {
        Map<String, Object> result = new HashMap<>();
        
        int offset = (page - 1) * size;
        List<Application> applications = applicationMapper.selectWithPagination(offset, size);
        int total = applicationMapper.countTotal();
        
        List<ApplicationResponse> responses = applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        result.put("applications", responses);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        result.put("total_pages", (int) Math.ceil((double) total / size));
        
        return result;
    }
    
    /**
     * 将Application实体转换为ApplicationResponse
     */
    private ApplicationResponse convertToResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setJobId(application.getJobId());
        response.setUserId(application.getUserId());
        response.setResumeId(application.getResumeId());
        response.setStatus(application.getStatus());
        response.setSubmittedAt(application.getSubmittedAt());
        
        if (application.getJob() != null) {
            response.setJobTitle(application.getJob().getTitle());
            if (application.getJob().getCompany() != null) {
                response.setCompanyName(application.getJob().getCompany().getName());
            }
        }
        
        if (application.getApplicant() != null) {
            response.setApplicantName(application.getApplicant().getName());
        }
        
        if (application.getResume() != null) {
            response.setResumeFilename(application.getResume().getOriginalFilename());
        }
        
        return response;
    }

    @Transactional
    public boolean deleteApplicationById(Integer applicationId) {
        int result = applicationMapper.deleteById(applicationId);
        return result > 0;
    }
} 