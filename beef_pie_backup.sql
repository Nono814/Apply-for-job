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
  `applicant_id` int unsigned NOT NULL COMMENT '求职者的ID',
  `resume_id` int unsigned NOT NULL COMMENT '本次申请所使用的简历ID',
  `status` enum('submitted','testing','completed') NOT NULL DEFAULT 'submitted' COMMENT '申请状态',
  `submitted_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间',
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`),
  KEY `applicant_id` (`applicant_id`),
  KEY `resume_id` (`resume_id`),
  CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`),
  CONSTRAINT `applications_ibfk_2` FOREIGN KEY (`applicant_id`) REFERENCES `users` (`id`),
  CONSTRAINT `applications_ibfk_3` FOREIGN KEY (`resume_id`) REFERENCES `resumes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位申请记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES
(1,1,1,1,'submitted','2025-06-27 11:11:14'),
(2,2,2,2,'testing','2025-06-27 11:11:14'),
(3,3,3,3,'completed','2025-06-27 11:11:14'),
(4,4,4,4,'submitted','2025-06-27 11:11:14'),
(5,5,5,5,'testing','2025-06-27 11:11:14'),
(6,6,6,6,'submitted','2025-06-27 11:11:14'),
(7,7,7,7,'completed','2025-06-27 11:11:14'),
(8,8,8,8,'submitted','2025-06-27 11:11:14'),
(9,9,9,9,'testing','2025-06-27 11:11:14'),
(10,10,10,10,'submitted','2025-06-27 11:11:14'),
(11,11,11,11,'testing','2025-06-27 11:11:14'),
(12,12,12,12,'completed','2025-06-27 11:11:14'),
(13,13,13,13,'submitted','2025-06-27 11:11:14'),
(14,14,14,14,'testing','2025-06-27 11:11:14'),
(15,1,11,19,'submitted','2025-06-27 11:11:14');
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES
(1,'蓝天科技','https://www.bluesky-tech.com','https://cdn.example.com/logos/bluesky.png','Series B','一家专注于云计算与大数据解决方案的领先企业。','https://videos.example.com/bluesky_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(2,'创新未来','https://www.future-innovators.com','https://cdn.example.com/logos/future.png','Series A','致力于通过人工智能技术改善人们的生活。','https://videos.example.com/future_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(3,'数据脉动','https://www.data-pulse.net','https://cdn.example.com/logos/datapulse.png','Public','全球领先的数据分析与商业智能平台。','https://videos.example.com/datapulse_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(4,'量子跃迁','https://www.quantumleap.dev','https://cdn.example.com/logos/quantum.png','Seed','探索量子计算在金融领域的应用。','https://videos.example.com/quantum_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(5,'绿洲生态','https://www.oasis-eco.org','https://cdn.example.com/logos/oasis.png','Other','非营利组织，致力于环境保护和可持续发展。','https://videos.example.com/oasis_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(6,'蜂鸟物流','https://www.hummingbird-logistics.com','https://cdn.example.com/logos/hummingbird.png','Series C+','提供快速、智能的同城配送服务。','https://videos.example.com/hummingbird_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(7,'银河游戏','https://www.galaxy-games.com','https://cdn.example.com/logos/galaxy.png','Public','创造沉浸式互动娱乐体验的顶级游戏工作室。','https://videos.example.com/galaxy_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(8,'磐石安防','https://www.rocksolid-security.com','https://cdn.example.com/logos/rocksolid.png','Series A','企业级的网络安全解决方案提供商。','https://videos.example.com/rocksolid_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(9,'晨曦教育','https://www.aurora-edu.com','https://cdn.example.com/logos/aurora.png','Pre-A','打造个性化学习路径的在线教育平台。','https://videos.example.com/aurora_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(10,'饕餮美食','https://www.gourmet-feast.com','https://cdn.example.com/logos/gourmet.png','Seed','连接用户与高端私厨的美食分享平台。','https://videos.example.com/gourmet_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(11,'风驰电掣','https://www.velocity-ev.com','https://cdn.example.com/logos/velocity.png','Series B','专注于高性能电动汽车的研发与制造。','https://videos.example.com/velocity_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(12,'云端筑梦','https://www.dreamcloud-arch.com','https://cdn.example.com/logos/dreamcloud.png','Pre-A','为建筑师提供云端协同设计工具。','https://videos.example.com/dreamcloud_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(13,'健康守护','https://www.guardian-health.io','https://cdn.example.com/logos/guardian.png','Series C+','基于可穿戴设备的个人健康管理服务。','https://videos.example.com/guardian_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(14,'智慧家居','https://www.smart-nest.com','https://cdn.example.com/logos/smartnest.png','Series A','构建万物互联的智能家居生态系统。','https://videos.example.com/smartnest_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59'),
(15,'星辰旅行','https://www.stardust-travel.com','https://cdn.example.com/logos/stardust.png','Other','提供定制化深度文化体验的旅行服务。','https://videos.example.com/stardust_intro.mp4',0,'2025-06-27 11:10:59','2025-06-27 11:10:59');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES
(1,1,'高级Java工程师','full-time','hybrid',25000,40000,'CNY','month'),
(2,2,'产品经理 | AI方向','full-time','remote',150000,250000,'USD','year'),
(3,3,'数据分析师','full-time','on-site',18000,30000,'CNY','month'),
(4,4,'量子算法研究员','full-time','on-site',300000,500000,'USD','year'),
(5,5,'项目协调官','part-time','remote',8000,12000,'CNY','month'),
(6,6,'物流运营专员','full-time','hybrid',12000,18000,'CNY','month'),
(7,7,'游戏UI设计师','full-time','on-site',20000,35000,'CNY','month'),
(8,8,'网络安全顾问','full-time','remote',200000,350000,'USD','year'),
(9,9,'在线课程设计师','internship','remote',4000,6000,'CNY','month'),
(10,10,'市场推广实习生','internship','hybrid',3000,5000,'CNY','month'),
(11,1,'前端开发工程师','full-time','hybrid',22000,38000,'CNY','month'),
(12,2,'机器学习算法工程师','full-time','remote',180000,300000,'USD','year'),
(13,7,'3D角色动画师','full-time','on-site',25000,45000,'CNY','month'),
(14,13,'健康数据分析师','full-time','hybrid',20000,32000,'CNY','month'),
(15,14,'嵌入式系统工程师','full-time','on-site',28000,48000,'CNY','month');
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resumes`
--

DROP TABLE IF EXISTS `resumes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `resumes` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '简历的唯一ID',
  `applicant_id` int unsigned NOT NULL COMMENT '所属求职者的ID (外键, 关联到users.id)',
  `file_path` varchar(255) NOT NULL COMMENT '简历文件在服务器上的存储路径',
  `original_filename` varchar(255) DEFAULT NULL COMMENT '简历文件的原始名称',
  `file_size` int unsigned DEFAULT NULL COMMENT '文件大小 (单位: 字节)',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为该用户的默认简历 (1:是, 0:否)',
  `uploaded_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`),
  KEY `applicant_id` (`applicant_id`),
  CONSTRAINT `resumes_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='求职者简历表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resumes`
--

LOCK TABLES `resumes` WRITE;
/*!40000 ALTER TABLE `resumes` DISABLE KEYS */;
INSERT INTO `resumes` VALUES
(1,1,'/data/resumes/resume_01.pdf','张伟-Java开发简历.pdf',102400,1,'2025-06-27 11:11:10'),
(2,2,'/data/resumes/resume_02.docx','李娜产品经理简历.docx',204800,1,'2025-06-27 11:11:10'),
(3,3,'/data/resumes/resume_03.pdf','王芳数据分析师岗位.pdf',153600,1,'2025-06-27 11:11:10'),
(4,4,'/data/resumes/resume_04.pdf','刘洋-研究员申请.pdf',307200,1,'2025-06-27 11:11:10'),
(5,5,'/data/resumes/resume_05.docx','陈静项目助理简历.docx',122880,1,'2025-06-27 11:11:10'),
(6,6,'/data/resumes/resume_06.pdf','赵强运营岗简历.pdf',133120,1,'2025-06-27 11:11:10'),
(7,7,'/data/resumes/resume_07.pdf','黄磊-UI设计师作品集链接.pdf',512000,1,'2025-06-27 11:11:10'),
(8,8,'/data/resumes/resume_08.pdf','周敏网络安全专家简历.pdf',256000,1,'2025-06-27 11:11:10'),
(9,9,'/data/resumes/resume_09.docx','吴秀英-教育行业实习.docx',102400,1,'2025-06-27 11:11:10'),
(10,10,'/data/resumes/resume_10.pdf','孙悦市场实习生简历.pdf',92160,1,'2025-06-27 11:11:10'),
(11,11,'/data/resumes/resume_11.pdf','马超前端开发工程师.pdf',184320,1,'2025-06-27 11:11:10'),
(12,12,'/data/resumes/resume_12.pdf','胡蝶-算法工程师.pdf',225280,1,'2025-06-27 11:11:10'),
(13,13,'/data/resumes/resume_13.pdf','石敢当-3D动画简历.pdf',409600,1,'2025-06-27 11:11:10'),
(14,14,'/data/resumes/resume_14.pdf','林黛玉-数据分析师.pdf',163840,1,'2025-06-27 11:11:10'),
(15,15,'/data/resumes/resume_15.docx','贾宝玉-嵌入式简历.docx',143360,1,'2025-06-27 11:11:10'),
(16,1,'/data/resumes/resume_16.pdf','张伟-英文简历-KevinZhang.pdf',112640,0,'2025-06-27 11:11:10'),
(17,2,'/data/resumes/resume_17.pdf','李娜-AI方向PM求职信.pdf',307200,0,'2025-06-27 11:11:10'),
(18,7,'/data/resumes/resume_18.pdf','黄磊-游戏设计中文简历.pdf',460800,0,'2025-06-27 11:11:10'),
(19,11,'/data/resumes/resume_19.docx','马超-Web全栈开发.docx',204800,0,'2025-06-27 11:11:10'),
(20,1,'/data/resumes/resume_20.docx','张伟-项目管理方向.docx',133120,0,'2025-06-27 11:11:10'),
(21,1,'/tmp/uploads/871de829-f7a2-41c7-91fe-41bd56692bf4.pdf','李思琪-金融研一.pdf',431552,0,'2025-07-01 11:08:40'),
(22,1,'/tmp/uploads/a8393757-ea5b-46d2-af53-180739782f7f.pdf','李思琪-金融研一.pdf',431552,0,'2025-07-01 11:14:52'),
(23,2,'/tmp/uploads/164f7ac4-84f6-40c0-9120-2dd116778476.pdf','李思琪-金融研一.pdf',431552,0,'2025-07-01 11:17:06');
/*!40000 ALTER TABLE `resumes` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'张伟','zhangwei@example.com','+8613800138001','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(2,'李娜','lina@example.com','+8613800138002','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(3,'王芳','wangfang@example.com','+8613800138003','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(4,'刘洋','liuyang@example.com','+8613800138004','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(5,'陈静','chenjing@example.com','+8613800138005','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(6,'赵强','zhaoqiang@example.com','+8613800138006','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(7,'黄磊','huanglei@example.com','+8613800138007','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(8,'周敏','zhoumin@example.com','+8613800138008','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(9,'吴秀英','wuxiuying@example.com','+8613800138009','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(10,'孙悦','sunyue@example.com','+8613800138010','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(11,'马超','machao@example.com','+8613800138011','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(12,'胡蝶','hudie@example.com','+8613800138012','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(13,'石敢当','shigandang@example.com','+8613800138013','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(14,'林黛玉','lindaiyu@example.com','+8613800138014','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(15,'贾宝玉','jiabaoyu@example.com','+8613800138015','2025-06-27 11:11:02','2025-06-27 11:11:02'),
(16,'张三','zhangsan@example.com','13800138000','2025-06-29 17:25:13','2025-06-29 17:25:13');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-01 12:01:22
