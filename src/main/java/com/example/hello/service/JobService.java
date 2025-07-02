package com.example.hello.service;

import com.example.hello.dto.JobDetailResponse;
import com.example.hello.entity.Job;
import com.example.hello.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 职位服务类
 */
@Service
public class JobService {
    
    @Autowired
    private JobMapper jobMapper;
    
    /**
     * 获取职位列表（分页）
     */
    public Map<String, Object> getJobList(Integer page, Integer size) {
        // 使用PageHelper进行分页
        com.github.pagehelper.PageHelper.startPage(page, size);
        List<Job> jobList = jobMapper.selectJobListWithCompany();
        
        // 获取分页信息
        com.github.pagehelper.PageInfo<Job> pageInfo = new com.github.pagehelper.PageInfo<>(jobList);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", jobList);
        result.put("total", pageInfo.getTotal());
        result.put("page", pageInfo.getPageNum());
        result.put("size", pageInfo.getPageSize());
        result.put("pages", pageInfo.getPages());
        
        return result;
    }
    
    /**
     * 根据ID获取职位详情
     */
    public Optional<JobDetailResponse> getJobDetail(Integer jobId) {
        Job job = jobMapper.selectJobWithCompanyById(jobId);
        if (job == null) {
            return Optional.empty();
        }
        
        // 构建薪资范围字符串
        String salaryRange = buildSalaryRange(job);
        
        JobDetailResponse response = new JobDetailResponse(
            job.getTitle(),
            job.getCompany() != null ? job.getCompany().getName() : "",
            job.getEmploymentType(),
            job.getWorkStyle(),
            salaryRange,
            job.getCompany() != null ? job.getCompany().getLogoUrl() : ""
        );
        
        return Optional.of(response);
    }
    
    /**
     * 构建薪资范围字符串
     */
    private String buildSalaryRange(Job job) {
        if (job.getSalaryMin() == null && job.getSalaryMax() == null) {
            return "薪资面议";
        }
        
        String currency = job.getSalaryCurrency() != null ? job.getSalaryCurrency() : "USD";
        String period = "year".equals(job.getSalaryPeriod()) ? "年" : "月";
        
        if (job.getSalaryMin() != null && job.getSalaryMax() != null) {
            return String.format("%s%d-%d/%s", currency, job.getSalaryMin(), job.getSalaryMax(), period);
        } else if (job.getSalaryMin() != null) {
            return String.format("%s%d+/%s", currency, job.getSalaryMin(), period);
        } else {
            return String.format("%s%d以下/%s", currency, job.getSalaryMax(), period);
        }
    }
} 