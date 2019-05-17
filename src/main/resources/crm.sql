/*
SQLyog Professional v12.08 (32 bit)
MySQL - 5.5.48 : Database - crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `crm`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_pass` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `login_times` int(11) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_admin` (`role_id`),
  CONSTRAINT `FK_admin` FOREIGN KEY (`role_id`) REFERENCES `admin_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin` */

insert  into `admin`(`id`,`role_id`,`user_name`,`user_pass`,`phone`,`login_times`,`create_date`,`login_date`,`status`,`remark`) values (1,1,'admin','123',NULL,NULL,NULL,NULL,'1',NULL),(4,2,'aocctu','123','18377784921',NULL,'2017-05-11',NULL,'1',NULL);

/*Table structure for table `admin_roles` */

DROP TABLE IF EXISTS `admin_roles`;

CREATE TABLE `admin_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `role_privelege` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin_roles` */

insert  into `admin_roles`(`id`,`role_name`,`role_privelege`,`create_date`,`remark`) values (1,'超级管理员','1,2,3,4,13,14,15,5,6,7,8,9,10,11,12',NULL,'不可删除'),(2,'普通管理员','1,4,13','2017-05-09',NULL);

/*Table structure for table `admin_roles_settings` */

DROP TABLE IF EXISTS `admin_roles_settings`;

CREATE TABLE `admin_roles_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `porder` int(11) DEFAULT '0',
  `url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_admin_roles_settings` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin_roles_settings` */

insert  into `admin_roles_settings`(`id`,`name`,`code`,`parent_id`,`porder`,`url`) values (1,'系统功能管理','settings_manager',NULL,0,'adminRolesSettings/select'),(2,'管理员管理','admin_manager',NULL,0,'admin/select'),(3,'管理员角色管理','adminRoles_manager',NULL,0,'adminRoles/select'),(4,'部门信息','department_manager',NULL,0,'department/select'),(5,'职位信息','jobInfo_manager',NULL,0,'jobInfo/select'),(6,'员工','employee_manager',NULL,0,'employee/select'),(7,'权限','rights_manager',NULL,0,'rights/select'),(8,'职位权限','jobRight',NULL,0,'jobRight/select'),(9,'客户基础信息','custom_manager',NULL,0,'custom/select'),(10,'销售客户跟踪信息','customInfo_manager',NULL,0,'customInfo/select'),(11,'咨询师跟单记录','consultRecord_manager',NULL,0,'consultRecord/select'),(12,'重置密码记录','resetpass_manager',NULL,0,'resetpass/select'),(13,'部门添加','department:add',4,0,NULL),(14,'部门修改','department:update',4,0,NULL),(15,'部门删除','department:delete',4,0,NULL);

/*Table structure for table `consultrecord` */

DROP TABLE IF EXISTS `consultrecord`;

CREATE TABLE `consultrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customId` int(11) NOT NULL,
  `consultStatu` char(1) NOT NULL,
  `consultManId` int(11) NOT NULL,
  `consultDate` date NOT NULL,
  `result` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customId` (`customId`),
  KEY `consultManId` (`consultManId`),
  CONSTRAINT `consultrecord_ibfk_1` FOREIGN KEY (`customId`) REFERENCES `custom` (`id`),
  CONSTRAINT `consultrecord_ibfk_2` FOREIGN KEY (`consultManId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `consultrecord` */

insert  into `consultrecord`(`id`,`customId`,`consultStatu`,`consultManId`,`consultDate`,`result`) values (1,100,'0',108,'2016-10-01',''),(2,101,'2',108,'2016-10-02','2016-10-06 : 测试,2016-10-13 : aaa'),(3,102,'2',108,'2016-10-03',NULL),(4,103,'1',108,'2016-10-04','2016-10-06 : 测试3'),(6,104,'0',102,'2016-10-08',NULL),(7,108,'0',108,'2016-10-08',NULL),(8,175,'0',102,'2016-10-13',NULL),(9,176,'0',103,'2016-10-13',NULL),(10,177,'0',103,'2016-10-13',NULL),(11,202,'0',103,'2016-10-13',NULL),(12,240,'0',120,'2017-05-11',NULL),(13,101,'1',100,'2017-05-10',NULL);

/*Table structure for table `custom` */

DROP TABLE IF EXISTS `custom`;

CREATE TABLE `custom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `education` char(20) DEFAULT NULL,
  `phoneNo` char(11) NOT NULL,
  `qq` char(20) DEFAULT NULL,
  `email` char(30) DEFAULT NULL,
  `customStatu` char(1) NOT NULL,
  `createDate` date NOT NULL,
  `inviteName` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

/*Data for the table `custom` */

insert  into `custom`(`id`,`name`,`education`,`phoneNo`,`qq`,`email`,`customStatu`,`createDate`,`inviteName`) values (100,'李qq','2','11146655666','18877779990','qq123456@163.com','3','2016-09-13','张qq'),(101,'李aa','2','11146655667','28877779990','aa123456@163.com','4','2016-09-27','张aa'),(102,'李zz','1','11146655668','38877779990','zz123456@163.com','3','2016-09-27','张zz'),(103,'李ww','1','14446655998','48877779990','ww123456@163.com','2','2016-09-29','张ww'),(104,'李ss','2','15546655666','58877779990','ss123456@163.com','3','2016-09-28','张ss'),(105,'李xx','2','16646655666','68877779990','xx123456@163.com','2','2016-09-28','张xx'),(106,'李cc','1','17746655666','78877779990','cc123456@163.com','3','2016-09-28','张cc'),(107,'李dd','2','18846655666','88877779990','dd123456@163.com','4','2016-09-25','张dd'),(108,'李ee','1','19946655666','98877779990','ee123456@163.com','3','2016-09-25','张ee'),(109,'李ff','3','11010655666','10877779990','ff123456@163.com','2','2016-09-26','张ff'),(110,'李gg','4','11111655666','11877779990','gg123456@163.com','3','2016-09-26','张gg'),(111,'章三三','1','13551255555','','','4','2016-10-03',''),(112,'李思思','4','13551255556','','','2','2016-10-03',''),(113,'王五','1','13551255557','','','2','2016-10-07',''),(114,'赵六','2','13551255558','','','2','2016-10-07',''),(115,'周七','1','13551255522','','','2','2016-10-09',''),(117,'李xy','2','11146655666','18877779990','qq123456@163.com','3','0016-09-13','张qq'),(118,'李wu','2','11146655667','28877779990','aa123456@163.com','4','0016-09-27','张aa'),(119,'李ab','1','11146655668','38877779990','zz123456@163.com','3','0016-09-27','张zz'),(120,'赵X天','1','13551246666','','','2','2016-10-11',NULL),(130,'custom1','1','13551255589','','','2','2016-10-12',NULL),(131,'刘一','本科','13551249919','11111111','liuyi@163.com','2','2016-08-16',''),(132,'陈二','本科','13552149920','11111112','chenre@qq.com','2','2016-08-16',''),(133,'张三','本科','13553049921','11111113','zhnagsan@163.com','2','2016-08-17',''),(134,'李四','本科','13553949922','11111114','lisi@qq.com','2','2016-08-17',''),(135,'王五','本科','13554849923','11111115','wangwu@163.com','2','2016-08-18',''),(136,'赵六','大专','13555749924','11111116','xhaoliu@qq.com','2','2016-08-18',''),(137,'孙七','大专','13556649925','11111117','sunqi@163.com','2','2016-08-19',''),(138,'周八','大专','13557549926','11111118','zhouba@qq.com','2','2016-08-19',''),(139,'吴九','大专','13558449927','11111119','wujiu@163.com','1','2016-08-20',''),(140,'郑十','大专','13559349928','11111120','zhengshi@qq.com','1','2016-08-20',''),(141,'许文星','本科','13560249929','11111111','liuyi@163.com','1','2016-08-16',''),(142,'刘纯华','本科','13561149930','11111112','chenre@qq.com','1','2016-08-16',''),(143,'杜海骏','本科','13562049931','11111113','zhnagsan@163.com','1','2016-08-17',''),(144,'徐磊磊','本科','13562949932','11111114','lisi@qq.com','1','2016-08-17',''),(145,'乔云飞','本科','13563849933','11111115','wangwu@163.com','1','2016-08-18',''),(146,'魏茂磊','大专','13564749934','11111116','xhaoliu@qq.com','1','2016-08-18',''),(147,'谌怡江','大专','13565649935','11111117','sunqi@163.com','1','2016-08-19',''),(148,'焦润超','大专','13566549936','11111118','zhouba@qq.com','1','2016-08-19',''),(149,'郑亮','大专','13567449937','11111119','wujiu@163.com','1','2016-08-20',''),(150,'苏哲','大专','13568349938','11111120','zhengshi@qq.com','1','2016-08-20',''),(151,'张悦','本科','13569249939','11111121','wujiu@164.com','1','2016-08-20',''),(152,'廖颖','本科','13570149940','11111122','zhengshi@qq.com','1','2016-08-20',''),(153,'陈柏君','大专','13571049941','11111123','wujiu@165.com','1','2016-08-16',''),(154,'张谦','大专','13571949942','11111124','zhengshi@qq.com','2','2016-08-16',''),(155,'董降龙','大专','13572849943','11111125','wujiu@166.com','2','2016-08-17',''),(156,'庞东国','大专','13573749944','11111126','zhengshi@qq.com','1','2016-08-17',''),(157,'孙嘉伸','大专','13574649945','11111127','wujiu@167.com','1','2016-08-18',''),(158,'罗亮','本科','13575549946','11111128','zhengshi@qq.com','1','2016-08-20',''),(159,'管祥凯','大专','13576449947','11111129','wujiu@168.com','1','2016-08-16',''),(160,'肖承敏','大专','13577349948','11111130','zhengshi@qq.com','1','2016-10-06',''),(161,'吴景昌','大专','13578249949','11111131','wujiu@169.com','1','2016-10-07',''),(162,'史伟琪','本科','13579149950','11111132','zhengshi@qq.com','1','2016-08-17',''),(163,'曹振鹏','本科','13580049951','11111133','wujiu@170.com','1','2016-10-06',''),(164,'兰庚坤','大专','13580949952','11111134','zhengshi@qq.com','1','2016-10-07',''),(165,'叶鹏','大专','13581849953','11111135','wujiu@171.com','1','2016-10-06',''),(166,'钱辉杰','大专','13582749954','11111136','zhengshi@qq.com','1','2016-10-07',''),(167,'张松','大专','13583649955','11111137','wujiu@172.com','1','2016-10-06',''),(168,'张杰','大专','13584549956','11111138','zhengshi@qq.com','2','2016-10-07',''),(169,'罗志君','大专','13585449957','11111139','wujiu@173.com','2','2016-10-06',''),(170,'王懋林','大专','13586349958','11111140','zhengshi@qq.com','2','2016-10-07',''),(171,'黄振寰','大专','13587249959','11111141','wujiu@174.com','1','2016-10-06',''),(172,'金平','本科','13588149960','11111142','zhengshi@qq.com','1','2016-10-07',''),(173,'卢昳晖','本科','13589049961','11111143','wujiu@175.com','1','2016-10-06',''),(174,'王瑞广','大专','13589949962','11111144','zhengshi@qq.com','1','2016-10-07',''),(175,'王嘉星','大专','13590849963','11111145','wujiu@176.com','3','2016-10-06',''),(176,'冯坤','大专','13591749964','11111146','zhengshi@qq.com','3','2016-10-07',''),(177,'邵飞','大专','13592649965','11111147','wujiu@177.com','3','2016-08-18',''),(178,'周煜欣','大专','13593549966','11111148','wujiu@177.com','1','2016-08-16',''),(179,'周松','本科','13594449967','11111149','zhengshi@qq.com','1','2016-09-16',''),(180,'试试','1','13551255584','','','2','2016-10-13',NULL),(181,'章翔','本科','13595349968','11111150','wujiu@178.com','1','2016-09-17',''),(182,'范铁赢','大专','13596249969','11111151','wujiu@178.com','2','2016-09-18',''),(183,'李洋','大专','13597149970','11111152','zhengshi@qq.com','2','2016-09-19',''),(184,'赵燕','大专','13598049971','11111153','wujiu@179.com','2','2016-09-20',''),(185,'王磊','大专','13598949972','11111154','wujiu@179.com','2','2016-09-21',''),(186,'朱先生','大专','13599849973','11111155','zhengshi@qq.com','2','2016-09-22',''),(187,'杜燕鹏','大专','13600749974','11111156','wujiu@180.com','2','2016-09-23',''),(188,'李常州','大专','13601649975','11111157','wujiu@180.com','1','2016-09-24',''),(189,'张子固','大专','13602549976','11111158','zhengshi@qq.com','1','2016-09-25',''),(190,'王哲','本科','13603449977','11111159','wujiu@181.com','1','2016-09-26',''),(191,'文斌','本科','13604349978','11111160','wujiu@181.com','1','2016-09-27',''),(192,'张强','大专','13605249979','11111161','zhengshi@qq.com','1','2016-09-28',''),(193,'叶平裕','大专','13606149980','11111162','wujiu@182.com','1','2016-09-29',''),(194,'王辉','大专','13607049981','11111163','wujiu@182.com','1','2016-09-30',''),(195,'李耀','大专','13607949982','11111164','zhengshi@qq.com','2','2016-08-17',''),(196,'杨园园','大专','13608849983','11111165','wujiu@183.com','2','2016-08-17',''),(197,'王惠梅','大专','13609749984','11111166','zhengshi@qq.com','2','2016-08-18',''),(198,'严成龙','大专','13610649985','11111167','wujiu@184.com','2','2016-08-17',''),(199,'谯林','大专','13611549986','11111168','zhengshi@qq.com','2','2016-08-18',''),(200,'刘权','大专','13612449987','11111169','wujiu@185.com','2','2016-08-16',''),(201,'孙嘉伸','大专','13613349988','11111170','zhengshi@qq.com','1','2016-08-16',''),(202,'肖承敏','本科','13614249989','11111171','wujiu@186.com','3','2016-08-17',''),(203,'曹振鹏','本科','13615149990','11111172','zhengshi@qq.com','0','2016-08-17',''),(204,'钱辉杰','大专','13616049991','11111173','wujiu@187.com','1','2016-08-18',''),(205,'罗志君','大专','13616949992','11111174','zhengshi@qq.com','2','2016-08-17',''),(206,'金平','大专','13617849993','11111175','wujiu@188.com','2','2016-08-18',''),(207,'王嘉星','大专','13618749994','11111176','zhengshi@qq.com','2','2016-08-16',''),(208,'周煜欣','大专','13619649995','11111177','wujiu@189.com','2','2016-08-16',''),(209,'范铁赢','本科','13620549996','11111178','zhengshi@qq.com','2','2016-08-17',''),(210,'王磊','本科','13621449997','11111179','wujiu@190.com','1','2016-08-17',''),(211,'李常州','大专','13622349998','11111180','zhengshi@qq.com','1','2016-08-18',''),(212,'文斌','大专','13623249999','11111181','wujiu@191.com','2','2016-08-17',''),(213,'王辉','大专','13624150000','11111182','zhengshi@qq.com','2','2016-08-18',''),(214,'小李1','大专','13625050001','11111183','wujiu@192.com','2','2016-08-19',''),(215,'小李2','本科','13625950002','11111184','zhengshi@qq.com','2','2016-08-20',''),(216,'小李3','本科','13626850003','11111185','wujiu@193.com','1','2016-08-21',''),(217,'小李4','大专','13627750004','11111186','zhengshi@qq.com','1','2016-08-22',''),(218,'小李5','大专','13628650005','11111187','wujiu@194.com','1','2016-08-23',''),(219,'小李6','大专','13629550006','11111188','zhengshi@qq.com','2','2016-08-24',''),(220,'小李7','大专','13630450007','11111189','wujiu@195.com','2','2016-08-25',''),(221,'小李8','本科','13631350008','11111190','zhengshi@qq.com','2','2016-08-26',''),(222,'小李9','本科','13632250009','11111191','wujiu@196.com','2','2016-08-27',''),(223,'小李10','大专','13633150010','11111192','zhengshi@qq.com','1','2016-08-16',''),(224,'小李11','大专','13634050011','11111193','wujiu@197.com','1','2016-08-17',''),(225,'小李12','大专','13634950012','11111194','zhengshi@qq.com','1','2016-08-17',''),(226,'小李13','大专','13635850013','11111195','wujiu@198.com','2','2016-08-18',''),(227,'小李14','大专','13636750014','11111196','zhengshi@qq.com','2','2016-08-17',''),(228,'小李15','本科','13637650015','11111197','wujiu@199.com','2','2016-08-18',''),(229,'小李16','本科','13638550016','11111198','zhengshi@qq.com','2','2016-08-19',''),(230,'小李17','大专','13639450017','11111199','wujiu@200.com','1','2016-08-20',''),(231,'小李18','大专','13640350018','11111200','zhengshi@qq.com','1','2016-08-21',''),(232,'小李19','大专','13641250019','11111201','wujiu@201.com','1','2016-08-22',''),(233,'小李20','大专','13642150020','11111202','zhengshi@qq.com','2','2016-09-21',''),(234,'小李21','本科','13643050021','11111203','wujiu@202.com','2','2016-09-22',''),(235,'小李22','本科','13643950022','11111204','zhengshi@qq.com','1','2016-08-21',''),(236,'小李23','大专','13644850023','11111205','wujiu@203.com','2','2016-08-22',''),(237,'小李24','大专','13645750024','11111206','zhengshi@qq.com','2','2016-10-02',''),(238,'小李25','大专','13646650025','11111207','wujiu@204.com','2','2016-10-02',''),(239,'等等','2','13551255552','','','0','2016-10-13',NULL),(240,'class4','123','123',NULL,'123','5','2017-05-11','123');

/*Table structure for table `custominfo` */

DROP TABLE IF EXISTS `custominfo`;

CREATE TABLE `custominfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customId` int(11) NOT NULL,
  `followManId` int(11) NOT NULL,
  `statu` char(1) DEFAULT NULL,
  `startDate` date NOT NULL,
  `planDate` date DEFAULT NULL,
  `lastFollowDate` date DEFAULT NULL,
  `mark` varchar(1000) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `customId` (`customId`),
  KEY `followManId` (`followManId`),
  CONSTRAINT `custominfo_ibfk_1` FOREIGN KEY (`customId`) REFERENCES `custom` (`id`),
  CONSTRAINT `custominfo_ibfk_2` FOREIGN KEY (`followManId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

/*Data for the table `custominfo` */

insert  into `custominfo`(`id`,`customId`,`followManId`,`statu`,`startDate`,`planDate`,`lastFollowDate`,`mark`) values (1,100,111,'1','2016-10-01','2016-10-14','2016-10-13','2016-10-12 : 手机关机了'),(2,101,111,'1','2016-10-01','2016-10-13','2016-10-13','2016-10-13 : dddddddd'),(3,102,111,'1','2016-10-02','2016-10-04','2016-10-06',NULL),(4,103,111,'3','2016-10-02','2016-10-04','2016-10-06',NULL),(5,104,111,'3','2016-10-04','2016-10-05','2016-10-06',''),(6,105,111,'4','2016-10-01',NULL,'2016-10-05',''),(7,107,111,'5','2016-10-04',NULL,NULL,NULL),(8,108,101,'0','2016-10-05','2016-10-04','2016-10-05','2016-10-05测试3'),(9,109,111,'4','2016-10-05',NULL,'2016-10-06','2016-10-05:test,2016-10-05:test2'),(10,111,111,'4','2016-10-07',NULL,'2016-10-07',''),(11,112,111,'0','2016-10-07','2016-10-13','2016-10-12',''),(12,113,111,'0','2016-10-07',NULL,'2016-10-07',''),(13,114,111,'0','2016-10-07',NULL,NULL,''),(16,113,111,'0','2016-10-07',NULL,NULL,''),(17,114,111,'0','2016-10-07',NULL,NULL,''),(26,130,104,'1','2016-10-12','2016-10-13','2016-10-12',',2016-10-12 : 未接通'),(27,130,110,'0','2016-10-13',NULL,NULL,''),(28,131,110,'0','2016-10-13',NULL,NULL,''),(29,132,110,'0','2016-10-13',NULL,NULL,''),(30,133,110,'0','2016-10-13',NULL,NULL,''),(31,134,111,'0','2016-10-13','2016-10-19','2016-10-13',''),(32,135,111,'0','2016-10-13',NULL,NULL,''),(33,136,111,'0','2016-10-13',NULL,NULL,''),(34,137,111,'0','2016-10-13',NULL,NULL,''),(35,138,113,'0','2016-10-13',NULL,NULL,''),(36,154,113,'0','2016-10-13',NULL,NULL,''),(37,155,113,'0','2016-10-13',NULL,NULL,''),(38,168,113,'0','2016-10-13',NULL,NULL,''),(39,136,110,'0','2016-10-13',NULL,NULL,''),(40,137,113,'0','2016-10-13',NULL,NULL,''),(41,138,113,'0','2016-10-13',NULL,NULL,''),(42,154,111,'0','2016-10-13',NULL,NULL,''),(43,155,110,'0','2016-10-13',NULL,NULL,''),(44,168,110,'0','2016-10-13',NULL,NULL,''),(45,169,110,'0','2016-10-13',NULL,NULL,''),(46,170,113,'0','2016-10-13',NULL,NULL,''),(47,180,110,'0','2016-10-13',NULL,NULL,''),(48,182,110,'0','2016-10-13',NULL,NULL,''),(49,183,110,'0','2016-10-13',NULL,NULL,''),(50,184,110,'0','2016-10-13',NULL,NULL,''),(51,185,110,'0','2016-10-13',NULL,NULL,''),(52,186,110,'0','2016-10-13',NULL,NULL,''),(53,187,110,'0','2016-10-13',NULL,NULL,''),(54,195,110,'0','2016-10-13',NULL,NULL,''),(55,196,110,'0','2016-10-13',NULL,NULL,''),(56,197,110,'0','2016-10-13',NULL,NULL,''),(57,198,110,'0','2016-10-13',NULL,NULL,''),(58,199,111,'0','2016-10-13',NULL,NULL,''),(59,200,111,'0','2016-10-13',NULL,NULL,''),(60,206,111,'0','2016-10-13',NULL,NULL,''),(61,207,111,'0','2016-10-13',NULL,NULL,''),(62,208,111,'0','2016-10-13',NULL,NULL,''),(63,212,111,'0','2016-10-13',NULL,NULL,''),(64,213,111,'0','2016-10-13',NULL,NULL,''),(65,214,111,'0','2016-10-13',NULL,NULL,''),(66,215,111,'0','2016-10-13',NULL,NULL,''),(67,219,111,'0','2016-10-13',NULL,NULL,''),(68,220,111,'0','2016-10-13',NULL,NULL,''),(69,221,113,'0','2016-10-13',NULL,NULL,''),(70,222,113,'0','2016-10-13',NULL,NULL,''),(71,226,113,'0','2016-10-13',NULL,NULL,''),(72,227,113,'0','2016-10-13',NULL,NULL,''),(73,228,113,'0','2016-10-13',NULL,NULL,''),(74,229,113,'0','2016-10-13',NULL,NULL,''),(75,233,113,'0','2016-10-13',NULL,NULL,''),(76,234,113,'0','2016-10-13',NULL,NULL,''),(77,236,113,'0','2016-10-13',NULL,NULL,''),(78,237,113,'0','2016-10-13',NULL,NULL,''),(79,238,113,'0','2016-10-13',NULL,NULL,''),(80,239,106,'0','2016-10-13',NULL,NULL,''),(81,240,120,'1','2017-05-01','2017-05-03','2017-05-02',NULL),(82,100,101,'4','2017-05-01','2017-05-03','2017-05-02',NULL),(83,100,100,'5','2017-05-01','2017-05-03','2017-05-02',NULL),(85,100,100,'2','2017-05-01','2017-05-03','2017-05-02',NULL);

/*Table structure for table `delivery` */

DROP TABLE IF EXISTS `delivery`;

CREATE TABLE `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_num` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `material_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `model` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ichiban` int(11) DEFAULT NULL,
  `receive_num` int(11) DEFAULT NULL,
  `materials_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `apply_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `apply_date` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `delivery_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `delivery_date` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `delivery` */

insert  into `delivery`(`id`,`batch_num`,`material_code`,`model`,`description`,`ichiban`,`receive_num`,`materials_status`,`apply_name`,`apply_date`,`delivery_name`,`delivery_date`) values (1,'','34','11','222',333,123,'领料中','admin','2019-05-17 14:15:53',NULL,NULL),(2,'','34','11','222',333,123,'已发料','admin','2019-05-17 14:20:48','admin','2019-05-17 15:49:37'),(3,'','34','11','222',333,123,'已发料','admin','2019-05-17 14:21:44','admin','2019-05-17 16:31:01'),(4,'','34','11','222',333,123,'已发料','admin','2019-05-17 14:24:11','admin','2019-05-17 16:18:45'),(5,'','21','12','12',120,50,'已发料','admin','2019-05-17 16:47:43','admin','2019-05-17 16:53:05'),(6,'','201905171655','Ma1',NULL,1500,500,'已发料','admin','2019-05-17 16:56:05','admin','2019-05-17 16:56:28');

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dname` char(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`dname`) values (1,'技术部'),(2,'销售部'),(3,'线上咨询部'),(4,'线下咨询部'),(5,'销售支持部'),(6,'管理部'),(7,'666');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(30) NOT NULL,
  `pass` char(120) NOT NULL,
  `nickname` char(30) NOT NULL,
  `realname` char(30) NOT NULL,
  `jobInfoId` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL,
  `phoneNo` char(11) NOT NULL,
  `officeTel` char(11) DEFAULT NULL,
  `workStatu` char(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `jobInfoId` (`jobInfoId`),
  KEY `departmentId` (`departmentId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`jobInfoId`) REFERENCES `jobinfo` (`id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`username`,`pass`,`nickname`,`realname`,`jobInfoId`,`departmentId`,`phoneNo`,`officeTel`,`workStatu`) values (100,'superadmin@oaec.com','299bbc6453f96d5cb3e91f389a2df191748fb32623c293eeabec236f3184c468','superAdmin','超级管理员',1,1,'13551255588','61196200','1'),(101,'admin@oaec.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','管理员','管理员',2,1,'12222222222','22222222222','1'),(102,'zz123456@qq.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','zz','雏田',3,4,'13333333333','33333333333','1'),(103,'ww123456@qq.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','ww','天天',3,4,'14444444444','44444444444','1'),(104,'ss123456@qq.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','ss','宁次',5,3,'15555555555','55555555555','1'),(105,'onlineManager6@oaec.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','onlineManager','网络咨询主管',6,3,'16666666666','66666666666','1'),(106,'online@oaec.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','onlineConsult','卡卡西11',6,2,'17777777777','77777777777','1'),(107,'dd123456@qq.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','dd','木叶丸',5,3,'18888888888','88888888888','1'),(108,'consulter@oaec.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','Consulter','纲手',3,4,'19999999999','99999999999','1'),(110,'qq1111111@qq.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','jj','佐助',8,2,'1112222333','98765432100','1'),(111,'saler@oaec.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','saler','鸣人',8,2,'13551445369','62200696','1'),(113,'666666@qq.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','OAEC20','小李',8,2,'13551255566','61196201','1'),(115,'assistant@oaec.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','Assistant','销售助理',7,5,'13551269999','61196289','1'),(117,'qq5555@.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','OAEC892','孙菲菲',2,1,'13551255599','61196211','1'),(118,'qq12345@163.com','c61bfc278c72560e5cc3a7d44154b6e3d2dfabebae62c97bd16b8d651c23eeac','OAEC139','方法',5,3,'13551255533','61196288','1'),(120,'Aocctu','12345','123','123',3,1,'123','123','1'),(121,'admin','123','123','管理员',1,1,'13','12','1'),(132,'9086','123','a','李华武',11,6,'18319271291','123','1'),(133,'1104','123','123','123',2,1,'1235','123','1'),(134,'add','123','152','165',3,2,'18319271291','123','1');

/*Table structure for table `express` */

DROP TABLE IF EXISTS `express`;

CREATE TABLE `express` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_iphone` char(11) COLLATE utf8_bin NOT NULL,
  `exp_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `pay_type` varchar(50) COLLATE utf8_bin NOT NULL,
  `exp_cost` decimal(10,2) DEFAULT NULL,
  `exp_company_id` int(11) NOT NULL,
  `exp_num` varchar(50) COLLATE utf8_bin NOT NULL,
  `exp_status` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `create_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_date` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `exp_company_id` (`exp_company_id`),
  CONSTRAINT `express_ibfk_1` FOREIGN KEY (`exp_company_id`) REFERENCES `expresscompany` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `express` */

insert  into `express`(`id`,`exp_iphone`,`exp_name`,`pay_type`,`exp_cost`,`exp_company_id`,`exp_num`,`exp_status`,`create_name`,`create_date`,`remark`) values (3,'123456789','2233','到付','225.50',2,'259','已接收','admin','2019-04-29 00:00:00','6666'),(4,'18319271291','李华武','到付','200.50',3,'8884522','已接收','9086','2019-04-30 00:00:00',NULL),(5,'11','11','到付','11.00',1,'11','已接收','admin','2019-05-05 16:50:02','11'),(8,'33','33','到付','33.00',3,'11\r\n22\r\n33\r\n44\r\n55\r\n66\r\n77','已接收','admin','2019-05-05 17:40:06',NULL),(9,'12','12','寄付','0.00',2,'12\r\n12\r\n13\r\n14','已接收','admin','2019-05-09 09:13:47',NULL),(10,'22','22','寄付','0.00',2,'23\r\n45\r\n67','已接收','admin','2019-05-09 09:24:50',NULL),(11,'22','22','寄付','0.00',2,'23\r\n45\r\n67','已接收','admin','2019-05-09 09:24:50',NULL),(12,'22','22','寄付','0.00',2,'23\r\n45\r\n67','已接收','admin','2019-05-09 09:24:50',NULL),(13,'123','321','寄付','0.00',2,'13\r','已接收','admin','2019-05-09 09:28:06',NULL),(14,'123','321','寄付','0.00',2,'23\r','已接收','admin','2019-05-09 09:28:06',NULL),(15,'123','321','寄付','0.00',2,'45\r','已接收','admin','2019-05-09 09:28:06',NULL),(16,'123','321','寄付','0.00',2,'67','已录入','admin','2019-05-09 09:28:06',NULL),(17,'18377784921','李华武','寄付','0.00',1,'123456\r','已录入','admin','2019-05-09 09:29:14',NULL),(18,'18377784921','李华武','寄付','0.00',1,'234567\r','已录入','admin','2019-05-09 09:29:14',NULL),(19,'18377784921','李华武','寄付','0.00',1,'45678\r','已录入','admin','2019-05-09 09:29:14',NULL),(20,'18377784921','李华武','寄付','0.00',1,'56789','已录入','admin','2019-05-09 09:29:14',NULL),(21,'18319271291','小Q','寄付','0.00',2,'777\r','已接收','admin','2019-05-10 08:59:48',NULL),(22,'18319271291','小Q','寄付','0.00',2,'666\r','已录入','admin','2019-05-10 08:59:48',NULL),(23,'18319271291','小Q','寄付','0.00',2,'555\r','已录入','admin','2019-05-10 08:59:48',NULL),(24,'18319271291','小Q','寄付','0.00',2,'999','已接收','admin','2019-05-10 08:59:48',NULL),(26,'1231','李华武','寄付','0.00',1,'123\r','已录入','admin','2019-05-10 11:06:05',NULL),(27,'1231','李华武','寄付','0.00',1,'asd11','已接收','admin','2019-05-10 11:06:05',NULL),(28,'13245','小P','寄付','0.00',3,'5558899\r','已录入','admin','2019-05-16 09:54:54',NULL),(29,'13245','小P','寄付','0.00',3,'4444489\r','已录入','admin','2019-05-16 09:54:54',NULL),(30,'13245','小P','寄付','0.00',3,'6666622\r','已录入','admin','2019-05-16 09:54:54',NULL),(31,'13245','小P','寄付','0.00',3,'1234956','已录入','admin','2019-05-16 09:54:54',NULL);

/*Table structure for table `expresscompany` */

DROP TABLE IF EXISTS `expresscompany`;

CREATE TABLE `expresscompany` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_company` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `expresscompany` */

insert  into `expresscompany`(`id`,`exp_company`) values (1,'顺丰'),(2,'EMS'),(3,'申通'),(4,'韵达'),(5,'中通'),(6,'百世'),(7,'圆通'),(8,'德邦'),(9,'天天');

/*Table structure for table `expresssort` */

DROP TABLE IF EXISTS `expresssort`;

CREATE TABLE `expresssort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_num` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `model` char(11) COLLATE utf8_bin DEFAULT NULL,
  `material_type` char(11) COLLATE utf8_bin DEFAULT NULL,
  `quantity` char(11) COLLATE utf8_bin DEFAULT NULL,
  `fault` char(11) COLLATE utf8_bin DEFAULT NULL,
  `type` char(11) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `color` char(11) COLLATE utf8_bin DEFAULT NULL,
  `sn` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `create_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_date` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `confirm_fault` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `repair_type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `position` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `expresssort` */

insert  into `expresssort`(`id`,`exp_num`,`model`,`material_type`,`quantity`,`fault`,`type`,`remark`,`color`,`sn`,`create_name`,`create_date`,`confirm_fault`,`repair_type`,`position`) values (3,'22','33','1',NULL,'44','1','55','66','77',NULL,'2019-05-07 16:19:20','okbj','待报','来料检验'),(4,'q','w','3',NULL,'e','2','r','t','y',NULL,NULL,'OK','待报','入库'),(6,'12','12','2',NULL,'12','1','12','12','12222','admin','2019-05-07 17:16:03','aaa','待一级翻新','入库'),(7,'123','12','1',NULL,'31','1','12','3','123','admin','2019-05-14 09:03:59',NULL,NULL,'分拣录入'),(8,'123','123','1',NULL,'12','1','13','2','13','admin','2019-05-14 09:04:32',NULL,NULL,'入库'),(9,'13','12','3','13','12','1','123','31',NULL,'admin','2019-05-14 09:04:46','1','待三级翻新','来料检验'),(10,'3','4','2',NULL,'5','1','5','6','77','admin','2019-05-14 09:05:08','2','待一级翻新','入库');

/*Table structure for table `jobinfo` */

DROP TABLE IF EXISTS `jobinfo`;

CREATE TABLE `jobinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job` char(30) NOT NULL,
  `departmentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `departmentId` (`departmentId`),
  CONSTRAINT `jobinfo_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `jobinfo` */

insert  into `jobinfo`(`id`,`job`,`departmentId`) values (1,'超级管理员',1),(2,'管理员',1),(3,'咨询师',4),(4,'咨询师主管',4),(5,'网络咨询',3),(6,'网络咨询主管',3),(7,'销售助理',5),(8,'销售员',2),(9,'销售主管',2),(10,'程序员',1),(11,'快递管理员',6);

/*Table structure for table `jobright` */

DROP TABLE IF EXISTS `jobright`;

CREATE TABLE `jobright` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobInfoId` int(11) NOT NULL,
  `rightid` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jobInfoId` (`jobInfoId`),
  KEY `rightId` (`rightid`(255)),
  CONSTRAINT `jobright_ibfk_1` FOREIGN KEY (`jobInfoId`) REFERENCES `jobinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `jobright` */

insert  into `jobright`(`id`,`jobInfoId`,`rightid`) values (31,1,'1,10,11,12,13,2,14,15,16,17,3,18,19,20,21,4,22,23,24,25,5,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,48,49,50,51,52,53,55,56,57,58,54,66,67,68,69,65,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87'),(33,4,'2,3,4,5,6,7'),(36,2,'1,10,11,12,13,2,14,15,16,17,3,18,19,20,21,4,22,23,24,25,5,26,27,28,29,6,30,31,32,33,7,34,35,36,37,8,38,39,40,41,9,42,43,44,45'),(37,11,'48,49,50,52,53,55,56,57,54,67,68,65,70,71,74,75,76,77,78,79,80,81,82,83,84,85,86,87');

/*Table structure for table `partswarehouse` */

DROP TABLE IF EXISTS `partswarehouse`;

CREATE TABLE `partswarehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `model` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `ichiban` int(11) DEFAULT NULL,
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `scrap` int(11) DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_date` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `materials_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `receive_num` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `partswarehouse` */

insert  into `partswarehouse`(`id`,`material_code`,`model`,`quantity`,`ichiban`,`description`,`scrap`,`remark`,`type`,`status`,`create_name`,`create_date`,`materials_status`,`receive_num`) values (1,'111','12',12,111,'12',0,'32','1','来料','name','2019-05-15',NULL,NULL),(2,'12','2155',32,0,'23',320,'34','2','来料','admin','2019-05-15 10:17:42',NULL,NULL),(6,'aa','bb',11,0,'aa',11,'asd','2','来料','admin','2019-05-15 10:23:12',NULL,NULL),(8,'12','12',1,0,'12',1,'12','2','来料','admin','2019-05-15 16:21:12',NULL,NULL),(9,'21','12',-38,70,'12',0,'12','1','来料','admin','2019-05-15 16:23:38','',''),(11,'34','11',87,87,'222',0,'445','1','来料','admin','2019-05-15 18:20:32','',''),(12,'201905171655','Ma1',1000,1000,NULL,0,NULL,'1','来料','admin','2019-05-17 16:55:30','','');

/*Table structure for table `resetpass` */

DROP TABLE IF EXISTS `resetpass`;

CREATE TABLE `resetpass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(30) NOT NULL,
  `phoneNo` char(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `resetpass` */

insert  into `resetpass`(`id`,`username`,`phoneNo`) values (4,'assistant@oaec.com','13551269999'),(5,'admin','123'),(7,'Aocctu','123'),(8,'admin','123');

/*Table structure for table `rights` */

DROP TABLE IF EXISTS `rights`;

CREATE TABLE `rights` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rightName` char(40) NOT NULL,
  `rightType` char(1) NOT NULL,
  `url` char(30) DEFAULT NULL,
  `pid` int(11) DEFAULT '0',
  `rightCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Data for the table `rights` */

insert  into `rights`(`rid`,`rightName`,`rightType`,`url`,`pid`,`rightCode`) values (1,'权限管理','1','rights/select',0,'rights:mamager'),(2,'部门信息','1','department/select',0,'rights:select'),(3,'职位信息','1','jobInfo/select',0,'rights:select'),(4,'员工信息','1','employee/select',0,'rights:select'),(5,'职位权限','1','jobRight/select',0,'rights:select'),(6,'客户基础信息','1','custom/select',0,'rights:select'),(7,'销售客户跟踪信息','1','customInfo/select',0,'rights:select'),(8,'咨询师跟单记录','1','consultRecord/select',0,'rights:select'),(9,'重置密码','1','resetpass/select',0,'rights:select'),(10,'添加权限','2',NULL,1,'rights:add'),(11,'修改权限','2',NULL,1,'rights:update'),(12,'删除权限','2',NULL,1,'rights:delete'),(13,'查询权限','2',NULL,1,'rights:select'),(14,'部门添加','2',NULL,2,'department:add'),(15,'部门修改','2',NULL,2,'department:update'),(16,'部门删除','2',NULL,2,'department:delete'),(17,'部门查询','2',NULL,2,'department:select'),(18,'职位添加','2',NULL,3,'jobInfo:add'),(19,'职位修改','2',NULL,3,'jobInfo:update'),(20,'职位删除','2',NULL,3,'jobInfo:delete'),(21,'职位查询','2',NULL,3,'jobInfo:select'),(22,'员工添加','2',NULL,4,'employee:add'),(23,'员工修改','2',NULL,4,'employee:update'),(24,'员工删除','2',NULL,4,'employee:delete'),(25,'员工查询','2',NULL,4,'employee:select'),(26,'职位权限添加','2',NULL,5,'jobRight:add'),(27,'职位权限修改','2',NULL,5,'jobRight:update'),(28,'职位权限删除','2',NULL,5,'jobRight:delete'),(29,'职位权限查询','2',NULL,5,'jobRight:select'),(30,'客户信息添加','2',NULL,6,'custom:add'),(31,'客户信息修改','2',NULL,6,'custom:update'),(32,'客户信息删除','2',NULL,6,'custom:delete'),(33,'客户信息删除','2',NULL,6,'custom:select'),(34,'销售客户添加','2',NULL,7,'customInfo:add'),(35,'销售客户修改','2',NULL,7,'customInfo:update'),(36,'销售客户删除','2',NULL,7,'customInfo:delete'),(37,'销售客户查询','2',NULL,7,'customInfo:select'),(38,'咨询师跟单添加','2',NULL,8,'consultRecord:add'),(39,'咨询师跟单修改','2',NULL,8,'consultRecord:update'),(40,'咨询师跟单删除','2',NULL,8,'consultRecord:delete'),(41,'咨询师跟单查询','2',NULL,8,'consultRecord:select'),(42,'重置密码添加','2',NULL,9,'resetpass:add'),(43,'重置密码修改','2',NULL,9,'resetpass:update'),(44,'重置密码删除','2',NULL,9,'resetpass:delete'),(45,'重置密码查询','2',NULL,9,'resetpass:select'),(48,'快递录入','1','express/select',0,'rights:select'),(49,'快递添加','2',NULL,48,'express:add'),(50,'快递修改','2',NULL,48,'express:update'),(51,'快递删除','2',NULL,48,'express:delete'),(52,'快递查询','2',NULL,48,'express:select'),(53,'快递公司','1','expressCompany/select',0,'rights:select'),(54,'快递接收','1','expressReceive/select',0,'rights:select'),(55,'快递公司添加','2',NULL,53,'expressCompany:add'),(56,'快递公司修改','2',NULL,53,'expressCompany:update'),(57,'快递公司查询','2',NULL,53,'expressCompany:select'),(58,'快递公司删除','2',NULL,53,'expressCompany:delete'),(65,'分拣入库','1','expressSort/select',0,'rights:select'),(66,'快递接收添加','2',NULL,54,'expresReceive:add'),(67,'快递接收修改','2',NULL,54,'expresReceive:update'),(68,'快递接收查询','2',NULL,54,'expresReceive:select'),(69,'快递接收删除','2',NULL,54,'expressCompany:delete'),(70,'分检入库查询','2',NULL,65,'expressSort:select'),(71,'分检入库添加','2',NULL,65,'expressSort:add'),(72,'分检入库修改','2',NULL,65,'expressSort:update'),(73,'分检入库删除','2',NULL,65,'expressSort:delete'),(74,'来料检验','2',NULL,65,'expressSort:examine'),(75,'入库','2',NULL,65,'expressSort:storage'),(76,'配件仓库','1','partsWarehouse/select',0,'rights:select'),(77,'配件来料入库','2',NULL,76,'partsWarehouse:add'),(78,'配件仓库查询','2',NULL,76,'partsWarehouse:select'),(79,'配件仓库修改','2',NULL,76,'partsWarehouse:update'),(80,'配件仓库删除','2',NULL,76,'partsWarehouse:delete'),(81,'配件仓库盘点','2',NULL,76,'partsWarehouse:inventory'),(82,'申请领料','1','apply/select',0,'rights:select'),(83,'申请领料查询','2',NULL,82,'apply:select'),(84,'申请领料修改','2',NULL,82,'apply:update'),(85,'配件发料','1','delivery/select',0,'right:select'),(86,'配件发料查询','2',NULL,85,'delivery:select'),(87,'配件批量发料','2',NULL,85,'delivery:update');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
