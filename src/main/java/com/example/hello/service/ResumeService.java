package com.example.hello.service;

import com.example.hello.entity.Resume;
import com.example.hello.mapper.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.DigestUtils;

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
    
    @Autowired
    private ObjectStorageService objectStorageService;
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    /**
     * 上传简历
     * 使用对象存储服务上传文件，支持PDF、DOCX、DOC格式
     */
    @Transactional
    public Map<String, Object> uploadResume(MultipartFile file, Integer userId, Boolean isDefault) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 验证文件类型
            String originalFilename = file.getOriginalFilename();
            if (!objectStorageService.isValidFileType(originalFilename)) {
                result.put("message", "不支持的文件类型，仅支持PDF、DOCX、DOC格式");
                return result;
            }
            
            // 验证文件大小（最大3MB）
            if (!objectStorageService.isValidFileSize(file.getSize(), 3)) {
                result.put("message", "文件大小超过限制，最大支持3MB");
                return result;
            }
            
            // 计算文件内容的MD5哈希值
            String fileHash = DigestUtils.md5DigestAsHex(file.getInputStream());
            // 查重：同一用户已上传过相同内容的简历
            Resume exist = resumeMapper.selectByUserIdAndFileHash(userId, fileHash);
            if (exist != null) {
                result.put("status", "DUPLICATE"); // 添加状态标识，用于控制器判断
                result.put("message", "您已上传过相同内容的简历文件！");
                result.put("resume_id", exist.getId());
                result.put("file_url", exist.getFilePath());
                return result;
            }
            
            // 上传文件到对象存储
            String fileUrl = objectStorageService.uploadFile(
                file.getInputStream(), 
                originalFilename, 
                file.getContentType()
            );
            
            // 如果设置为默认，先取消其他简历的默认状态
            if (Boolean.TRUE.equals(isDefault)) {
                resumeMapper.updateDefaultStatus(userId, false);
            }
            
            // 保存简历信息到数据库
            Resume resume = new Resume();
            resume.setUserId(userId);
            resume.setFilePath(fileUrl); // 存储文件URL而不是本地路径
            resume.setOriginalFilename(originalFilename);
            resume.setFileSize((int) file.getSize());
            resume.setIsDefault(Boolean.TRUE.equals(isDefault));
            resume.setUploadedAt(LocalDateTime.now());
            resume.setFileHash(fileHash); // 存储文件哈希
            
            resumeMapper.insert(resume);
            
            result.put("resume_id", resume.getId());
            result.put("file_url", fileUrl); // 返回文件URL
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