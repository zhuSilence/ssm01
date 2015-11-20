/*
Navicat MySQL Data Transfer

Source Server         : silence
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-11-19 20:03:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `be_using`
-- ----------------------------
DROP TABLE IF EXISTS `be_using`;
CREATE TABLE `be_using` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_id` int(11) DEFAULT NULL,
  `u_place` char(20) DEFAULT NULL,
  `u_state` int(11) DEFAULT NULL COMMENT '0��ʾʹ����\r\n            1��ʾͣ����\r\n            2��ʾά����',
  `u_mark` char(200) DEFAULT NULL,
  `is_using` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_use` (`d_id`),
  CONSTRAINT `FK_use` FOREIGN KEY (`d_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of be_using
-- ----------------------------

-- ----------------------------
-- Table structure for `buy_device`
-- ----------------------------
DROP TABLE IF EXISTS `buy_device`;
CREATE TABLE `buy_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer` varchar(20) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  `b_money` double DEFAULT NULL,
  `b_time` time DEFAULT NULL,
  `b_mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_buy` (`d_id`),
  CONSTRAINT `FK_buy` FOREIGN KEY (`d_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buy_device
-- ----------------------------

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_name` varchar(20) DEFAULT NULL,
  `d_desc` varchar(200) DEFAULT NULL,
  `d_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for `fix_device`
-- ----------------------------
DROP TABLE IF EXISTS `fix_device`;
CREATE TABLE `fix_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fixer` varchar(20) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  `fix_time` time DEFAULT NULL,
  `fix_mark` varchar(200) DEFAULT NULL,
  `is_fixed` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_fix` (`d_id`),
  CONSTRAINT `FK_fix` FOREIGN KEY (`d_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fix_device
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL COMMENT '�����������ʹ��',
  `locked` bit(1) DEFAULT b'0' COMMENT '0表示为锁定，1表示锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'silence', '123456', 'haha', '');
