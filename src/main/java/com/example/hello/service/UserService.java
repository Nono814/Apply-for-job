package com.example.hello.service;

import com.example.hello.dto.UserRequest;
import com.example.hello.entity.User;
import com.example.hello.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务类
 */
@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 用户注册/登录（完整信息）
     */
    @Transactional
    public Map<String, Object> registerOrLogin(UserRequest request) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查邮箱是否已存在
        User existingUser = userMapper.selectByEmail(request.getEmail());
        if (existingUser != null) {
            // 用户已存在，返回用户信息
            result.put("user_id", existingUser.getId());
            result.put("message", "用户信息已保存");
            return result;
        }
        
        // 检查手机号是否已存在
        existingUser = userMapper.selectByPhoneNumber(request.getPhoneNumber());
        if (existingUser != null) {
            // 用户已存在，返回用户信息
            result.put("user_id", existingUser.getId());
            result.put("message", "用户信息已保存");
            return result;
        }
        
        // 创建新用户
        User newUser = new User(request.getName(), request.getEmail(), request.getPhoneNumber());
        userMapper.insert(newUser);
        
        result.put("user_id", newUser.getId());
        result.put("message", "用户信息已保存");
        return result;
    }
    
    /**
     * 简化的手机号注册/登录
     */
    @Transactional
    public Map<String, Object> registerOrLoginByPhone(String phoneNumber) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查手机号是否已存在
        User existingUser = userMapper.selectByPhoneNumber(phoneNumber);
        if (existingUser != null) {
            // 用户已存在，返回用户信息
            result.put("user_id", existingUser.getId());
            result.put("message", "登录成功");
            result.put("is_registered", true);
            return result;
        }
        
        // 创建新用户（只有手机号）
        User newUser = new User();
        newUser.setPhoneNumber(phoneNumber);
        userMapper.insert(newUser);
        
        result.put("user_id", newUser.getId());
        result.put("message", "注册成功");
        result.put("is_registered", false);
        return result;
    }
    
    /**
     * 更新用户信息（补充姓名和邮箱）
     */
    @Transactional
    public Map<String, Object> updateUserInfo(Integer userId, String name, String email) {
        Map<String, Object> result = new HashMap<>();
        
        User user = userMapper.selectById(userId);
        if (user == null) {
            result.put("message", "用户不存在");
            return result;
        }
        
        // 检查邮箱是否被其他用户使用
        User existingUser = userMapper.selectByEmail(email);
        if (existingUser != null && !existingUser.getId().equals(userId)) {
            result.put("message", "该邮箱已被其他用户使用");
            return result;
        }
        
        // 更新用户信息
        user.setName(name);
        user.setEmail(email);
        userMapper.updateById(user);
        
        result.put("message", "用户信息更新成功");
        return result;
    }
    
    /**
     * 根据ID获取用户信息
     */
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }
    
    /**
     * 根据手机号获取用户信息
     */
    public User getUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectByPhoneNumber(phoneNumber);
    }
} 