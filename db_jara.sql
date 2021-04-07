-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: db_jira
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access_token`
--

DROP TABLE IF EXISTS `access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_token` (
  `id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbw4pq85heyxvxgm3h6ysan3nw` (`user_id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_token`
--

LOCK TABLES `access_token` WRITE;
/*!40000 ALTER TABLE `access_token` DISABLE KEYS */;
INSERT INTO `access_token` VALUES (38,0,'e7dbb4ffe10de73e70a0508749d5a2b4.2df240f2e828b09876bbf63715c8ad5f.1553346830927',30),(40,0,'2a6440228ed0b1f5d427b8c8da58ead0.75343cba2e0ec1dd0866b77716473dc7.1554025363716',27),(50,0,'d46ed481e9abbdc6c96e9367c8bd067b.c8bb3f5d8b90222a12b5950dbb0dd28a.1600529422641',41);
/*!40000 ALTER TABLE `access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action` (
  `id_action` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_action`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES (1,'Quan ly ho so',1);
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_detail`
--

DROP TABLE IF EXISTS `action_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_detail` (
  `id_action_detail` int(11) NOT NULL AUTO_INCREMENT,
  `action_detail_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id_topic` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_action_detail`),
  KEY `FKskimd7x9dat8v4du72t7cu34w` (`id_topic`),
  KEY `FKp2xpol27geyl71vaxjqnndn56` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_detail`
--

LOCK TABLES `action_detail` WRITE;
/*!40000 ALTER TABLE `action_detail` DISABLE KEYS */;
INSERT INTO `action_detail` VALUES (7,'task 1','2020-11-19 17:00:00','2020-10-19 17:00:00',2,2,41),(5,'Công Việc 1','2020-11-19 17:00:00','2020-10-19 17:00:00',1,1,41),(6,'Công việc 3','2020-11-19 17:00:00','2020-10-19 17:00:00',1,2,41);
/*!40000 ALTER TABLE `action_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id_department` int(11) NOT NULL AUTO_INCREMENT,
  `name_department` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_department`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Phòng HCNS',0),(2,'Phòng CNTT',0),(4,'Thư Viện',0),(5,'Phòng kỹ thuật',0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` double DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `FKdtvbvnxc0q7vsp1ev909vrtx6` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (42,'Bình Nhiên','Thái','',0,41);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (51),(51),(51),(51);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `name_role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,1,'Cấp 1',0),(2,2,'Cấp 2',0),(3,3,'Cấp 3',0),(4,4,'Cấp 4',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id_topic` int(11) NOT NULL AUTO_INCREMENT,
  `name_topic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id_department` int(11) DEFAULT NULL,
  `id_type_topic` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_topic`),
  KEY `FKmpkm9dwobadv8svf39wgu2e33` (`id_department`),
  KEY `FK4oxf1j1k3inxabbv939u243d` (`id_type_topic`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Quản lý hồ sơ, giấy tờ',0,1,3),(2,'Công tác lễ tân',0,1,3),(3,'Hoàn thành dự án ABC',0,2,3),(4,'Kiểm kho thực tế',0,5,4);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_topic`
--

DROP TABLE IF EXISTS `type_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_topic` (
  `id_type_topic` int(11) NOT NULL AUTO_INCREMENT,
  `name_type_topic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_type_topic`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_topic`
--

LOCK TABLES `type_topic` WRITE;
/*!40000 ALTER TABLE `type_topic` DISABLE KEYS */;
INSERT INTO `type_topic` VALUES (3,'Công việc phải làm',0),(4,'Công việc cần làm',0);
/*!40000 ALTER TABLE `type_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (41,'huythai@gmail.com','3aa43300a473374b426178c9f2f0a094','huythai@gmail.com',1),(44,'1','2','2',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work` (
  `id_work` int(10) NOT NULL,
  `work_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `topic` int(10) DEFAULT NULL,
  `implement` int(10) DEFAULT NULL,
  `start_day` date DEFAULT NULL,
  `end_day` date DEFAULT NULL,
  `work_status` bit(1) DEFAULT NULL,
  `id_implement` int(11) DEFAULT NULL,
  `id_topic` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_work`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (1,'Task 1',1,41,NULL,NULL,'',NULL,NULL);
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-07 15:30:27

CREATE TABLE `raci` (
  `id` INT NOT NULL,
  `ki_hieu` VARCHAR(45) NOT NULL,
  `ten` VARCHAR(45) NOT NULL,
  `mo_ta` VARCHAR(2000) NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'mô hình raci';
INSERT INTO `raci` (`id`, `ki_hieu`, `ten`, `mo_ta`) VALUES ('1', 'R', 'Resposible', 'Đây là người sẽ thực hiện công việc để hoàn thành nhiệm vụ. Mọi nhiệm vụ đều cần ít nhất một Bên chịu trách nhiệm, nhưng bạn có thể phân công thêm.');
INSERT INTO `raci` (`id`, `ki_hieu`, `ten`, `mo_ta`) VALUES ('2', 'A', 'Accountable', 'Người cuối cùng chịu trách nhiệm đối với việc hoàn thành chính xác và toàn diện về kết quả chuyển giao hoặc công việc, và là người giao nhiệm vụ cho những người Responsible. Nói cách khác, người này phải ký vào (phê duyệt) kết quả mà người Responsible cung cấp. Chỉ có duy nhất một Accounttable quy định cho mỗi nhiệm vụ, kết quả chuyển giao.');
INSERT INTO `raci` (`id`, `ki_hieu`, `ten`, `mo_ta`) VALUES ('3', 'C', 'Consulted', 'Những người được hỏi ý kiến, điển hình là chuyên gia giải quyết vấn đề.');
INSERT INTO `raci` (`id`, `ki_hieu`, `ten`, `mo_ta`) VALUES ('4', 'I', 'Informed', 'Các thành viên trong nhóm này chỉ cần cập nhật tiến độ dự án, thường chỉ khi hoàn thành nhiệm vụ hoặc chuyển giao chứ không tập trung vào chi tiết.');

CREATE TABLE `unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ma_don_vi` VARCHAR(45) NULL,
  `ten_don_vi` VARCHAR(300) NULL,
  `loai_don_vi` INT NULL,
  `ma_don_vi_cha` VARCHAR(45) NULL,
  `dia_chi` VARCHAR(2000) NULL,
  `kinh_do` FLOAT NULL,
  `vi_do` FLOAT NULL,
  `thoi_gian_tao` DATETIME NULL,
  `thoi_gian_cap_nhat` DATETIME NULL,
  PRIMARY KEY (`id`));
  ALTER TABLE `unit` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `work` 
DROP COLUMN `id_topic`,
DROP COLUMN `id_implement`,
DROP COLUMN `work_status`,
DROP COLUMN `end_day`,
DROP COLUMN `start_day`,
DROP COLUMN `implement`,
DROP COLUMN `topic`,
DROP COLUMN `work_name`,
ADD COLUMN `id_don_vi` INT NULL AFTER `id`,
ADD COLUMN `stt` VARCHAR(45) NULL AFTER `id_don_vi`,
ADD COLUMN `noi_dung_cv` VARCHAR(2000) NULL AFTER `stt`,
ADD COLUMN `id_user` INT NULL AFTER `noi_dung_cv`,
ADD COLUMN `time_thuc_hien` DATETIME NULL AFTER `id_user`,
ADD COLUMN `time_hoan_thanh` DATETIME NULL AFTER `time_thuc_hien`,
ADD COLUMN `bgd` VARCHAR(45) NULL AFTER `time_hoan_thanh`,
ADD COLUMN `nsth` VARCHAR(45) NULL AFTER `bgd`,
ADD COLUMN `ktkh` VARCHAR(45) NULL AFTER `nsth`,
ADD COLUMN `ktdt` VARCHAR(45) NULL AFTER `ktkh`,
ADD COLUMN `dhtt` VARCHAR(45) NULL AFTER `ktdt`,
ADD COLUMN `cntt` VARCHAR(45) NULL AFTER `dhtt`,
ADD COLUMN `ttvt` VARCHAR(45) NULL AFTER `cntt`,
ADD COLUMN `ket_qua` VARCHAR(2000) NULL AFTER `ttvt`,
CHANGE COLUMN `id_work` `id` INT(10) NOT NULL AUTO_INCREMENT ;


