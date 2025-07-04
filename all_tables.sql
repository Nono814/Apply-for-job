/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: niuroubuger-mysql.ns-30d7gjvu.svc    Database: Beef Pie
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `applications` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '申请记录的唯一ID',
  `job_id` int unsigned NOT NULL COMMENT '申请的职位ID',
  `user_id` int unsigned NOT NULL,
  `resume_id` int unsigned NOT NULL COMMENT '本次申请所使用的简历ID',
  `status` enum('submitted','testing','completed') NOT NULL DEFAULT 'submitted' COMMENT '申请状态',
  `submitted_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间',
  `applied_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`),
  KEY `resume_id` (`resume_id`),
  KEY `fk_applications_user_id` (`user_id`),
  CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`),
  CONSTRAINT `applications_ibfk_3` FOREIGN KEY (`resume_id`) REFERENCES `resumes` (`id`),
  CONSTRAINT `fk_applications_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位申请记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '公司唯一ID',
  `name` varchar(80) NOT NULL COMMENT '公司名称 (唯一必填项)',
  `website` varchar(255) DEFAULT NULL COMMENT '公司网站 (可选)',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '公司Logo的URL (可选, 1:1关系)',
  `financing_stage` enum('Seed','Pre-A','Series A','Series B','Series C+','Public','Other') DEFAULT NULL COMMENT '融资阶段 (可选)',
  `description` varchar(1000) DEFAULT NULL COMMENT '公司信息/简介 (可选, 最多1000字符)',
  `intro_video_url` varchar(500) DEFAULT NULL COMMENT '公司介绍视频链接 (可选, 最多500字符)',
  `wants_video_support` tinyint(1) DEFAULT '0' COMMENT '是否对视频制作支持感兴趣 (可选)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司核心信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '职位唯一ID',
  `company_id` int unsigned NOT NULL COMMENT '关联的公司ID',
  `title` varchar(255) NOT NULL COMMENT '职位名称，例如 "Product Manager | 产品经理"',
  `employment_type` enum('full-time','part-time','internship') NOT NULL COMMENT '岗位性质 (例如: 全职)',
  `work_style` enum('on-site','remote','hybrid') NOT NULL COMMENT '办公方式 (例如: 远程)',
  `salary_min` int unsigned DEFAULT NULL COMMENT '薪资范围的最低值',
  `salary_max` int unsigned DEFAULT NULL COMMENT '薪资范围的最高值',
  `salary_currency` char(3) DEFAULT 'USD' COMMENT '薪资的货币单位 (例如 "USD", "CNY")',
  `salary_period` enum('year','month') NOT NULL DEFAULT 'year' COMMENT '薪资周期 (年/月)',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `jobs_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位招聘信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resumes`
--

DROP TABLE IF EXISTS `resumes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `resumes` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '简历的唯一ID',
  `user_id` int unsigned NOT NULL,
  `file_path` varchar(255) NOT NULL COMMENT '简历文件在服务器上的存储路径',
  `original_filename` varchar(255) DEFAULT NULL COMMENT '简历文件的原始名称',
  `file_size` int unsigned DEFAULT NULL COMMENT '文件大小 (单位: 字节)',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为该用户的默认简历 (1:是, 0:否)',
  `uploaded_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `file_hash` varchar(64) DEFAULT NULL COMMENT 'æ–‡ä»¶å†…å®¹å“ˆå¸Œå€¼',
  PRIMARY KEY (`id`),
  KEY `idx_resumes_userid_filehash` (`user_id`,`file_hash`),
  CONSTRAINT `fk_resumes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='求职者简历表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID',
  `name` varchar(50) NOT NULL COMMENT '用户的姓名，最大长度50个字符',
  `email` varchar(255) NOT NULL COMMENT '用户的邮箱地址，必填，唯一',
  `phone_number` varchar(25) NOT NULL COMMENT '用户的手机号码，必填，唯一（使用VARCHAR以兼容不同国家/地区格式）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-04  6:27:20
