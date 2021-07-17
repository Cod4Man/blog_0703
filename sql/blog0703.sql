/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-wz9xv7k830nh47u0sxo.mysql.rds.aliyuncs.com:3306
 Source Schema         : blog0703

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/07/2021 23:09:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `useabled` tinyint(1) NOT NULL,
  `auth_menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_menu_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`auth_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, 1, '查看用户', 'GET:/api/user/**', '2021-07-17 17:49:43', '2021-07-17 17:49:43');
INSERT INTO `authority` VALUES (2, 1, '编辑用户', 'PUT:/api/user/**', '2021-07-17 17:50:06', '2021-07-17 17:50:06');
INSERT INTO `authority` VALUES (3, 1, '新增用户', 'POST:/api/user/**', '2021-07-17 17:50:26', '2021-07-17 17:50:26');
INSERT INTO `authority` VALUES (4, 1, '删除用户', 'DELETE:/api/user/**', '2021-07-17 17:50:37', '2021-07-17 17:50:37');

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `front_cover_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `viewer_count` int(11) NOT NULL DEFAULT 0,
  `like_count` int(11) NOT NULL DEFAULT 0,
  `user_id` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_like
-- ----------------------------
DROP TABLE IF EXISTS `blog_like`;
CREATE TABLE `blog_like`  (
  `bl_id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`bl_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) NOT NULL,
  `type_code_key` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fans_follow
-- ----------------------------
DROP TABLE IF EXISTS `fans_follow`;
CREATE TABLE `fans_follow`  (
  `ff_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `fans_user_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ff_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `fans_user_id`(`fans_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user` int(11) NOT NULL,
  `to_user` int(11) NOT NULL,
  `msg_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `msg_text` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`msg_id`) USING BTREE,
  INDEX `from_user`(`from_user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '123456', 'all', 'password,refresh_token', '', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `useabled` tinyint(1) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_cd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 1, '2021-07-17 10:47:22', '2021-07-17 10:47:22', 'ROOT');
INSERT INTO `role` VALUES (2, '系统管理员', 1, '2021-07-17 10:47:46', '2021-07-17 10:47:46', 'ADMIN');
INSERT INTO `role` VALUES (3, '游客', 1, '2021-07-17 10:48:03', '2021-07-17 10:48:03', 'GUEST');

-- ----------------------------
-- Table structure for role_auth_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_auth_relation`;
CREATE TABLE `role_auth_relation`  (
  `ra_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `auth_id` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ra_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_auth_relation
-- ----------------------------
INSERT INTO `role_auth_relation` VALUES (1, 1, 1, '2021-07-17 17:51:00', '2021-07-17 17:51:00');
INSERT INTO `role_auth_relation` VALUES (3, 2, 3, '2021-07-17 17:51:30', '2021-07-17 17:51:30');
INSERT INTO `role_auth_relation` VALUES (4, 2, 1, '2021-07-17 17:51:58', '2021-07-17 17:51:58');
INSERT INTO `role_auth_relation` VALUES (5, 3, 2, '2021-07-17 17:52:03', '2021-07-17 17:52:03');
INSERT INTO `role_auth_relation` VALUES (6, 2, 4, '2021-07-17 17:52:12', '2021-07-17 17:52:12');
INSERT INTO `role_auth_relation` VALUES (7, 2, 2, '2021-07-17 18:41:40', '2021-07-17 18:41:40');

-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client`  (
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
INSERT INTO `sys_oauth_client` VALUES ('client', NULL, '123456', 'all', 'authorization_code,password,refresh_token,implicit', NULL, NULL, 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` VALUES ('youlai-admin', '', '123456', 'all', 'password,client_credentials,refresh_token,authorization_code', '', '', 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` VALUES ('youlai-weapp', '', '123456', 'all', 'authorization_code,password,refresh_token,implicit', NULL, NULL, 3600, 7200, NULL, 'true');

-- ----------------------------
-- Table structure for system_common_param
-- ----------------------------
DROP TABLE IF EXISTS `system_common_param`;
CREATE TABLE `system_common_param`  (
  `code_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code_key` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `useabled` tinyint(1) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `useabled` tinyint(1) NOT NULL DEFAULT 1,
  `phone_num` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL DEFAULT 0,
  `avatar_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `phone_num`(`phone_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '管理员', '$2a$10$yJSqqr6sTxNuYtA6EKcVUe2I4USFCzJ29sNcRrBvtAkSYcNg5ydQ6', 1, '11111111112', 0, 'picture.baidu.com/123.jpg', '2021-07-05 10:32:28', '2021-07-05 10:32:28');
INSERT INTO `user` VALUES (2, 'xiaozhang', '张酱', '$2a$10$yJSqqr6sTxNuYtA6EKcVUe2I4USFCzJ29sNcRrBvtAkSYcNg5ydQ6', 1, '11111111111', 0, 'picture.baidu.com/123.jpg', '2021-07-05 10:32:28', '2021-07-05 10:32:28');

-- ----------------------------
-- Table structure for user_event
-- ----------------------------
DROP TABLE IF EXISTS `user_event`;
CREATE TABLE `user_event`  (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_ip` int(10) NOT NULL,
  `user_action` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `user_ip`(`user_ip`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`  (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ur_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (1, 1, 1, '2021-07-17 10:50:58', '2021-07-17 10:50:58');
INSERT INTO `user_role_relation` VALUES (2, 1, 2, '2021-07-17 10:51:03', '2021-07-17 10:51:03');
INSERT INTO `user_role_relation` VALUES (3, 2, 3, '2021-07-17 10:51:09', '2021-07-17 10:51:09');

SET FOREIGN_KEY_CHECKS = 1;
