/*
Navicat MySQL Data Transfer

Source Server         : silence
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-11-27 17:48:06
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
  `date` datetime DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL COMMENT '�����������ʹ��',
  `locked` bit(1) DEFAULT b'0' COMMENT '0表示为锁定，1表示锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5472 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'silence', '66958F231D867FCAB23AC9D0200CAC1C', '2015-11-25 12:02:15', 'haha', '');
INSERT INTO `user` VALUES ('3', '樱桃小丸子', '123456', '2015-11-04 12:02:27', '3ggdfgty', '');
INSERT INTO `user` VALUES ('4', '娜美', '434356', '2015-11-08 12:02:31', '4y5ertg', '');
INSERT INTO `user` VALUES ('5', '海贼王', '56544', '2015-11-14 12:02:35', '5tytyrthg', '');
INSERT INTO `user` VALUES ('6', '黑骑一户', '656546', '2015-11-03 12:02:38', '6hgfhfgh', '');
INSERT INTO `user` VALUES ('7', '杨过', '7dfgdfs', '2015-11-28 12:02:43', '7gfhfhgji', '');
INSERT INTO `user` VALUES ('8', '小龙女', '8fhghfg', '2015-11-16 12:02:47', '8ui6645', '');
INSERT INTO `user` VALUES ('9', '孙悟空', '9hghr6ui', '2015-11-21 12:02:51', '9ty5688u', '');
INSERT INTO `user` VALUES ('10', '白骨精', 'fdsf', '2015-11-26 14:03:20', 'fdf', '');
INSERT INTO `user` VALUES ('5437', '大海', 'dahai', null, 'dahai', null);
INSERT INTO `user` VALUES ('5438', '什么', '324', '2015-11-26 16:00:29', '32', '');
INSERT INTO `user` VALUES ('5439', '你猜', '324', '2015-11-26 16:06:25', '324', '');
INSERT INTO `user` VALUES ('5440', '操控', '3', '2015-11-26 16:11:01', '342', '');
INSERT INTO `user` VALUES ('5441', '发撒扥', '34435', '2015-11-26 16:15:02', '45', '');
INSERT INTO `user` VALUES ('5442', '团', '565', '2015-11-26 16:15:53', '6546', '');
INSERT INTO `user` VALUES ('5444', '海明威', 'haimingwei', '2015-11-26 16:17:54', 'wrtt', '');
INSERT INTO `user` VALUES ('5445', '行', null, '2015-11-26 16:20:26', null, null);
INSERT INTO `user` VALUES ('5446', '朱朱', 'zhuxiang', '2015-11-26 16:21:24', 'zhuxiang', '');
INSERT INTO `user` VALUES ('5447', '达人', 'dar', '2015-11-26 16:24:47', 'dar', '');
INSERT INTO `user` VALUES ('5448', '什么鬼', 'nicl', '2015-11-26 16:28:07', 'nicl', '');
INSERT INTO `user` VALUES ('5450', '不是吧', 'buui', '2015-11-26 16:34:26', 'buui', '');
INSERT INTO `user` VALUES ('5451', '搞什么', 'gai', '2015-11-26 16:38:30', 'gao', '');
INSERT INTO `user` VALUES ('5452', '你妹', 'nimzi', '2015-11-26 16:41:15', 'miaf', '');
INSERT INTO `user` VALUES ('5453', '去死吧', 'qusi', '2015-11-26 16:46:09', 'wudf', '');
INSERT INTO `user` VALUES ('5455', '趋势', 'quui', '2015-11-26 16:54:32', 'quui', '');
INSERT INTO `user` VALUES ('5456', 'ad发', '森啊', '2015-11-26 16:59:14', 'affectl', '');
INSERT INTO `user` VALUES ('5457', '团费', 'fhhg', '2015-11-26 17:00:26', 'gdfglr', '');
INSERT INTO `user` VALUES ('5458', '32', '32', '2015-11-27 10:55:33', '3289', '');
INSERT INTO `user` VALUES ('5463', '朱翔', 'EA48576F30BE1669971699C09AD05C94', '2015-11-27 12:37:20', '123456', '');
INSERT INTO `user` VALUES ('5464', '小明', 'abc', '2015-11-27 13:46:45', 'abcd', '');
INSERT INTO `user` VALUES ('5465', '小红', 'abc8', '2015-11-27 13:47:39', 'bc', '');
INSERT INTO `user` VALUES ('5466', '小小兵', '54509', '2015-11-27 14:13:21', '545', '');
INSERT INTO `user` VALUES ('5468', '刘亦菲', '78786576', '2015-11-27 14:27:01', '84547676', '');
INSERT INTO `user` VALUES ('5470', '大雄', '2982B73118CF042CC93271C67F99C4EF', '2015-11-27 14:39:17', '342', '');
INSERT INTO `user` VALUES ('5471', '静香', '34C0200ECD6BFC8505B54CEEADF448B8', '2015-11-27 15:01:16', '654321', '');
