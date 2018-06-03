/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : zbsjk

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-03 10:13:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `equipment_info`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_info`;
CREATE TABLE `equipment_info` (
  `equipment_number` varchar(50) NOT NULL COMMENT '设备编号',
  `register_number` varchar(50) DEFAULT NULL COMMENT '登记编号',
  `register_company` varchar(100) DEFAULT NULL COMMENT '登记单位',
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `user_id_card` varchar(50) DEFAULT NULL COMMENT '身份证',
  `company_name` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '信用代码',
  `user_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `equipment_province` varchar(50) DEFAULT NULL COMMENT '省',
  `equipment_city` varchar(50) DEFAULT NULL COMMENT '市',
  `equipment_area` varchar(50) DEFAULT NULL COMMENT '区',
  `equipment_brand` varchar(50) DEFAULT NULL COMMENT '设备品牌',
  `equipment_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
  `equipment_model` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `machine_number` varchar(50) DEFAULT NULL COMMENT '整机编号',
  `engine_number` varchar(50) DEFAULT NULL COMMENT '发动机编号',
  `engine_power` varchar(50) DEFAULT NULL COMMENT '发动机功率',
  `bucket_capacity` varchar(50) DEFAULT NULL COMMENT '铲斗容量',
  `machine_quality` varchar(50) DEFAULT NULL COMMENT '整机质量',
  `equipment_tyre` varchar(50) DEFAULT NULL COMMENT '轮胎',
  `equipment_track` varchar(50) DEFAULT NULL COMMENT '履带',
  `insurance_agency` varchar(50) DEFAULT NULL COMMENT '保险机构',
  `insurance_insurance` varchar(50) DEFAULT NULL COMMENT '保险险种',
  `equipment_long` varchar(50) DEFAULT NULL COMMENT '长',
  `equipment_wide` varchar(50) DEFAULT NULL COMMENT '宽',
  `equipment_hight` varchar(50) DEFAULT NULL COMMENT '高',
  `purchase_method` varchar(50) DEFAULT NULL COMMENT '购买方式',
  `fuel_variety` varchar(100) DEFAULT NULL COMMENT '燃料品种',
  `fuel_source` varchar(100) DEFAULT NULL COMMENT '燃料来源',
  `equipment_contaminants` varchar(100) DEFAULT NULL COMMENT '污染物',
  `noise_detection` varchar(100) DEFAULT NULL COMMENT '噪音检测',
  `discharge_stage` varchar(100) DEFAULT NULL COMMENT '排放阶段',
  `exhaust_emission` varchar(100) DEFAULT NULL COMMENT '尾气排放',
  `purchase_time` datetime DEFAULT NULL COMMENT '购买时间',
  `qr_code` varchar(100) DEFAULT NULL COMMENT '二维码名称',
  `audit_status` tinyint(4) DEFAULT '0' COMMENT '审核状态 0待审核，1审核通过',
  `audit_auditor` int(11) DEFAULT NULL COMMENT '审核人员',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rescue_status` tinyint(4) DEFAULT '0' COMMENT '是否应急救援 0否1是',
  `qr_code_path` varchar(100) DEFAULT NULL COMMENT '二维码地址',
  `pay_status` tinyint(4) DEFAULT '0' COMMENT '缴费状态,0未缴费，1已缴费',
  `equipment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `fuel_consumption` varchar(50) DEFAULT NULL COMMENT '耗油量',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='设备信息表';

-- ----------------------------
-- Records of equipment_info
-- ----------------------------
INSERT INTO `equipment_info` VALUES ('湘A长沙', null, null, '湘A', null, null, null, null, null, null, '长沙', '岳麓', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '70493113.jpg', '1', null, null, '2018-05-01 09:22:03', '1', 'http://localhost:8080/zbsjk/Report/70493113.jpg', '1', '1', '1.11', null);
INSERT INTO `equipment_info` VALUES ('湘B株洲', '湘B株洲', null, '湘B', null, null, null, null, null, null, '株洲', '衡阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, '2018-05-07 20:18:23', '0', null, '0', '2', null, null);
INSERT INTO `equipment_info` VALUES ('湘C株洲', '湘C株洲', null, '湘B', null, null, null, null, null, null, '株洲', '衡阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, '2018-05-12 21:47:08', '0', null, '0', '3', null, '1');
INSERT INTO `equipment_info` VALUES ('1株洲', '1株洲', null, '湘B', null, null, null, null, null, null, '株洲', '衡阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '50686609.jpg', '1', '1', '2018-05-12 21:48:12', '2018-05-12 21:47:48', '0', 'http://localhost:8080/zbsjk/Report/50686609.jpg', '0', '4', null, '1');

-- ----------------------------
-- Table structure for `equipment_info_delete_info`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_info_delete_info`;
CREATE TABLE `equipment_info_delete_info` (
  `equipment_number` varchar(50) NOT NULL COMMENT '设备编号',
  `register_number` varchar(50) DEFAULT NULL COMMENT '登记编号',
  `register_company` varchar(100) DEFAULT NULL COMMENT '登记单位',
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `user_id_card` varchar(50) DEFAULT NULL COMMENT '身份证',
  `company_name` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '信用代码',
  `user_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `equipment_province` varchar(50) DEFAULT NULL COMMENT '省',
  `equipment_city` varchar(50) DEFAULT NULL COMMENT '市',
  `equipment_area` varchar(50) DEFAULT NULL COMMENT '区',
  `equipment_brand` varchar(50) DEFAULT NULL COMMENT '设备品牌',
  `equipment_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
  `equipment_model` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `machine_number` varchar(50) DEFAULT NULL COMMENT '整机编号',
  `engine_number` varchar(50) DEFAULT NULL COMMENT '发动机编号',
  `engine_power` varchar(50) DEFAULT NULL COMMENT '发动机功率',
  `bucket_capacity` varchar(50) DEFAULT NULL COMMENT '铲斗容量',
  `machine_quality` varchar(50) DEFAULT NULL COMMENT '整机质量',
  `equipment_tyre` varchar(50) DEFAULT NULL COMMENT '轮胎',
  `equipment_track` varchar(50) DEFAULT NULL COMMENT '履带',
  `insurance_agency` varchar(50) DEFAULT NULL COMMENT '保险机构',
  `insurance_insurance` varchar(50) DEFAULT NULL COMMENT '保险险种',
  `equipment_long` varchar(50) DEFAULT NULL COMMENT '长',
  `equipment_wide` varchar(50) DEFAULT NULL COMMENT '宽',
  `equipment_hight` varchar(50) DEFAULT NULL COMMENT '高',
  `purchase_method` varchar(50) DEFAULT NULL COMMENT '购买方式',
  `fuel_variety` varchar(100) DEFAULT NULL COMMENT '燃料品种',
  `fuel_source` varchar(100) DEFAULT NULL COMMENT '燃料来源',
  `equipment_contaminants` varchar(100) DEFAULT NULL COMMENT '污染物',
  `noise_detection` varchar(100) DEFAULT NULL COMMENT '噪音检测',
  `discharge_stage` varchar(100) DEFAULT NULL COMMENT '排放阶段',
  `exhaust_emission` varchar(100) DEFAULT NULL COMMENT '尾气排放',
  `purchase_time` datetime DEFAULT NULL COMMENT '购买时间',
  `qr_code` varchar(100) DEFAULT NULL COMMENT '二维码名称',
  `audit_status` tinyint(4) DEFAULT '0' COMMENT '审核状态 0待审核，1审核通过',
  `audit_auditor` int(11) DEFAULT NULL COMMENT '审核人员',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rescue_status` tinyint(4) DEFAULT '0' COMMENT '是否应急救援 0否1是',
  `qr_code_path` varchar(100) DEFAULT NULL COMMENT '二维码地址',
  `pay_status` tinyint(4) DEFAULT '0' COMMENT '缴费状态,0未缴费，1已缴费',
  `equipment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `fuel_consumption` varchar(50) DEFAULT NULL COMMENT '耗油量',
  `operation_user` int(11) DEFAULT NULL COMMENT '操作人id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='删除的设备信息表';

-- ----------------------------
-- Records of equipment_info_delete_info
-- ----------------------------

-- ----------------------------
-- Table structure for `examination_info`
-- ----------------------------
DROP TABLE IF EXISTS `examination_info`;
CREATE TABLE `examination_info` (
  `examination_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `examination_results` int(11) DEFAULT NULL COMMENT '考试成绩',
  `equipment_number` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `watch_time` varchar(50) DEFAULT NULL COMMENT '观看时长',
  PRIMARY KEY (`examination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='考试成绩视频表';

-- ----------------------------
-- Records of examination_info
-- ----------------------------

-- ----------------------------
-- Table structure for `role_info`
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES ('1', '超级管理员');
INSERT INTO `role_info` VALUES ('2', '市级管理员');
INSERT INTO `role_info` VALUES ('3', '区级管理员');
INSERT INTO `role_info` VALUES ('4', '市级审核员');
INSERT INTO `role_info` VALUES ('5', '区级审核员');
INSERT INTO `role_info` VALUES ('6', '市级录入员');
INSERT INTO `role_info` VALUES ('7', '区级录入员');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  `user_sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `user_phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `user_province` varchar(20) DEFAULT NULL COMMENT '省',
  `user_city` varchar(20) DEFAULT NULL COMMENT '市',
  `user_area` varchar(20) DEFAULT NULL COMMENT '区',
  `user_pwd` varchar(50) DEFAULT '123456' COMMENT '密码默认123456',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  KEY `FK_Reference_1` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '超级管理员', null, '123456', '1', '湖南省', '长沙', '岳麓', '111111', null);

-- ----------------------------
-- Table structure for `user_info_delete_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info_delete_info`;
CREATE TABLE `user_info_delete_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  `user_sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `user_phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `user_province` varchar(20) DEFAULT NULL COMMENT '省',
  `user_city` varchar(20) DEFAULT NULL COMMENT '市',
  `user_area` varchar(20) DEFAULT NULL COMMENT '区',
  `user_pwd` varchar(50) DEFAULT '123456' COMMENT '密码默认123456',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operation_user` int(11) DEFAULT NULL COMMENT '操作人id',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='删除的用户表';

-- ----------------------------
-- Records of user_info_delete_info
-- ----------------------------
