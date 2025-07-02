package com.example.hello.service;

import com.example.hello.entity.Resume;
import com.example.hello.mapper.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 简历服务类
 */
@Service
public class ResumeService {
    
    @Autowired
    private ResumeMapper resumeMapper;
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    /**
     * 上传简历
     */
    @Transactional
    public Map<String, Object> uploadResume(MultipartFile file, Integer userId, Boolean isDefault) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 创建上传目录
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + fileExtension;
            
            // 保存文件
            Path filePath = uploadDir.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath);
            
            // 如果设置为默认，先取消其他简历的默认状态
            if (Boolean.TRUE.equals(isDefault)) {
                resumeMapper.updateDefaultStatus(userId, false);
            }
            
            // 保存简历信息到数据库
            Resume resume = new Resume();
            resume.setUserId(userId);
            resume.setFilePath(filePath.toString());
            resume.setOriginalFilename(originalFilename);
            resume.setFileSize((int) file.getSize());
            resume.setIsDefault(Boolean.TRUE.equals(isDefault));
            
            resumeMapper.insert(resume);
            
            result.put("resume_id", resume.getId());
            result.put("filename", originalFilename);
            result.put("message", "简历上传成功");
            
        } catch (IOException e) {
            result.put("message", "文件上传失败: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 获取用户简历列表
     */
    public List<Resume> getUserResumes(Integer userId) {
        return resumeMapper.selectByUserId(userId);
    }
    
    /**
     * 根据ID获取简历
     */
    public Resume getResumeById(Integer resumeId) {
        return resumeMapper.selectById(resumeId);
    }
    
    /**
     * 设置简历为默认
     */
    @Transactional
    public boolean setResumeAsDefault(Integer resumeId, Integer userId) {
        // 先取消所有简历的默认状态
        resumeMapper.updateDefaultStatus(userId, false);
        // 设置指定简历为默认
        return resumeMapper.setAsDefault(resumeId, userId) > 0;
    }
    
    /**
     * 获取用户默认简历
     */
    public Resume getDefaultResume(Integer userId) {
        List<Resume> resumes = resumeMapper.selectByUserId(userId);
        if (resumes != null) {
            for (Resume resume : resumes) {
                if (Boolean.TRUE.equals(resume.getIsDefault())) {
                    return resume;
                }
            }
        }
        return null;
    }
} 