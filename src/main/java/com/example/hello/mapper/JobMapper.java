package com.example.hello.mapper;

import com.example.hello.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位Mapper接口
 */
@Mapper
public interface JobMapper {
    
    /**
     * 根据ID查询职位详情（包含公司信息）
     */
    Job selectJobWithCompanyById(@Param("id") Integer id);
    
    /**
     * 根据ID查询职位基本信息
     */
    Job selectById(@Param("id") Integer id);
    
    /**
     * 分页查询职位列表（包含公司信息）
     */
    List<Job> selectJobListWithCompany();
    
    /**
     * 统计职位总数
     */
    int countJobs();
} 