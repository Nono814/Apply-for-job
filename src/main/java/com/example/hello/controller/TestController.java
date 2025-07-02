package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
public class TestController {
    
    @GetMapping("/")
    public String hello() {
        return "Hello, Spring Boot! 申请职位系统已启动";
    }
    
    @GetMapping("/health")
    public String health() {
        return "系统运行正常";
    }
} 