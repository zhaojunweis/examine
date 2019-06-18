-- MySQL dump 10.13  Distrib 8.0.11, for linux-glibc2.12 (i686)
--
-- Host: localhost    Database: examine
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_exam`
--

DROP TABLE IF EXISTS `t_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '考试自增ID，在代码中不使用',
  `exam_name` varchar(255) NOT NULL COMMENT '考试名称',
  `exam_start_time` datetime DEFAULT NULL COMMENT '考试时间',
  `t_name` varchar(225) DEFAULT NULL COMMENT '创建老师的名称',
  `exam_paper_url` varchar(255) DEFAULT NULL COMMENT '考试试卷路径',
  `is_auto_start` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否自动开始，0表示不允许自动开始，1表示自动开始',
  `is_start` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开始，0表示没有开始，1表示已经开始',
  `is_pigeonhole` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否归档，0表示未归档，1表示已经归档',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '软删除，不允许物理删除，0表不允许删除，1表示允许删除',
  `is_finished` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='考试信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_exam`
--

LOCK TABLES `t_exam` WRITE;
/*!40000 ALTER TABLE `t_exam` DISABLE KEYS */;
INSERT INTO `t_exam` VALUES (100,'C','2019-06-14 22:20:12','zjw',NULL,1,1,0,0,0),(132,'Java','2019-06-14 21:52:17','zjw','zjw/明日目标.txt',0,0,1,0,1),(137,'kk','2019-06-14 22:26:56','zjw','zjw/.viminfo',1,0,1,0,1),(138,'ghf','2019-06-13 18:30:00','zjw','zjw/.viminfo',1,0,0,0,0),(139,'kjh','2019-06-14 22:27:20','zjw',NULL,1,0,0,0,0),(140,'kk','2019-06-14 22:28:57','zjw','zjw/.viminfo',1,0,0,0,0),(141,'ggss','2019-06-14 23:51:56','zjw','zjw/.viminfo',1,0,1,0,1);
/*!40000 ALTER TABLE `t_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_notification`
--

DROP TABLE IF EXISTS `t_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `examId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_notification`
--

LOCK TABLES `t_notification` WRITE;
/*!40000 ALTER TABLE `t_notification` DISABLE KEYS */;
INSERT INTO `t_notification` VALUES (18,'今天的考试到了',NULL),(19,'按Ip查询的接口没找到，student表缺少Ip字段',NULL),(20,'hello',NULL),(21,'789456sad',NULL),(22,'DSADSAXZ',NULL),(100,'今天英语六级过了',NULL),(101,'今天六级考试',100),(102,'今天六级比过',100),(103,'今天六级必过',100);
/*!40000 ALTER TABLE `t_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_perm`
--

DROP TABLE IF EXISTS `t_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_perm` (
  `perm_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `url` varchar(200) DEFAULT NULL COMMENT '权限接口',
  `desc` varchar(30) DEFAULT NULL COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_perm`
--

LOCK TABLES `t_perm` WRITE;
/*!40000 ALTER TABLE `t_perm` DISABLE KEYS */;
INSERT INTO `t_perm` VALUES (1,'/teacher_exam_before','考前操作初始化'),(2,'/teacher_exam_after  ','考后操作初始化'),(3,'/teacher_manage_summary ','考试概况初始化'),(4,'/teacher_manage_student ','学生信息初始化'),(5,'/teacher_manage_unlock  ','解锁绑定初始化'),(6,'/teacher_manage_notify ','通知管理初始化'),(7,'/teacher_exam_modify  ','考前编辑'),(8,'/submitTeacherLogin  ','教师登陆'),(9,'/teacher_main  ','教师首页初始化 '),(10,'/importStudentInfo  ','导入学生信息到数据库中'),(11,'/exportSubmitInfo ','导出提交信息'),(12,'/downLoadZipFile  ','下载所有学生提交的内容'),(13,'/saveStudent ','教师手动添加学生信息'),(14,'/updateDirtyData  ','更新学生脏数据'),(15,'/doUnbinding  ','学生信息解绑与IP'),(16,'/insertMessage  ','新建消息'),(17,'/selectAllMessage  ','查询所有消息'),(18,'/examinfo_modifier  ','编辑考试'),(19,'/updateExamInfo ','教师更新考试的接口'),(20,'/startExam  ','开始考试'),(21,'/stopExam ','停止考试'),(22,'/uploadExamPaper  ','教师上传考试试卷'),(23,'/saveExam ','添加考试'),(24,'/clearExam ','根据考试名称清理该场考试'),(25,'/admin_main','管理员界面'),(26,'/admin_teacher','教师管理界面初始化'),(27,'/saveTeacher','添加教师'),(28,'/removeTeacher','删除单个教师'),(29,'/updateUserAccount','更新用户信息'),(30,'/admin_Teacher_Edit','管理员修改教师的相关信息layer弹窗内容'),(31,'/updateTeacherById','根据Id编辑单个教师的信息'),(32,'/admin_exam','考试清理界面初始化'),(33,'/clearExam','学生查看通知消息'),(34,'/systemconfig','学生查看考试通知'),(35,'/admin_config','系统配置界面初始化'),(36,'/student_main','学生首页初始化'),(37,'/studentListdir','学生查看提交情况初始化'),(38,'/student_exam_upload','学生提交界面初始化'),(39,'/studentsubmit','学生提交答案'),(40,'/studentdoloadpage','学生查看通知消息'),(41,'/editPassword','修改密码'),(42,'/checkPasswordByname','根据用户名查询密码'),(43,'/downloadPaper','学生下载试卷'),(44,'/studentNotifyMessage','学生查看通知消息');
/*!40000 ALTER TABLE `t_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_role` (
  `role_id` int(4) NOT NULL,
  `role` char(20) NOT NULL,
  `description` char(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin','管理员'),(2,'teacher','教师'),(3,'student','学生');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_perm`
--

DROP TABLE IF EXISTS `t_role_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_role_perm` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `perm_id` int(11) NOT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_perm`
--

LOCK TABLES `t_role_perm` WRITE;
/*!40000 ALTER TABLE `t_role_perm` DISABLE KEYS */;
INSERT INTO `t_role_perm` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(3,36),(3,37),(3,38),(3,44),(3,40),(2,41),(2,42),(1,41),(1,42),(3,43),(3,44);
/*!40000 ALTER TABLE `t_role_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student`
--

DROP TABLE IF EXISTS `t_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID，一般不再在代码中使用\r\n',
  `s_sno` char(11) NOT NULL COMMENT '学号',
  `s_name` varchar(255) NOT NULL COMMENT '用户名',
  `s_class_id` tinyint(4) NOT NULL DEFAULT '1' COMMENT '班级ID',
  `s_score_Id` int(11) DEFAULT NULL COMMENT '考试名称',
  `s_is_delete` tinyint(4) DEFAULT '0' COMMENT '软删除，1表示是，2表示否',
  `role_id` int(11) DEFAULT '3' COMMENT '角色类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='学生信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student`
--

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` VALUES (47,'1610120002','赵俊伟',1,58,1,3),(53,'1610120005','徐文才',1,140,0,3),(54,'1610120006','才哥很棒',4,58,0,3),(72,'1610120016','蚂蚁',6,93,0,3),(78,'1610120010','梁卫',78,100,0,3),(79,'1610120009','天才',5,138,0,3),(80,'1231535','你好',4,138,0,3),(81,'1610120005','徐文才',1,138,0,3),(82,'1610120017','徐文才',6,100,1,3),(83,'1610120001','赵俊伟',45,100,0,3);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_submit`
--

DROP TABLE IF EXISTS `t_submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_submit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `s_sno` char(11) DEFAULT '0' COMMENT '是否已经登陆',
  `s_is_submit` tinyint(4) DEFAULT NULL COMMENT '是否已经提交',
  `g_gmt_create` datetime DEFAULT NULL COMMENT '提交时间，修改时间',
  `s_exam_paper` varchar(255) DEFAULT NULL COMMENT '试卷试卷',
  `s_login_address` char(32) DEFAULT NULL COMMENT '登陆IP',
  `s_is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0表示已经删除，1表示没有删除',
  `exam_Id` tinyint(4) DEFAULT NULL,
  `s_file_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_submit`
--

LOCK TABLES `t_submit` WRITE;
/*!40000 ALTER TABLE `t_submit` DISABLE KEYS */;
INSERT INTO `t_submit` VALUES (56,'1610120017',1,'2019-06-16 11:41:55',NULL,'192.168.88.1',0,100,NULL),(58,'1610120010',NULL,'2019-06-16 21:20:16',NULL,'10.12.52.226',0,100,NULL);
/*!40000 ALTER TABLE `t_submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system`
--

DROP TABLE IF EXISTS `t_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `s_exam_time` int(11) DEFAULT NULL COMMENT '每场考试的周期',
  `s_page_count` tinyint(4) DEFAULT NULL COMMENT '分页数量',
  `max_upload_size` bigint(20) DEFAULT NULL COMMENT '最大文件上传',
  `min_upload_size` bigint(20) DEFAULT NULL COMMENT '文件最小上传大小',
  `manual_open_time` int(11) DEFAULT NULL COMMENT '手动开启的时间阈值',
  `s_can_delete` tinyint(4) DEFAULT NULL COMMENT '是否语序教师删除，不允许物理删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='系统设置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_system`
--

LOCK TABLES `t_system` WRITE;
/*!40000 ALTER TABLE `t_system` DISABLE KEYS */;
INSERT INTO `t_system` VALUES (1,15,10,1234568,11,30,0);
/*!40000 ALTER TABLE `t_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_teacher`
--

DROP TABLE IF EXISTS `t_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `t_name` varchar(255) NOT NULL COMMENT '教师名称',
  `t_pass` varchar(32) NOT NULL COMMENT '教师密码',
  `t_is_admin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为管理员 1表示是，0表示不是',
  `t_is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '软删除，1表示是，0表示没有',
  `t_real_name` varchar(50) NOT NULL,
  `role_id` int(11) COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='教师表格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_teacher`
--

LOCK TABLES `t_teacher` WRITE;
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;
INSERT INTO `t_teacher` VALUES (113,'admin','admin',1,0,'admin',1),(120,'zjw','e10adc3949ba59abbe56e057f20f883e',0,0,'赵俊伟',2),(122,'xwc','e10adc3949ba59abbe56e057f20f883e',1,0,'徐文才',1);
/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-16 23:18:27
