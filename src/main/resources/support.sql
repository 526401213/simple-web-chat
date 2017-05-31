
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `recordId` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `content` text,
  `createDate` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(50) DEFAULT NULL COMMENT '记录状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for chat_user
-- ----------------------------
DROP TABLE IF EXISTS `chat_user`;
CREATE TABLE `chat_user` (
  `userId` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `personality` varchar(255) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `lastLogin` timestamp NULL DEFAULT NULL,
  `loginCount` int(3) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(50) DEFAULT NULL COMMENT '记录状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`userId`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
