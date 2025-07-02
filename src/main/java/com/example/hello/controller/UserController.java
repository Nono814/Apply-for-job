package com.example.hello.controller;

import com.example.hello.dto.ApiResponse;
import com.example.hello.dto.UserRequest;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 用户注册/登录
     */
    @PostMapping
    public ApiResponse<Map<String, Object>> registerOrLogin(@Valid @RequestBody UserRequest request) {
        Map<String, Object> result = userService.registerOrLogin(request);
        return ApiResponse.success(result.get("message").toString(), result);
    }
} 