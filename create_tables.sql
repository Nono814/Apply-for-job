-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS niurouberger CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE niurouberger;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 公司表
CREATE TABLE IF NOT EXISTS companies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    address VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 职位表
CREATE TABLE IF NOT EXISTS jobs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    company_id INT NOT NULL,
    description TEXT,
    requirements TEXT,
    salary_min DECIMAL(10,2),
    salary_max DECIMAL(10,2),
    location VARCHAR(200),
    job_type VARCHAR(50),
    status VARCHAR(20) DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

-- 简历表
CREATE TABLE IF NOT EXISTS resumes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT,
    upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 申请记录表
CREATE TABLE IF NOT EXISTS applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    job_id INT NOT NULL,
    user_id INT NOT NULL,
    resume_id INT NOT NULL,
    status VARCHAR(20) DEFAULT 'pending',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (resume_id) REFERENCES resumes(id),
    UNIQUE KEY unique_application (job_id, user_id)
);

-- 插入测试数据
INSERT INTO companies (id, name, description, address) VALUES 
(1, '腾讯科技有限公司', '腾讯是一家以互联网为基础的科技与文化公司，通过技术丰富互联网用户的生活，助力企业数字化升级。', '深圳市南山区高新科技园')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO jobs (id, title, company_id, description, requirements, salary_min, salary_max, location, job_type) VALUES 
(1, 'Java开发工程师', 1, '负责公司核心业务系统的开发与维护，参与系统架构设计和技术选型。', '1. 本科及以上学历，计算机相关专业\n2. 3年以上Java开发经验\n3. 熟悉Spring Boot、MyBatis等框架\n4. 熟悉MySQL数据库设计和优化', 15000.00, 25000.00, '深圳', '全职')
ON DUPLICATE KEY UPDATE title = title; 