package com.example.hello.mapper;

import com.example.hello.entity.Application;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位申请Mapper接口
 */
@Mapper
public interface ApplicationMapper {
    
    /**
     * 插入新申请
     */
    int insert(Application application);
    
    /**
     * 根据ID查询申请详情（包含职位、用户、简历信息）
     */
    Application selectApplicationWithDetailsById(@Param("id") Integer id);
    
    /**
     * 根据ID查询申请基本信息
     */
    Application selectById(@Param("id") Integer id);
    
    /**
     * 检查是否已经申请过该职位
     */
    int countByJobIdAndUserId(@Param("jobId") Integer jobId, @Param("userId") Integer userId);
    
    /**
     * 根据申请人ID查询申请列表
     */
    List<Application> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据职位ID查询申请列表
     */
    List<Application> selectByJobId(@Param("jobId") Integer jobId);
    
    /**
     * 根据状态查询申请列表
     */
    List<Application> selectByStatus(@Param("status") String status);
    
    /**
     * 更新申请状态
     */
    int updateStatus(@Param("id") Integer id, @Param("status") String status);
    
    /**
     * 分页查询申请列表
     */
    List<Application> selectWithPagination(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 统计申请总数
     */
    int countTotal();

    int deleteById(@Param("id") Integer id);
} 