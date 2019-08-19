/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.40 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `db_table` */

DROP TABLE IF EXISTS `db_table`;

CREATE TABLE `db_table` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键uuid',
  `father_uuid` varchar(32) DEFAULT NULL COMMENT '父uuid',
  `data_source_id` varchar(32) DEFAULT NULL COMMENT '数据源配置id',
  `code` varchar(200) NOT NULL COMMENT '编码',
  `dbet_name` varchar(200) DEFAULT NULL COMMENT '英文名称',
  `dbct_name` varchar(200) DEFAULT NULL COMMENT '中文名称',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `remarks` text COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据表';

/*Data for the table `db_table` */

insert  into `db_table`(`id`,`father_uuid`,`data_source_id`,`code`,`dbet_name`,`dbct_name`,`order_num`,`status`,`remarks`,`create_user_id`,`create_time`,`update_time`) values ('720ab903670549bba6d3c30d435ad300',NULL,NULL,'testTable','test_name','测试表',0,1,NULL,NULL,'2019-08-04 07:57:21','2019-08-04 07:57:21'),('d88da52c84e94634a4f1ea9db9bf150c',NULL,NULL,'tdtest','dbetName','测试table',0,1,NULL,NULL,'2019-08-11 08:20:55','2019-08-11 08:20:55');

/*Table structure for table `db_table_field` */

DROP TABLE IF EXISTS `db_table_field`;

CREATE TABLE `db_table_field` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键uuid',
  `father_uuid` varchar(32) DEFAULT NULL COMMENT '父uuid',
  `db_table_id` varchar(32) DEFAULT NULL COMMENT '数据表id',
  `code` varchar(200) DEFAULT NULL COMMENT '编码',
  `dbef_name` varchar(200) DEFAULT NULL COMMENT '英文名称',
  `dbcf_name` varchar(200) DEFAULT NULL COMMENT '中文名称',
  `dbf_type` varchar(200) DEFAULT NULL COMMENT '字段类型',
  `not_null` varchar(200) DEFAULT 'null' COMMENT '是否为空  null /not null',
  `pk` varchar(200) DEFAULT '否' COMMENT '是否是主键  否 /是',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `remarks` text COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设置表字段表';

/*Data for the table `db_table_field` */

insert  into `db_table_field`(`id`,`father_uuid`,`db_table_id`,`code`,`dbef_name`,`dbcf_name`,`dbf_type`,`not_null`,`pk`,`order_num`,`status`,`remarks`,`create_user_id`,`create_time`,`update_time`) values ('2147b1d2aa634af9914730b7fa451e0c','720ab903670549bba6d3c30d435ad300','720ab903670549bba6d3c30d435ad300','FieldCode','Field02','字段2',NULL,'null','否',0,1,NULL,NULL,'2019-08-06 18:45:34','2019-08-06 18:45:34'),('3576f47cd1054ce2a9a49c73e21f070e','720ab903670549bba6d3c30d435ad300','720ab903670549bba6d3c30d435ad300','FieldCode','Field03','字段3',NULL,'null','否',0,1,NULL,NULL,'2019-08-06 18:45:48','2019-08-06 18:45:48'),('fb18267009d04e8eb474fcb0e9c9728c','720ab903670549bba6d3c30d435ad300','720ab903670549bba6d3c30d435ad300','FieldCode','Field01','字段1',NULL,'null','否',0,1,NULL,NULL,'2019-08-06 18:45:16','2019-08-06 18:45:16');

/*Table structure for table `leaveapply` */

DROP TABLE IF EXISTS `leaveapply`;

CREATE TABLE `leaveapply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_instance_id` varchar(45) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `start_time` varchar(45) DEFAULT NULL,
  `end_time` varchar(45) DEFAULT NULL,
  `leave_type` varchar(45) DEFAULT NULL,
  `reason` varchar(400) DEFAULT NULL,
  `apply_time` varchar(100) DEFAULT NULL,
  `reality_start_time` varchar(45) DEFAULT NULL,
  `reality_end_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `leaveapply` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `permissionname` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `permissionname_UNIQUE` (`permissionname`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`pid`,`permissionname`) values (2,'人事审批'),(9,'出纳付款'),(8,'总经理审批'),(3,'财务审批'),(1,'部门领导审批'),(15,'采购审批');

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemlist` text NOT NULL,
  `total` float NOT NULL,
  `applytime` varchar(45) DEFAULT NULL,
  `applyer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `rolename_UNIQUE` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`rid`,`rolename`) values (9,'人事'),(10,'出纳员'),(11,'总经理'),(16,'财务管理员'),(1,'部门经理'),(13,'采购经理');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `permissionid` int(11) NOT NULL,
  PRIMARY KEY (`rpid`),
  KEY `a_idx` (`roleid`),
  KEY `b_idx` (`permissionid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`rpid`,`roleid`,`permissionid`) values (27,9,2),(28,10,9),(34,11,2),(35,11,8),(36,11,1),(37,13,15),(39,16,3),(42,1,3);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `no` varchar(200) DEFAULT NULL COMMENT '编号',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='学生表';

/*Data for the table `student` */

insert  into `student`(`id`,`no`,`name`,`create_time`) values (3,'20190726','dyp','2019-07-27 09:09:30'),(4,'20190726','dyp','2019-07-27 09:09:36');

/*Table structure for table `test_name` */

DROP TABLE IF EXISTS `test_name`;

CREATE TABLE `test_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `father_id` int(11) DEFAULT NULL COMMENT '父id',
  `Field02` varchar(200) DEFAULT NULL COMMENT '字段2',
  `Field03` varchar(200) DEFAULT NULL COMMENT '字段3',
  `Field01` varchar(200) DEFAULT NULL COMMENT '字段1',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `running` int(11) DEFAULT '1' COMMENT '是否使用  0：否   1：是',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='测试表';

/*Data for the table `test_name` */

insert  into `test_name`(`id`,`father_id`,`Field02`,`Field03`,`Field01`,`order_num`,`STATUS`,`running`,`create_user_id`,`create_time`,`update_time`) values (1,NULL,'Field02','Field03','Field01',0,1,1,NULL,'2019-08-06 19:22:00','2019-08-06 19:22:00'),(2,NULL,'Field02','Field03','Field01',0,1,1,NULL,'2019-08-06 19:22:59','2019-08-06 19:22:59'),(3,NULL,'test2','test3','test1',0,1,1,NULL,'2019-08-06 19:55:54','2019-08-06 19:55:54');

/*Table structure for table `test_table` */

DROP TABLE IF EXISTS `test_table`;

CREATE TABLE `test_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `father_id` int(11) DEFAULT NULL COMMENT '父id',
  `param02` varchar(200) DEFAULT NULL COMMENT '字段2',
  `param03` varchar(200) DEFAULT NULL COMMENT '字段3',
  `param01` varchar(200) DEFAULT NULL COMMENT '字段1',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `running` int(11) DEFAULT '1' COMMENT '是否使用  0：否   1：是',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表名';

/*Data for the table `test_table` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `tel` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`tel`,`age`) values (31,'xiaomi','1234','110',20),(33,'jon','1234','123',23),(34,'xiaocai','1234','111',32),(35,'WANG','1234','222',33);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `urid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`urid`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`urid`,`userid`,`roleid`) values (47,33,9),(48,33,1),(81,31,9),(82,31,10),(83,31,16),(84,31,1),(85,31,13),(86,35,11),(87,35,1),(92,34,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
