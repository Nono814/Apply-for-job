package com.example.hello.mapper;

import com.example.hello.entity.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 简历Mapper接口
 */
@Mapper
public interface ResumeMapper {
    
    /**
     * 插入新简历
     */
    int insert(Resume resume);
    
    /**
     * 根据用户ID查询简历列表
     */
    List<Resume> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据ID查询简历
     */
    Resume selectById(@Param("id") Integer id);
    
    /**
     * 更新简历的默认状态
     */
    int updateDefaultStatus(@Param("userId") Integer userId, @Param("isDefault") Boolean isDefault);
    
    /**
     * 设置指定简历为默认
     */
    int setAsDefault(@Param("id") Integer id, @Param("userId") Integer userId);
    
    /**
     * 根据用户ID和文件哈希查找简历（用于去重）
     */
    Resume selectByUserIdAndFileHash(@Param("userId") Integer userId, @Param("fileHash") String fileHash);
} 