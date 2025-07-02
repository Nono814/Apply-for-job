package com.example.hello.mapper;

import com.example.hello.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper {
    
    /**
     * 根据邮箱查询用户
     */
    User selectByEmail(@Param("email") String email);
    
    /**
     * 根据手机号查询用户
     */
    User selectByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    
    /**
     * 插入新用户
     */
    int insert(User user);
    
    /**
     * 根据ID查询用户
     */
    User selectById(@Param("id") Integer id);
    
    /**
     * 更新用户信息
     */
    int updateById(User user);
} 