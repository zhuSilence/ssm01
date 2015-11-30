/*
Navicat MySQL Data Transfer

Source Server         : silence
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-11-30 12:45:06
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
-- Table structure for `nav`
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(20) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `iconCls` varchar(20) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `nid` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nav
-- ----------------------------
INSERT INTO `nav` VALUES ('1', '系统管理', 'closed', 'icon-system', null, '0');
INSERT INTO `nav` VALUES ('2', '用户管理', 'closed', 'icon-manager', null, '0');
INSERT INTO `nav` VALUES ('3', '设备管理', 'closed', 'icon-user', null, '0');
INSERT INTO `nav` VALUES ('4', '使用列表', 'open', 'icon-tip', null, '3');
INSERT INTO `nav` VALUES ('5', '购买列表', 'open', 'icon-add-new', null, '3');
INSERT INTO `nav` VALUES ('6', '维修列表', 'open', 'icon-no', null, '3');
INSERT INTO `nav` VALUES ('7', '用户列表', 'open', 'icon-group', '/nav/userList.action', '2');
INSERT INTO `nav` VALUES ('8', '设备列表', 'open', 'icon-tip', null, '3');
INSERT INTO `nav` VALUES ('9', '菜单管理', 'open', null, '/nav/navList.action', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=5474 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'silence', '192298A586875DD11F885B0313C968D5', '2015-11-25 12:02:15', 'haha', '');
INSERT INTO `user` VALUES ('3', '樱桃小丸子', '9B06004EFDFD28FEF9AFFB74590F90DA', '2015-11-04 12:02:27', '3ggdfgty', '');
INSERT INTO `user` VALUES ('4', '娜美', '9E860FDE222AC57E58085D435A4FC8CE', '2015-11-08 12:02:31', '4y5ertg', '');
INSERT INTO `user` VALUES ('5', '海贼王', '26740811451162ADD67C5EB5DEEB6205', '2015-11-14 12:02:35', '5tytyrthg', '');
INSERT INTO `user` VALUES ('6', '黑骑一户', 'BFB78C8B3290DAB59B2A941274DD1D1A', '2015-11-03 12:02:38', '6hgfhfgh', '');
INSERT INTO `user` VALUES ('7', '杨过', '5534C9694CB980ADD51B344919B1385F', '2015-11-28 12:02:43', '7gfhfhgji', '');
INSERT INTO `user` VALUES ('8', '小龙女', '8EABBB7130468143FFA55A5E818A4198', '2015-11-16 12:02:47', '8ui6645', '');
INSERT INTO `user` VALUES ('9', '孙悟空', '488942F1ABEBA6C0B502C9C4095328B3', '2015-11-21 12:02:51', '9ty5688u', '');
INSERT INTO `user` VALUES ('10', '白骨精', '45D36B4F50ABA91F1428D4E90A125EA4', '2015-11-26 14:03:20', 'fdf', '');
INSERT INTO `user` VALUES ('5438', '什么', '98BA3E5C98819AE7B0003D3A2B7A711E', '2015-11-26 16:00:29', '32', '');
INSERT INTO `user` VALUES ('5439', '你猜', 'A9B36C24DDBD5D5AB1A95017D67357E3', '2015-11-26 16:06:25', '324', '');
INSERT INTO `user` VALUES ('5440', '操控', '97FED7596BEEB574DD3D51A886B34D85', '2015-11-26 16:11:01', '342', '');
INSERT INTO `user` VALUES ('5441', '发撒扥', 'E2FC8E5B4434E89E0DD67C9E33D6E867', '2015-11-26 16:15:02', '45', '');
INSERT INTO `user` VALUES ('5442', '团', '85A8E7C45D27FDCD098D9C787F0B7EFA', '2015-11-26 16:15:53', '6546', '');
INSERT INTO `user` VALUES ('5444', '海明威', '166DB80AD5F3CE23C535C012A1630AAC', '2015-11-26 16:17:54', 'wrtt', '');
INSERT INTO `user` VALUES ('5446', '朱朱', '2763259BA2EA8EF8BBD1DE64BF20CBE5', '2015-11-26 16:21:24', 'zhuxiang', '');
INSERT INTO `user` VALUES ('5447', '达人', '501AFED8188BB3D8F6DEA35B6668C840', '2015-11-26 16:24:47', 'dar', '');
INSERT INTO `user` VALUES ('5448', '什么鬼', '6181D181260E73E40DF5FB1431319896', '2015-11-26 16:28:07', 'nicl', '');
INSERT INTO `user` VALUES ('5450', '不是吧', '21CEA3B77F80AE940402DDBCB1B7F1E6', '2015-11-26 16:34:26', 'buui', '');
INSERT INTO `user` VALUES ('5451', '搞什么', '9AAFADCAE550ECC849AF70A45CEB5D8D', '2015-11-26 16:38:30', 'gao', '');
INSERT INTO `user` VALUES ('5452', '你妹', '11463C60AAC2339D288FB70C5FDC4189', '2015-11-26 16:41:15', 'miaf', '');
INSERT INTO `user` VALUES ('5453', '去死吧', '498B224E50698F1BB6B23785829145A9', '2015-11-26 16:46:09', 'wudf', '');
INSERT INTO `user` VALUES ('5455', '趋势', '4681C82B78B77CE5E644E108947EE0E5', '2015-11-26 16:54:32', 'quui', '');
INSERT INTO `user` VALUES ('5456', 'ad发', '25D72081E19288939ACEC09A51E1D7D1', '2015-11-26 16:59:14', 'affectl', '');
INSERT INTO `user` VALUES ('5457', '团费', '2646BA4432BD4292B2FBC32ED83FF661', '2015-11-26 17:00:26', 'gdfglr', '');
INSERT INTO `user` VALUES ('5458', '32', '5178C5A56C8A80C1D1E5F91DF0307619', '2015-11-27 10:55:33', '3289', '');
INSERT INTO `user` VALUES ('5463', '朱翔', 'EA48576F30BE1669971699C09AD05C94', '2015-11-27 12:37:20', '123456', '');
INSERT INTO `user` VALUES ('5464', '小明', 'BC9B5718AFDFFE85FB13555347969FF5', '2015-11-27 13:46:45', 'abcd', '');
INSERT INTO `user` VALUES ('5465', '小红', 'E4E92E32BB577F9BC6D74896552E0738', '2015-11-27 13:47:39', 'bc', '');
INSERT INTO `user` VALUES ('5466', '小小兵', '05C5E294697484E49ED9EDA5F756E793', '2015-11-27 14:13:21', '545', '');
INSERT INTO `user` VALUES ('5468', '刘亦菲', 'E781299803014BFE9C4AFF7005397335', '2015-11-27 14:27:01', '84547676', '');
INSERT INTO `user` VALUES ('5470', '大雄', '2982B73118CF042CC93271C67F99C4EF', '2015-11-27 14:39:17', '342', '');
INSERT INTO `user` VALUES ('5471', '静香', '34C0200ECD6BFC8505B54CEEADF448B8', '2015-11-27 15:01:16', '654321', '');
INSERT INTO `user` VALUES ('5472', '唐僧1', 'A1B515B2A38E466F7D91114CBE0D7470', '2015-11-29 14:42:44', '165', '');
INSERT INTO `user` VALUES ('5473', '乌索普', '0468992A159DE3D177C2B9BB9EB3D975', '2015-11-29 17:27:32', '1438', '');
