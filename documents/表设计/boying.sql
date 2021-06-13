/*
 Navicat MySQL Data Transfer

 Source Server         : 47.103.203.188
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 47.103.203.188:3306
 Source Schema         : boying

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 09/06/2021 19:31:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_category
-- ----------------------------
DROP TABLE IF EXISTS `admin_category`;
CREATE TABLE `admin_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '资源目录显示优先级',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '资源目录状态，启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台资源分类表，在细粒度进行权限控制时，可能资源会比较多，所以设计了个资源分类的概念，便于给角色分配资源。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_category
-- ----------------------------
INSERT INTO `admin_category` VALUES (2, '2021-05-08 08:22:38', '用户管理', 3, 1);
INSERT INTO `admin_category` VALUES (3, '2021-05-08 08:22:38', '目录管理', 3, 1);
INSERT INTO `admin_category` VALUES (4, '2021-05-08 08:22:38', '演出管理', 3, 1);
INSERT INTO `admin_category` VALUES (5, '2021-05-08 08:22:38', '订单管理', 3, 1);
INSERT INTO `admin_category` VALUES (6, '2021-05-08 08:22:38', '活动管理', 3, 1);
INSERT INTO `admin_category` VALUES (7, '2021-05-08 08:22:38', '权限管理', 3, 1);

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '菜单名称',
  `level` int(11) NOT NULL DEFAULT 1 COMMENT '菜单级数',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '菜单优先级',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '前端名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '前端图标',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '菜单启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台菜单表，用于控制后台用户可以访问的菜单，支持隐藏、排序和更改名称、图标。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, 0, '2021-05-08 08:26:03', '权限管理', 0, 3, 'ums', 'ums', 1);
INSERT INTO `admin_menu` VALUES (2, 1, '2021-05-08 08:26:03', '账号列表', 1, 2, 'admin', 'ums-admin', 1);
INSERT INTO `admin_menu` VALUES (3, 1, '2021-05-08 08:26:03', '角色列表', 1, 2, 'role', 'ums-role', 1);
INSERT INTO `admin_menu` VALUES (4, 1, '2021-05-08 08:26:03', '菜单列表', 1, 2, 'menu', 'ums-menu', 1);
INSERT INTO `admin_menu` VALUES (5, 1, '2021-05-08 08:26:03', '资源列表', 1, 2, 'resource', 'ums-resource', 1);
INSERT INTO `admin_menu` VALUES (6, 0, '2021-05-08 08:26:03', '活动管理', 0, 1, 'promo', 'pms', 1);
INSERT INTO `admin_menu` VALUES (7, 6, '2021-05-08 08:26:03', '活动列表', 1, 2, 'promoList', 'pms-list', 1);
INSERT INTO `admin_menu` VALUES (8, 6, '2021-05-08 08:26:03', '添加活动', 1, 2, 'addPromo', 'addPromo', 1);
INSERT INTO `admin_menu` VALUES (22, 0, '2021-05-18 08:06:30', '演出管理', 0, 2, 'show', 'show', 1);
INSERT INTO `admin_menu` VALUES (24, 22, '2021-05-18 08:10:05', '目录管理', 1, 2, 'category', 'category', 1);
INSERT INTO `admin_menu` VALUES (25, 22, '2021-05-18 08:11:53', '演出列表', 1, 2, 'showList', 'showList', 1);
INSERT INTO `admin_menu` VALUES (26, 22, '2021-05-18 08:13:22', '添加演出', 1, 3, 'addShow', 'addShow', 1);
INSERT INTO `admin_menu` VALUES (27, 0, '2021-05-18 08:13:46', '订单管理', 0, 2, 'order', 'order', 1);
INSERT INTO `admin_menu` VALUES (28, 27, '2021-05-18 08:14:10', '订单列表', 1, 2, 'orderList', 'orderList', 1);
INSERT INTO `admin_menu` VALUES (29, 1, '2021-05-18 15:32:58', '资源目录', 1, 2, 'resourceCategory', 'resource-category', 1);
INSERT INTO `admin_menu` VALUES (31, 0, '2021-05-19 09:34:49', '用户管理', 0, 1, 'user', 'user', 1);
INSERT INTO `admin_menu` VALUES (32, 31, '2021-05-19 10:18:38', '用户列表', 1, 0, 'User', 'User', 1);
INSERT INTO `admin_menu` VALUES (33, 27, '2021-06-02 09:18:34', '订单历史', 1, 2, 'history', 'history', 1);

-- ----------------------------
-- Table structure for admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL DEFAULT 0 COMMENT '对应资源目录ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '资源创建时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '资源URL',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '资源描述',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '资源启用状态：0->禁用；1->启用',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '资源显示优先级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台资源表，用于控制后台用户可以访问的接口，使用了Ant路径的匹配规则，可以使用通配符定义一系列接口的权限。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_resource
-- ----------------------------
INSERT INTO `admin_resource` VALUES (1, 7, '2021-05-08 08:25:16', '后台用户管理', '/AdminUser/**', '对后台用户进行管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (2, 7, '2021-05-08 08:25:16', '后台菜单管理', '/AdminMenu/**', '对后台菜单进行管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (3, 7, '2021-05-08 08:25:16', '后台资源目录管理', '/AdminCategory/**', '对后台资源目录进行管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (4, 7, '2021-05-08 08:25:16', '后台资源管理', '/AdminResource/**', '对后台资源进行管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (5, 7, '2021-05-08 08:25:16', '后台角色管理', '/AdminRole/**', '对后台角色进行管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (9, 3, '2021-05-19 00:29:49', '前台菜单管理', '/category/**', '对前台菜单管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (10, 5, '2021-05-19 00:49:06', '前台订单管理', '/order/**', '对前台订单管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (11, 4, '2021-05-19 00:49:54', '前台座次管理', '/seat/**', '对前台座次管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (12, 4, '2021-05-19 00:50:11', '前台演出管理', '/show/**', '对前台演出管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (14, 6, '2021-05-19 00:56:12', '前台活动管理', '/promo/**', '对前台活动管理所需资源', 1, 4);
INSERT INTO `admin_resource` VALUES (15, 2, '2021-05-19 00:56:56', '前台用户管理', '/user/**', '对前台用户管理所需资源', 1, 4);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色描述',
  `admin_count` int(11) NOT NULL DEFAULT 0 COMMENT '后台用户数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '角色的显示优先级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '超级管理员', '拥有一切权限', 1, '2021-05-08 08:27:22', 1, 2);
INSERT INTO `admin_role` VALUES (2, '初始管理员', '暂时没有任何权限', 1, '2021-05-08 08:27:22', 1, 2);
INSERT INTO `admin_role` VALUES (10, '演出管理员', '管理演出', 1, '2021-05-18 08:19:38', 1, 2);
INSERT INTO `admin_role` VALUES (11, '订单管理员', '管理订单', 1, '2021-05-18 08:19:58', 1, 2);
INSERT INTO `admin_role` VALUES (13, '活动管理员', '活动管理员', 1, '2021-05-18 09:02:57', 1, 2);
INSERT INTO `admin_role` VALUES (14, '角色管理员', '仅能添加角色', 1, '2021-05-18 16:04:26', 1, 2);

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台角色菜单关系表，多对多关系，可以给一个角色分配多个菜单。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (9, 1, 3);
INSERT INTO `admin_role_menu` VALUES (10, 2, 3);
INSERT INTO `admin_role_menu` VALUES (11, 3, 3);
INSERT INTO `admin_role_menu` VALUES (12, 4, 3);
INSERT INTO `admin_role_menu` VALUES (13, 5, 3);
INSERT INTO `admin_role_menu` VALUES (14, 6, 4);
INSERT INTO `admin_role_menu` VALUES (15, 7, 4);
INSERT INTO `admin_role_menu` VALUES (16, 8, 4);
INSERT INTO `admin_role_menu` VALUES (98, 8, 13);
INSERT INTO `admin_role_menu` VALUES (99, 7, 13);
INSERT INTO `admin_role_menu` VALUES (100, 6, 13);
INSERT INTO `admin_role_menu` VALUES (121, 2, 14);
INSERT INTO `admin_role_menu` VALUES (122, 3, 14);
INSERT INTO `admin_role_menu` VALUES (123, 4, 14);
INSERT INTO `admin_role_menu` VALUES (124, 5, 14);
INSERT INTO `admin_role_menu` VALUES (125, 29, 14);
INSERT INTO `admin_role_menu` VALUES (126, 1, 14);
INSERT INTO `admin_role_menu` VALUES (149, 2, 15);
INSERT INTO `admin_role_menu` VALUES (150, 3, 15);
INSERT INTO `admin_role_menu` VALUES (151, 4, 15);
INSERT INTO `admin_role_menu` VALUES (152, 5, 15);
INSERT INTO `admin_role_menu` VALUES (153, 29, 15);
INSERT INTO `admin_role_menu` VALUES (154, 1, 15);
INSERT INTO `admin_role_menu` VALUES (189, 26, 10);
INSERT INTO `admin_role_menu` VALUES (190, 24, 10);
INSERT INTO `admin_role_menu` VALUES (191, 25, 10);
INSERT INTO `admin_role_menu` VALUES (192, 22, 10);
INSERT INTO `admin_role_menu` VALUES (199, 28, 11);
INSERT INTO `admin_role_menu` VALUES (200, 33, 11);
INSERT INTO `admin_role_menu` VALUES (201, 27, 11);
INSERT INTO `admin_role_menu` VALUES (202, 5, 1);
INSERT INTO `admin_role_menu` VALUES (203, 7, 1);
INSERT INTO `admin_role_menu` VALUES (204, 8, 1);
INSERT INTO `admin_role_menu` VALUES (205, 4, 1);
INSERT INTO `admin_role_menu` VALUES (206, 26, 1);
INSERT INTO `admin_role_menu` VALUES (207, 24, 1);
INSERT INTO `admin_role_menu` VALUES (208, 25, 1);
INSERT INTO `admin_role_menu` VALUES (209, 28, 1);
INSERT INTO `admin_role_menu` VALUES (210, 2, 1);
INSERT INTO `admin_role_menu` VALUES (211, 3, 1);
INSERT INTO `admin_role_menu` VALUES (212, 29, 1);
INSERT INTO `admin_role_menu` VALUES (213, 32, 1);
INSERT INTO `admin_role_menu` VALUES (214, 33, 1);
INSERT INTO `admin_role_menu` VALUES (215, 1, 1);
INSERT INTO `admin_role_menu` VALUES (216, 6, 1);
INSERT INTO `admin_role_menu` VALUES (217, 22, 1);
INSERT INTO `admin_role_menu` VALUES (218, 27, 1);
INSERT INTO `admin_role_menu` VALUES (219, 31, 1);

-- ----------------------------
-- Table structure for admin_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_resource`;
CREATE TABLE `admin_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台角色资源关系表，多对多关系，可以给一个角色分配多个资源。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role_resource
-- ----------------------------
INSERT INTO `admin_role_resource` VALUES (6, 3, 1);
INSERT INTO `admin_role_resource` VALUES (7, 3, 2);
INSERT INTO `admin_role_resource` VALUES (8, 3, 3);
INSERT INTO `admin_role_resource` VALUES (9, 3, 4);
INSERT INTO `admin_role_resource` VALUES (10, 3, 5);
INSERT INTO `admin_role_resource` VALUES (38, 1, 1);
INSERT INTO `admin_role_resource` VALUES (39, 1, 2);
INSERT INTO `admin_role_resource` VALUES (40, 1, 3);
INSERT INTO `admin_role_resource` VALUES (41, 1, 5);
INSERT INTO `admin_role_resource` VALUES (42, 1, 4);
INSERT INTO `admin_role_resource` VALUES (44, 1, 15);
INSERT INTO `admin_role_resource` VALUES (45, 1, 9);
INSERT INTO `admin_role_resource` VALUES (46, 1, 11);
INSERT INTO `admin_role_resource` VALUES (47, 1, 12);
INSERT INTO `admin_role_resource` VALUES (48, 1, 10);
INSERT INTO `admin_role_resource` VALUES (49, 1, 14);
INSERT INTO `admin_role_resource` VALUES (52, 11, 10);
INSERT INTO `admin_role_resource` VALUES (54, 14, 5);
INSERT INTO `admin_role_resource` VALUES (55, 15, 1);
INSERT INTO `admin_role_resource` VALUES (56, 15, 2);
INSERT INTO `admin_role_resource` VALUES (57, 15, 3);
INSERT INTO `admin_role_resource` VALUES (58, 15, 5);
INSERT INTO `admin_role_resource` VALUES (59, 15, 4);
INSERT INTO `admin_role_resource` VALUES (60, 13, 14);
INSERT INTO `admin_role_resource` VALUES (61, 13, 9);
INSERT INTO `admin_role_resource` VALUES (62, 13, 12);
INSERT INTO `admin_role_resource` VALUES (63, 13, 11);
INSERT INTO `admin_role_resource` VALUES (69, 10, 11);
INSERT INTO `admin_role_resource` VALUES (70, 10, 12);
INSERT INTO `admin_role_resource` VALUES (71, 10, 9);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `login_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '管理员显示优先级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'root', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', 'tongji4m3@163.com', '2021-02-14 08:24:12', '2021-06-09 19:25:58', 1, 3);
INSERT INTO `admin_user` VALUES (8, '演出管理员账号', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', 'yanchuguanliyuan@qq.com', '2021-05-18 08:23:02', '2021-05-19 16:46:20', 1, 0);
INSERT INTO `admin_user` VALUES (10, '订单管理员账号', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', 'dingdanguanliyuan@qq.com', '2021-05-18 08:23:45', '2021-05-18 10:39:15', 1, 0);
INSERT INTO `admin_user` VALUES (11, '活动管理员账号', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', 'huodongguanliyuan@qq.com', '2021-05-18 09:03:26', '2021-05-19 09:14:36', 1, 0);
INSERT INTO `admin_user` VALUES (12, '角色管理员账号', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', 'jueseguanliyuan@qq.com', '2021-05-18 16:05:02', '2021-05-19 08:03:20', 1, 0);
INSERT INTO `admin_user` VALUES (13, '初始管理员账号', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', 'chushiguanliyuan@qq.com', '2021-05-19 09:16:37', '2021-05-26 16:32:31', 1, 0);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户和角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (3, 2, 2);
INSERT INTO `admin_user_role` VALUES (4, 3, 1);
INSERT INTO `admin_user_role` VALUES (14, 1, 3);
INSERT INTO `admin_user_role` VALUES (15, 1, 4);
INSERT INTO `admin_user_role` VALUES (16, 1, 1);
INSERT INTO `admin_user_role` VALUES (18, 9, 2);
INSERT INTO `admin_user_role` VALUES (19, 10, 11);
INSERT INTO `admin_user_role` VALUES (20, 11, 13);
INSERT INTO `admin_user_role` VALUES (21, 12, 14);
INSERT INTO `admin_user_role` VALUES (22, 13, 2);
INSERT INTO `admin_user_role` VALUES (23, 8, 10);

-- ----------------------------
-- Table structure for boying_category
-- ----------------------------
DROP TABLE IF EXISTS `boying_category`;
CREATE TABLE `boying_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '目录名称',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '用于排序,0则不显示',
  `admin_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '管理员是否删除了该目录,默认为0，即未删除状态',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '该目录的图标',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '目录描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '目录表,可标识是演唱会,还是相声表演等类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_category
-- ----------------------------
INSERT INTO `boying_category` VALUES (1, '演唱会', 12, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410350000', '演唱会');
INSERT INTO `boying_category` VALUES (2, '话剧歌剧', 11, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410378000', '话剧歌剧');
INSERT INTO `boying_category` VALUES (3, '体育', 10, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410428000', '体育');
INSERT INTO `boying_category` VALUES (4, '儿童亲子', 9, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410452000', '儿童亲子');
INSERT INTO `boying_category` VALUES (5, '展览休闲', 8, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410477000', '展览休闲');
INSERT INTO `boying_category` VALUES (6, '音乐会', 7, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410500000', '音乐会');
INSERT INTO `boying_category` VALUES (7, '曲苑杂坛', 6, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410520000', '曲苑杂坛');
INSERT INTO `boying_category` VALUES (8, '二次元', 5, 0, 'http://tongji-boying.oss-cn-shanghai.aliyuncs.com/undefined/1621410571000', '二次元');

-- ----------------------------
-- Table structure for boying_history
-- ----------------------------
DROP TABLE IF EXISTS `boying_history`;
CREATE TABLE `boying_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属用户Id',
  `show_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属演出Id',
  `seat_id` int(11) NOT NULL COMMENT '对应演出座次Id',
  `promo_id` int(11) NOT NULL DEFAULT 0 COMMENT '若非0，则代表是秒杀商品的价格',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '待观看，已完成，已取消(1，2，3)',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作的时间',
  `user_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户删除',
  `admin_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '管理员删除',
  `ticket_count` int(11) NOT NULL DEFAULT 0 COMMENT '票的总数',
  `ticket_price` double NOT NULL DEFAULT 0 COMMENT '该演出座次的单价',
  `order_price` double(10, 2) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000.00 COMMENT '订单总价格',
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单支付方式（支付宝，微信)',
  `qr_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '二维码图片,供观影人验证入场',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户订单历史(操作的时间需要重新设置）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_history
-- ----------------------------

-- ----------------------------
-- Table structure for boying_order
-- ----------------------------
DROP TABLE IF EXISTS `boying_order`;
CREATE TABLE `boying_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属用户Id',
  `show_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属演出Id',
  `seat_id` int(11) NOT NULL COMMENT '对应演出座次Id',
  `promo_id` int(11) NOT NULL DEFAULT 0 COMMENT '若非0，则代表是秒杀商品的价格',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '待观看，已完成，已取消(1，2，3)',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单支付时间',
  `user_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户删除',
  `admin_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '管理员删除',
  `ticket_count` int(11) NOT NULL DEFAULT 0 COMMENT '票的总数',
  `ticket_price` double NOT NULL DEFAULT 0 COMMENT '该演出座次的单价',
  `order_price` double(10, 2) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000.00 COMMENT '订单总价格',
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单支付方式（支付宝，微信)',
  `qr_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '二维码图片,供观影人验证入场',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_order`(`user_id`, `show_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_order
-- ----------------------------

-- ----------------------------
-- Table structure for boying_promo
-- ----------------------------
DROP TABLE IF EXISTS `boying_promo`;
CREATE TABLE `boying_promo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) NOT NULL DEFAULT 0 COMMENT '促销活动对应的座次',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '活动名称',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动结束时间',
  `price` double NOT NULL DEFAULT 0 COMMENT '秒杀时刻座次的价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '秒杀活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_promo
-- ----------------------------
INSERT INTO `boying_promo` VALUES (3, 7, '限时抢购', '2021-06-14 00:00:00', '2021-06-16 00:00:00', 480);

-- ----------------------------
-- Table structure for boying_seat
-- ----------------------------
DROP TABLE IF EXISTS `boying_seat`;
CREATE TABLE `boying_seat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `show_id` int(11) NOT NULL COMMENT '所属演出Id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所属哪个级别,例如\"A等票“，”B等票“',
  `price` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '该级别座位的定价',
  `capacity` int(11) NOT NULL DEFAULT 0 COMMENT '该级别座位的容量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 276 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '对于某个演唱会的容量信息表.包括每个级别的容量,价格,库存等' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_seat
-- ----------------------------
INSERT INTO `boying_seat` VALUES (1, 1, 'A等座', 580.00, 170);
INSERT INTO `boying_seat` VALUES (2, 1, 'B等座', 430.00, 210);
INSERT INTO `boying_seat` VALUES (3, 1, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (4, 2, 'A等座', 780.00, 120);
INSERT INTO `boying_seat` VALUES (5, 2, 'B等座', 480.00, 120);
INSERT INTO `boying_seat` VALUES (6, 2, 'C等座', 180.00, 140);
INSERT INTO `boying_seat` VALUES (7, 3, 'A等座', 680.00, 150);
INSERT INTO `boying_seat` VALUES (8, 3, 'B等座', 430.00, 140);
INSERT INTO `boying_seat` VALUES (9, 3, 'C等座', 180.00, 180);
INSERT INTO `boying_seat` VALUES (10, 4, 'A等座', 880.00, 160);
INSERT INTO `boying_seat` VALUES (11, 4, 'B等座', 480.00, 200);
INSERT INTO `boying_seat` VALUES (12, 4, 'C等座', 80.00, 160);
INSERT INTO `boying_seat` VALUES (13, 5, 'A等座', 780.00, 140);
INSERT INTO `boying_seat` VALUES (14, 5, 'B等座', 480.00, 160);
INSERT INTO `boying_seat` VALUES (15, 5, 'C等座', 180.00, 150);
INSERT INTO `boying_seat` VALUES (16, 6, 'A等座', 680.00, 190);
INSERT INTO `boying_seat` VALUES (17, 6, 'B等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (18, 6, 'C等座', 80.00, 210);
INSERT INTO `boying_seat` VALUES (19, 7, 'A等座', 880.00, 190);
INSERT INTO `boying_seat` VALUES (20, 7, 'B等座', 580.00, 210);
INSERT INTO `boying_seat` VALUES (21, 7, 'C等座', 280.00, 150);
INSERT INTO `boying_seat` VALUES (22, 8, 'A等座', 880.00, 200);
INSERT INTO `boying_seat` VALUES (23, 8, 'B等座', 580.00, 170);
INSERT INTO `boying_seat` VALUES (24, 8, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (25, 9, 'A等座', 780.00, 140);
INSERT INTO `boying_seat` VALUES (26, 9, 'B等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (27, 9, 'C等座', 380.00, 190);
INSERT INTO `boying_seat` VALUES (28, 10, 'A等座', 580.00, 140);
INSERT INTO `boying_seat` VALUES (29, 10, 'B等座', 330.00, 170);
INSERT INTO `boying_seat` VALUES (30, 10, 'C等座', 80.00, 160);
INSERT INTO `boying_seat` VALUES (31, 11, 'A等座', 780.00, 160);
INSERT INTO `boying_seat` VALUES (32, 11, 'B等座', 480.00, 160);
INSERT INTO `boying_seat` VALUES (33, 11, 'C等座', 180.00, 160);
INSERT INTO `boying_seat` VALUES (34, 12, 'A等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (35, 12, 'B等座', 330.00, 170);
INSERT INTO `boying_seat` VALUES (36, 12, 'C等座', 80.00, 200);
INSERT INTO `boying_seat` VALUES (37, 13, 'A等座', 880.00, 180);
INSERT INTO `boying_seat` VALUES (38, 13, 'B等座', 530.00, 140);
INSERT INTO `boying_seat` VALUES (39, 13, 'C等座', 180.00, 160);
INSERT INTO `boying_seat` VALUES (40, 14, 'A等座', 680.00, 200);
INSERT INTO `boying_seat` VALUES (41, 14, 'B等座', 430.00, 170);
INSERT INTO `boying_seat` VALUES (42, 14, 'C等座', 180.00, 160);
INSERT INTO `boying_seat` VALUES (43, 15, 'A等座', 680.00, 200);
INSERT INTO `boying_seat` VALUES (44, 15, 'B等座', 380.00, 180);
INSERT INTO `boying_seat` VALUES (45, 15, 'C等座', 80.00, 170);
INSERT INTO `boying_seat` VALUES (46, 16, 'A等座', 680.00, 170);
INSERT INTO `boying_seat` VALUES (47, 16, 'B等座', 430.00, 150);
INSERT INTO `boying_seat` VALUES (48, 16, 'C等座', 180.00, 160);
INSERT INTO `boying_seat` VALUES (49, 17, 'A等座', 780.00, 200);
INSERT INTO `boying_seat` VALUES (50, 17, 'B等座', 580.00, 190);
INSERT INTO `boying_seat` VALUES (51, 17, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (52, 18, 'A等座', 880.00, 150);
INSERT INTO `boying_seat` VALUES (53, 18, 'B等座', 630.00, 130);
INSERT INTO `boying_seat` VALUES (54, 18, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (55, 19, 'A等座', 580.00, 170);
INSERT INTO `boying_seat` VALUES (56, 19, 'B等座', 330.00, 160);
INSERT INTO `boying_seat` VALUES (57, 19, 'C等座', 80.00, 120);
INSERT INTO `boying_seat` VALUES (58, 20, 'A等座', 780.00, 230);
INSERT INTO `boying_seat` VALUES (59, 20, 'B等座', 530.00, 180);
INSERT INTO `boying_seat` VALUES (60, 20, 'C等座', 280.00, 210);
INSERT INTO `boying_seat` VALUES (61, 21, 'A等座', 680.00, 160);
INSERT INTO `boying_seat` VALUES (62, 21, 'B等座', 430.00, 130);
INSERT INTO `boying_seat` VALUES (63, 21, 'C等座', 180.00, 150);
INSERT INTO `boying_seat` VALUES (64, 22, 'A等座', 580.00, 180);
INSERT INTO `boying_seat` VALUES (65, 22, 'B等座', 430.00, 160);
INSERT INTO `boying_seat` VALUES (66, 22, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (67, 23, 'A等座', 880.00, 140);
INSERT INTO `boying_seat` VALUES (68, 23, 'B等座', 580.00, 150);
INSERT INTO `boying_seat` VALUES (69, 23, 'C等座', 280.00, 160);
INSERT INTO `boying_seat` VALUES (70, 24, 'A等座', 680.00, 160);
INSERT INTO `boying_seat` VALUES (71, 24, 'B等座', 380.00, 180);
INSERT INTO `boying_seat` VALUES (72, 24, 'C等座', 80.00, 150);
INSERT INTO `boying_seat` VALUES (73, 25, 'A等座', 780.00, 130);
INSERT INTO `boying_seat` VALUES (74, 25, 'B等座', 580.00, 140);
INSERT INTO `boying_seat` VALUES (75, 25, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (76, 26, 'A等座', 580.00, 120);
INSERT INTO `boying_seat` VALUES (77, 26, 'B等座', 480.00, 150);
INSERT INTO `boying_seat` VALUES (78, 26, 'C等座', 380.00, 150);
INSERT INTO `boying_seat` VALUES (79, 27, 'A等座', 880.00, 180);
INSERT INTO `boying_seat` VALUES (80, 27, 'B等座', 630.00, 160);
INSERT INTO `boying_seat` VALUES (81, 27, 'C等座', 380.00, 180);
INSERT INTO `boying_seat` VALUES (82, 28, 'A等座', 680.00, 160);
INSERT INTO `boying_seat` VALUES (83, 28, 'B等座', 480.00, 190);
INSERT INTO `boying_seat` VALUES (84, 28, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (85, 29, 'A等座', 880.00, 190);
INSERT INTO `boying_seat` VALUES (86, 29, 'B等座', 530.00, 160);
INSERT INTO `boying_seat` VALUES (87, 29, 'C等座', 180.00, 170);
INSERT INTO `boying_seat` VALUES (88, 30, 'A等座', 680.00, 160);
INSERT INTO `boying_seat` VALUES (89, 30, 'B等座', 530.00, 200);
INSERT INTO `boying_seat` VALUES (90, 30, 'C等座', 380.00, 160);
INSERT INTO `boying_seat` VALUES (91, 31, 'A等座', 680.00, 190);
INSERT INTO `boying_seat` VALUES (92, 31, 'B等座', 480.00, 160);
INSERT INTO `boying_seat` VALUES (93, 31, 'C等座', 280.00, 130);
INSERT INTO `boying_seat` VALUES (94, 32, 'A等座', 680.00, 190);
INSERT INTO `boying_seat` VALUES (95, 32, 'B等座', 380.00, 200);
INSERT INTO `boying_seat` VALUES (96, 32, 'C等座', 80.00, 180);
INSERT INTO `boying_seat` VALUES (97, 33, 'A等座', 780.00, 160);
INSERT INTO `boying_seat` VALUES (98, 33, 'B等座', 530.00, 190);
INSERT INTO `boying_seat` VALUES (99, 33, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (100, 34, 'A等座', 780.00, 170);
INSERT INTO `boying_seat` VALUES (101, 34, 'B等座', 480.00, 170);
INSERT INTO `boying_seat` VALUES (102, 34, 'C等座', 180.00, 170);
INSERT INTO `boying_seat` VALUES (103, 35, 'A等座', 880.00, 170);
INSERT INTO `boying_seat` VALUES (104, 35, 'B等座', 630.00, 140);
INSERT INTO `boying_seat` VALUES (105, 35, 'C等座', 380.00, 160);
INSERT INTO `boying_seat` VALUES (106, 36, 'A等座', 580.00, 150);
INSERT INTO `boying_seat` VALUES (107, 36, 'B等座', 430.00, 160);
INSERT INTO `boying_seat` VALUES (108, 36, 'C等座', 280.00, 150);
INSERT INTO `boying_seat` VALUES (109, 37, 'A等座', 680.00, 170);
INSERT INTO `boying_seat` VALUES (110, 37, 'B等座', 430.00, 160);
INSERT INTO `boying_seat` VALUES (111, 37, 'C等座', 180.00, 150);
INSERT INTO `boying_seat` VALUES (112, 38, 'A等座', 580.00, 200);
INSERT INTO `boying_seat` VALUES (113, 38, 'B等座', 330.00, 160);
INSERT INTO `boying_seat` VALUES (114, 38, 'C等座', 80.00, 180);
INSERT INTO `boying_seat` VALUES (115, 39, 'A等座', 580.00, 180);
INSERT INTO `boying_seat` VALUES (116, 39, 'B等座', 330.00, 160);
INSERT INTO `boying_seat` VALUES (117, 39, 'C等座', 80.00, 190);
INSERT INTO `boying_seat` VALUES (118, 40, 'A等座', 580.00, 190);
INSERT INTO `boying_seat` VALUES (119, 40, 'B等座', 480.00, 170);
INSERT INTO `boying_seat` VALUES (120, 40, 'C等座', 380.00, 160);
INSERT INTO `boying_seat` VALUES (121, 41, 'A等座', 580.00, 190);
INSERT INTO `boying_seat` VALUES (122, 41, 'B等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (123, 41, 'C等座', 180.00, 200);
INSERT INTO `boying_seat` VALUES (124, 42, 'A等座', 880.00, 130);
INSERT INTO `boying_seat` VALUES (125, 42, 'B等座', 630.00, 160);
INSERT INTO `boying_seat` VALUES (126, 42, 'C等座', 380.00, 140);
INSERT INTO `boying_seat` VALUES (127, 43, 'A等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (128, 43, 'B等座', 380.00, 160);
INSERT INTO `boying_seat` VALUES (129, 43, 'C等座', 180.00, 180);
INSERT INTO `boying_seat` VALUES (130, 44, 'A等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (131, 44, 'B等座', 330.00, 210);
INSERT INTO `boying_seat` VALUES (132, 44, 'C等座', 80.00, 170);
INSERT INTO `boying_seat` VALUES (133, 45, 'A等座', 580.00, 170);
INSERT INTO `boying_seat` VALUES (134, 45, 'B等座', 380.00, 190);
INSERT INTO `boying_seat` VALUES (135, 45, 'C等座', 180.00, 180);
INSERT INTO `boying_seat` VALUES (136, 46, 'A等座', 780.00, 190);
INSERT INTO `boying_seat` VALUES (137, 46, 'B等座', 430.00, 210);
INSERT INTO `boying_seat` VALUES (138, 46, 'C等座', 80.00, 180);
INSERT INTO `boying_seat` VALUES (139, 47, 'A等座', 880.00, 170);
INSERT INTO `boying_seat` VALUES (140, 47, 'B等座', 480.00, 130);
INSERT INTO `boying_seat` VALUES (141, 47, 'C等座', 80.00, 150);
INSERT INTO `boying_seat` VALUES (142, 48, 'A等座', 680.00, 170);
INSERT INTO `boying_seat` VALUES (143, 48, 'B等座', 380.00, 210);
INSERT INTO `boying_seat` VALUES (144, 48, 'C等座', 80.00, 190);
INSERT INTO `boying_seat` VALUES (145, 49, 'A等座', 680.00, 200);
INSERT INTO `boying_seat` VALUES (146, 49, 'B等座', 480.00, 160);
INSERT INTO `boying_seat` VALUES (147, 49, 'C等座', 280.00, 210);
INSERT INTO `boying_seat` VALUES (148, 50, 'A等座', 880.00, 160);
INSERT INTO `boying_seat` VALUES (149, 50, 'B等座', 530.00, 200);
INSERT INTO `boying_seat` VALUES (150, 50, 'C等座', 180.00, 170);
INSERT INTO `boying_seat` VALUES (151, 51, 'A等座', 880.00, 190);
INSERT INTO `boying_seat` VALUES (152, 51, 'B等座', 630.00, 180);
INSERT INTO `boying_seat` VALUES (153, 51, 'C等座', 380.00, 210);
INSERT INTO `boying_seat` VALUES (154, 52, 'A等座', 880.00, 190);
INSERT INTO `boying_seat` VALUES (155, 52, 'B等座', 530.00, 190);
INSERT INTO `boying_seat` VALUES (156, 52, 'C等座', 180.00, 170);
INSERT INTO `boying_seat` VALUES (157, 53, 'A等座', 780.00, 200);
INSERT INTO `boying_seat` VALUES (158, 53, 'B等座', 430.00, 180);
INSERT INTO `boying_seat` VALUES (159, 53, 'C等座', 80.00, 170);
INSERT INTO `boying_seat` VALUES (160, 54, 'A等座', 780.00, 190);
INSERT INTO `boying_seat` VALUES (161, 54, 'B等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (162, 54, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (163, 55, 'A等座', 580.00, 190);
INSERT INTO `boying_seat` VALUES (164, 55, 'B等座', 480.00, 170);
INSERT INTO `boying_seat` VALUES (165, 55, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (166, 56, 'A等座', 880.00, 140);
INSERT INTO `boying_seat` VALUES (167, 56, 'B等座', 480.00, 170);
INSERT INTO `boying_seat` VALUES (168, 56, 'C等座', 80.00, 140);
INSERT INTO `boying_seat` VALUES (169, 57, 'A等座', 780.00, 150);
INSERT INTO `boying_seat` VALUES (170, 57, 'B等座', 580.00, 200);
INSERT INTO `boying_seat` VALUES (171, 57, 'C等座', 380.00, 180);
INSERT INTO `boying_seat` VALUES (172, 58, 'A等座', 680.00, 190);
INSERT INTO `boying_seat` VALUES (173, 58, 'B等座', 480.00, 170);
INSERT INTO `boying_seat` VALUES (174, 58, 'C等座', 280.00, 150);
INSERT INTO `boying_seat` VALUES (175, 59, 'A等座', 880.00, 180);
INSERT INTO `boying_seat` VALUES (176, 59, 'B等座', 630.00, 130);
INSERT INTO `boying_seat` VALUES (177, 59, 'C等座', 380.00, 180);
INSERT INTO `boying_seat` VALUES (178, 60, 'A等座', 880.00, 170);
INSERT INTO `boying_seat` VALUES (179, 60, 'B等座', 530.00, 160);
INSERT INTO `boying_seat` VALUES (180, 60, 'C等座', 180.00, 170);
INSERT INTO `boying_seat` VALUES (181, 61, 'A等座', 780.00, 140);
INSERT INTO `boying_seat` VALUES (182, 61, 'B等座', 530.00, 150);
INSERT INTO `boying_seat` VALUES (183, 61, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (184, 62, 'A等座', 880.00, 180);
INSERT INTO `boying_seat` VALUES (185, 62, 'B等座', 480.00, 170);
INSERT INTO `boying_seat` VALUES (186, 62, 'C等座', 80.00, 180);
INSERT INTO `boying_seat` VALUES (187, 63, 'A等座', 580.00, 120);
INSERT INTO `boying_seat` VALUES (188, 63, 'B等座', 430.00, 140);
INSERT INTO `boying_seat` VALUES (189, 63, 'C等座', 280.00, 180);
INSERT INTO `boying_seat` VALUES (190, 64, 'A等座', 780.00, 170);
INSERT INTO `boying_seat` VALUES (191, 64, 'B等座', 430.00, 190);
INSERT INTO `boying_seat` VALUES (192, 64, 'C等座', 80.00, 180);
INSERT INTO `boying_seat` VALUES (193, 65, 'A等座', 580.00, 200);
INSERT INTO `boying_seat` VALUES (194, 65, 'B等座', 430.00, 190);
INSERT INTO `boying_seat` VALUES (195, 65, 'C等座', 280.00, 190);
INSERT INTO `boying_seat` VALUES (196, 66, 'A等座', 580.00, 200);
INSERT INTO `boying_seat` VALUES (197, 66, 'B等座', 330.00, 190);
INSERT INTO `boying_seat` VALUES (198, 66, 'C等座', 80.00, 160);
INSERT INTO `boying_seat` VALUES (199, 67, 'A等座', 880.00, 160);
INSERT INTO `boying_seat` VALUES (200, 67, 'B等座', 630.00, 230);
INSERT INTO `boying_seat` VALUES (201, 67, 'C等座', 380.00, 210);
INSERT INTO `boying_seat` VALUES (202, 68, 'A等座', 580.00, 150);
INSERT INTO `boying_seat` VALUES (203, 68, 'B等座', 380.00, 160);
INSERT INTO `boying_seat` VALUES (204, 68, 'C等座', 180.00, 130);
INSERT INTO `boying_seat` VALUES (205, 69, 'A等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (206, 69, 'B等座', 330.00, 150);
INSERT INTO `boying_seat` VALUES (207, 69, 'C等座', 80.00, 160);
INSERT INTO `boying_seat` VALUES (208, 70, 'A等座', 680.00, 200);
INSERT INTO `boying_seat` VALUES (209, 70, 'B等座', 430.00, 180);
INSERT INTO `boying_seat` VALUES (210, 70, 'C等座', 180.00, 210);
INSERT INTO `boying_seat` VALUES (211, 71, 'A等座', 780.00, 170);
INSERT INTO `boying_seat` VALUES (212, 71, 'B等座', 430.00, 180);
INSERT INTO `boying_seat` VALUES (213, 71, 'C等座', 80.00, 150);
INSERT INTO `boying_seat` VALUES (214, 72, 'A等座', 880.00, 210);
INSERT INTO `boying_seat` VALUES (215, 72, 'B等座', 530.00, 210);
INSERT INTO `boying_seat` VALUES (216, 72, 'C等座', 180.00, 200);
INSERT INTO `boying_seat` VALUES (217, 73, 'A等座', 580.00, 210);
INSERT INTO `boying_seat` VALUES (218, 73, 'B等座', 430.00, 190);
INSERT INTO `boying_seat` VALUES (219, 73, 'C等座', 280.00, 220);
INSERT INTO `boying_seat` VALUES (220, 74, 'A等座', 780.00, 130);
INSERT INTO `boying_seat` VALUES (221, 74, 'B等座', 530.00, 150);
INSERT INTO `boying_seat` VALUES (222, 74, 'C等座', 280.00, 140);
INSERT INTO `boying_seat` VALUES (223, 75, 'A等座', 880.00, 190);
INSERT INTO `boying_seat` VALUES (224, 75, 'B等座', 530.00, 190);
INSERT INTO `boying_seat` VALUES (225, 75, 'C等座', 180.00, 190);
INSERT INTO `boying_seat` VALUES (226, 76, 'A等座', 780.00, 150);
INSERT INTO `boying_seat` VALUES (227, 76, 'B等座', 480.00, 140);
INSERT INTO `boying_seat` VALUES (228, 76, 'C等座', 180.00, 140);
INSERT INTO `boying_seat` VALUES (229, 77, 'A等座', 780.00, 120);
INSERT INTO `boying_seat` VALUES (230, 77, 'B等座', 430.00, 150);
INSERT INTO `boying_seat` VALUES (231, 77, 'C等座', 80.00, 160);
INSERT INTO `boying_seat` VALUES (232, 78, 'A等座', 580.00, 180);
INSERT INTO `boying_seat` VALUES (233, 78, 'B等座', 480.00, 180);
INSERT INTO `boying_seat` VALUES (234, 78, 'C等座', 380.00, 140);
INSERT INTO `boying_seat` VALUES (235, 79, 'A等座', 880.00, 160);
INSERT INTO `boying_seat` VALUES (236, 79, 'B等座', 480.00, 160);
INSERT INTO `boying_seat` VALUES (237, 79, 'C等座', 80.00, 180);
INSERT INTO `boying_seat` VALUES (238, 80, 'A等座', 780.00, 140);
INSERT INTO `boying_seat` VALUES (239, 80, 'B等座', 480.00, 160);
INSERT INTO `boying_seat` VALUES (240, 80, 'C等座', 180.00, 140);
INSERT INTO `boying_seat` VALUES (241, 81, 'A等座', 880.00, 140);
INSERT INTO `boying_seat` VALUES (242, 81, 'B等座', 480.00, 120);
INSERT INTO `boying_seat` VALUES (243, 81, 'C等座', 80.00, 120);
INSERT INTO `boying_seat` VALUES (244, 82, 'A等座', 880.00, 150);
INSERT INTO `boying_seat` VALUES (245, 82, 'B等座', 630.00, 150);
INSERT INTO `boying_seat` VALUES (246, 82, 'C等座', 380.00, 130);
INSERT INTO `boying_seat` VALUES (247, 83, 'A等座', 780.00, 160);
INSERT INTO `boying_seat` VALUES (248, 83, 'B等座', 580.00, 170);
INSERT INTO `boying_seat` VALUES (249, 83, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (250, 84, 'A等座', 580.00, 170);
INSERT INTO `boying_seat` VALUES (251, 84, 'B等座', 430.00, 160);
INSERT INTO `boying_seat` VALUES (252, 84, 'C等座', 280.00, 170);
INSERT INTO `boying_seat` VALUES (253, 85, 'A等座', 880.00, 170);
INSERT INTO `boying_seat` VALUES (254, 85, 'B等座', 530.00, 180);
INSERT INTO `boying_seat` VALUES (255, 85, 'C等座', 180.00, 160);
INSERT INTO `boying_seat` VALUES (256, 86, 'A等座', 680.00, 200);
INSERT INTO `boying_seat` VALUES (257, 86, 'B等座', 530.00, 200);
INSERT INTO `boying_seat` VALUES (258, 86, 'C等座', 380.00, 180);
INSERT INTO `boying_seat` VALUES (259, 87, 'A等座', 780.00, 180);
INSERT INTO `boying_seat` VALUES (260, 87, 'B等座', 530.00, 170);
INSERT INTO `boying_seat` VALUES (261, 87, 'C等座', 280.00, 220);
INSERT INTO `boying_seat` VALUES (262, 88, 'A等座', 880.00, 150);
INSERT INTO `boying_seat` VALUES (263, 88, 'B等座', 630.00, 160);
INSERT INTO `boying_seat` VALUES (264, 88, 'C等座', 380.00, 170);
INSERT INTO `boying_seat` VALUES (265, 89, 'A等座', 880.00, 160);
INSERT INTO `boying_seat` VALUES (266, 89, 'B等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (267, 89, 'C等座', 280.00, 140);
INSERT INTO `boying_seat` VALUES (268, 90, 'A等座', 680.00, 160);
INSERT INTO `boying_seat` VALUES (269, 90, 'B等座', 380.00, 160);
INSERT INTO `boying_seat` VALUES (270, 90, 'C等座', 80.00, 160);
INSERT INTO `boying_seat` VALUES (271, 91, 'A等座', 580.00, 160);
INSERT INTO `boying_seat` VALUES (272, 91, 'B等座', 330.00, 180);
INSERT INTO `boying_seat` VALUES (273, 91, 'C等座', 80.00, 150);

-- ----------------------------
-- Table structure for boying_show
-- ----------------------------
DROP TABLE IF EXISTS `boying_show`;
CREATE TABLE `boying_show`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '所属的目录',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `poster` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '该演唱会的海报信息',
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '存储该演唱会等的图文信息',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '演出所在城市',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '具体演出地址',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '演出开始时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '演出结束时间',
  `weight` int(11) NOT NULL DEFAULT 0 COMMENT '该演出展示的优先基本,0为不展示',
  `min_price` double NOT NULL DEFAULT 0,
  `max_price` double NOT NULL DEFAULT 0,
  `admin_delete` tinyint(1) NULL DEFAULT 0 COMMENT '管理员是否删除该演出，默认0，即不删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_show_category`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '演唱会信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_show
-- ----------------------------
INSERT INTO `boying_show` VALUES (1, 1, '【演唱会】完美的演唱会', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01liqivw2GdSHVQOCuz_!!2251059038.jpg', '无', '上海', '梅赛德斯奔驰中心', '2021-06-25 12:00:00', '2021-06-28 14:00:00', 5, 280, 580, 0);
INSERT INTO `boying_show` VALUES (2, 1, '【周杰伦】杰伦演唱会', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01z8h9Gg2GdSHerWAPp_!!2251059038.jpg', '无', '上海', '黄渡理工', '2021-06-15 19:07:55', '2021-06-18 11:07:29', 5, 180, 780, 0);
INSERT INTO `boying_show` VALUES (3, 1, '【摩登站】2021跨年倒计时重磅大趴“超模DJ荧光派对”-魅惑女王控场，地表最闪亮！', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01q8qfiQ2GdSHf6hc4B_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-23 15:00:00', '2021-06-26 17:00:00', 3, 180, 680, 0);
INSERT INTO `boying_show` VALUES (4, 1, '【全场畅饮】怒放2021 夜场', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01hAEZOO2GdSHhiNdcg_!!2251059038.png', '无', '广州', '美琪大戏院', '2021-06-14 07:02:02', '2021-06-17 09:00:00', 2, 80, 880, 0);
INSERT INTO `boying_show` VALUES (5, 1, '【中国新说唱、说唱听我的 人气选手】 出来玩er·全国巡演 ', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN01HiPqZe2GdSHB4qO8k_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-21 12:00:00', '2021-06-24 14:00:00', 2, 180, 780, 0);
INSERT INTO `boying_show` VALUES (6, 1, '【珍珠剧场】阿黛尔&艾德希兰 致敬音乐会', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01uM01x12GdSHMnlKV0_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-24 18:00:00', '2021-06-27 20:00:00', 5, 80, 680, 0);
INSERT INTO `boying_show` VALUES (7, 1, '“繁星闪烁” AKB48 Team SH总选答谢巡回演唱会 ', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN019bIWyQ2GdSHh7uIeP_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-14 21:00:00', '2021-06-17 23:00:00', 5, 280, 880, 0);
INSERT INTO `boying_show` VALUES (8, 1, '韩雪、刘令飞领衔主演音乐剧《白夜行》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01nGg2Nw2GdSHNvXy5S_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-21 07:00:00', '2021-06-24 09:00:00', 3, 280, 880, 0);
INSERT INTO `boying_show` VALUES (9, 1, '音乐剧《献给阿尔吉侬的花束》12月23日-1月3日', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01S0bXpe2GdSHUO4rQ8_!!2251059038.png', '无', '北京', '中国大戏院', '2021-06-17 07:00:00', '2021-06-20 09:00:00', 2, 380, 780, 0);
INSERT INTO `boying_show` VALUES (10, 2, '开心麻花首部悬疑惊悚喜剧《醉后赢家》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01Vv1mQO2GdSFvnmIDs_!!2251059038.png', '无', '上海', '文化广场', '2021-06-16 21:00:00', '2021-06-19 23:00:00', 4, 80, 580, 0);
INSERT INTO `boying_show` VALUES (11, 2, '赖声川编剧、导演，倪妮主演话剧《幺幺洞捌》', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN013bRFhI2GdSHdykcBh_!!0-item_pic.jpg', '无', '广州', '美琪大戏院', '2021-06-20 21:00:00', '2021-06-23 23:00:00', 5, 180, 780, 0);
INSERT INTO `boying_show` VALUES (12, 2, '东野圭吾惊心悬疑舞台剧《片想》', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01DB9Lfj2GdSHc2UTKI_!!0-item_pic.jpg', '无', '广州', '美琪大戏院', '2021-06-18 15:00:00', '2021-06-21 17:00:00', 5, 80, 580, 0);
INSERT INTO `boying_show` VALUES (13, 2, '话剧艺术中心·环球舞台演出季 阿加莎·克里斯蒂传世巨著《无人生还》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01zNhxDZ2GdSHQBpBUH_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-13 15:00:00', '2021-06-16 17:00:00', 4, 180, 880, 0);
INSERT INTO `boying_show` VALUES (14, 2, '话剧艺术中心·环球舞台演出季 阿加莎·克里斯蒂经典法庭大戏 《原告证人》（又译：《控方证人》）', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01Yz8Tv72GdSHVQgjsZ_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-20 18:00:00', '2021-06-23 20:00:00', 3, 180, 680, 0);
INSERT INTO `boying_show` VALUES (15, 2, '开心麻花高糖喜剧《恋爱吧！人类》', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', '无', '深圳', '时尚艺术中心', '2021-06-21 18:00:00', '2021-06-24 20:00:00', 4, 80, 680, 0);
INSERT INTO `boying_show` VALUES (16, 2, '春放 民国知识分子喜剧《四张机》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01CDNTAE2GdSHgYIWyR_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-12 07:00:00', '2021-06-15 09:00:00', 3, 180, 680, 0);
INSERT INTO `boying_show` VALUES (17, 2, '话剧《平凡的世界》[九维钜献] 陕西人艺戏剧周', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01rlBt242GdSHKvABvO_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-15 12:00:00', '2021-06-18 14:00:00', 4, 380, 780, 0);
INSERT INTO `boying_show` VALUES (18, 3, '话剧《四世同堂》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01kj7S8f2GdSHGdjaOZ_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-18 21:00:00', '2021-06-21 23:00:00', 1, 380, 880, 0);
INSERT INTO `boying_show` VALUES (19, 3, '2020圣诞超级枕头大战-全程高能一战入魂-', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN01XuIDSq2GdSHJT4EG4_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-20 18:00:00', '2021-06-23 20:00:00', 2, 80, 580, 0);
INSERT INTO `boying_show` VALUES (20, 3, '开心麻花2020年度大戏《了不起的爹地》', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01nQBW6Q2GdSHf0NgCY_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-23 21:00:00', '2021-06-26 23:00:00', 2, 280, 780, 0);
INSERT INTO `boying_show` VALUES (21, 3, '《暗恋桃花源》专属版', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN01pljSWM2GdSHGfWuJ7_!!2-item_pic.png', '无', '北京', '中国大戏院', '2021-06-17 12:00:00', '2021-06-20 14:00:00', 4, 180, 680, 0);
INSERT INTO `boying_show` VALUES (22, 3, '话剧艺术中心·环球舞台演出季 百老汇悬疑惊悚喜剧 2011~2021 十周年·纪念版 《死亡陷阱》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01liqivw2GdSHVQOCuz_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-15 21:00:00', '2021-06-18 23:00:00', 4, 280, 580, 0);
INSERT INTO `boying_show` VALUES (23, 3, '开心麻花爆笑舞台剧《婿事待发》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01t1GRQy2GdSGfQfHl9_!!2251059038.png', '无', '深圳', '时尚艺术中心', '2021-06-20 12:00:00', '2021-06-23 14:00:00', 3, 280, 880, 0);
INSERT INTO `boying_show` VALUES (24, 3, '浸入式戏剧《不眠之夜》', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01EOAed82GdSGLVFrr4_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-15 07:00:00', '2021-06-18 09:00:00', 5, 80, 680, 0);
INSERT INTO `boying_show` VALUES (25, 3, '天刀IP国风嘉年华 暨手游剑荡八荒S1开幕式', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01GsVqRp2GdSHqg6uWb_!!2-item_pic.png', '无', '广州', '美琪大戏院', '2021-06-16 07:00:00', '2021-06-19 09:00:00', 2, 380, 780, 0);
INSERT INTO `boying_show` VALUES (26, 4, 'DramaKids艺术剧团·圣诞亲子视听音乐会《铃儿响叮当 Jingle Bells》 ——“一场可以看的音乐会”', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01z8h9Gg2GdSHerWAPp_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-20 07:00:00', '2021-06-23 09:00:00', 5, 380, 580, 0);
INSERT INTO `boying_show` VALUES (27, 4, '《欢乐马戏》--马戏城中剧场亲子马戏', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01XQt7KQ2GdSHTQMFaq_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-24 18:00:00', '2021-06-27 20:00:00', 5, 380, 880, 0);
INSERT INTO `boying_show` VALUES (28, 4, '开心麻花重磅新戏《贼想得到你》', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01jAyCoN2GdSHFuN7qa_!!0-item_pic.jpg', '无', '深圳', '时尚艺术中心', '2021-06-24 07:00:00', '2021-06-27 09:00:00', 2, 280, 680, 0);
INSERT INTO `boying_show` VALUES (29, 4, '开心麻花首部原创爆笑舞台剧《皇帝的新娘》', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN011P71Go2GdSHKXhJnJ_!!0-item_pic.jpg', '无', '上海', '文化广场', '2021-06-23 12:00:00', '2021-06-26 14:00:00', 3, 180, 880, 0);
INSERT INTO `boying_show` VALUES (30, 4, '【圣诞特惠】开心麻花爆笑舞台剧《竞演州长》', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01Qcl0wP2GdSHKhvtkr_!!0-item_pic.jpg', '无', '上海', '文化广场', '2021-06-15 18:00:00', '2021-06-18 20:00:00', 3, 380, 680, 0);
INSERT INTO `boying_show` VALUES (31, 4, '音乐剧《春之觉醒》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01a8TwFQ2GdSHPxOPP7_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-20 21:00:00', '2021-06-23 23:00:00', 1, 280, 680, 0);
INSERT INTO `boying_show` VALUES (32, 4, '开心麻花摇滚喜剧《牢友记》', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN019SIp1l2GdSHVqlHPD_!!0-item_pic.jpg', '无', '上海', '文化广场', '2021-06-18 12:00:00', '2021-06-21 14:00:00', 3, 80, 680, 0);
INSERT INTO `boying_show` VALUES (33, 4, '天猫 | 2020 Sneaker Con 球鞋潮流展（）', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01VOZN3b2GdSHNPkS8U_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-20 07:00:00', '2021-06-23 09:00:00', 1, 280, 780, 0);
INSERT INTO `boying_show` VALUES (34, 4, '“故宫里的神兽世界”全国首展', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN01RankqJ2GdSHHCRjGw_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-18 15:00:00', '2021-06-21 17:00:00', 5, 180, 780, 0);
INSERT INTO `boying_show` VALUES (35, 4, '2020芭莎珠宝国际设计师精品展', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01YP7ycU2GdSHUwU7wU_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-24 18:00:00', '2021-06-27 20:00:00', 5, 380, 880, 0);
INSERT INTO `boying_show` VALUES (36, 4, '奥斯吉美奥斯OSGEMEOS个展：来者皆是客 复星美术馆', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN016NrH572GdSHf4BeaG_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-17 15:00:00', '2021-06-20 17:00:00', 3, 280, 580, 0);
INSERT INTO `boying_show` VALUES (37, 5, '大美之颂——云冈石窟千年记忆与对话', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01UROuwN2GdSHauhPHF_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-17 07:00:00', '2021-06-20 09:00:00', 2, 180, 680, 0);
INSERT INTO `boying_show` VALUES (38, 5, '新年重磅 |“波普图像”——安迪·沃霍尔的1962-1987', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01B4Qne72GdSHaRFXmT_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-14 18:00:00', '2021-06-17 20:00:00', 3, 80, 580, 0);
INSERT INTO `boying_show` VALUES (39, 5, '白玉兰国际冰雪节', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN019FCZTC2GdSHc22AJG_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-18 21:00:00', '2021-06-21 23:00:00', 4, 80, 580, 0);
INSERT INTO `boying_show` VALUES (40, 5, '火红中国年 民族乐团新春音乐会', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN01kcopvp2GdSHfjN5bK_!!2-item_pic.png', '无', '广州', '美琪大戏院', '2021-06-24 12:00:00', '2021-06-27 14:00:00', 3, 380, 580, 0);
INSERT INTO `boying_show` VALUES (41, 5, '20201227贝多芬马拉松Ⅱ', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01L6lqQX2GdSHhoTdgL_!!0-item_pic.jpg', '无', '深圳', '时尚艺术中心', '2021-06-21 18:00:00', '2021-06-24 20:00:00', 5, 180, 580, 0);
INSERT INTO `boying_show` VALUES (42, 5, '心弦澎湃——弦乐之声迎新音乐会', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01mbxIN52GdSHTnUlq6_!!2251059038.png', '无', '上海', '文化广场', '2021-06-17 12:00:00', '2021-06-20 14:00:00', 2, 380, 880, 0);
INSERT INTO `boying_show` VALUES (43, 5, '“圆舞曲之夜”——2021新年交响音乐会', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01QCQZRY2GdSHXO1c1x_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-22 15:00:00', '2021-06-25 17:00:00', 2, 180, 580, 0);
INSERT INTO `boying_show` VALUES (44, 5, '国青管弦乐团2021新年音乐会', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01byzd1r2GdSHfMcDGv_!!2-item_pic.png', '无', '广州', '美琪大戏院', '2021-06-22 12:00:00', '2021-06-25 14:00:00', 3, 80, 580, 0);
INSERT INTO `boying_show` VALUES (45, 5, '音乐剧《在远方》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN010lsjvA2GdSHMaHZlw_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-25 15:00:00', '2021-06-28 17:00:00', 4, 180, 580, 0);
INSERT INTO `boying_show` VALUES (46, 5, '【缪时客2020年末钜献】推理音乐剧《小说》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01XzGnQz2GdSHSSod7C_!!2251059038.png', '无', '深圳', '时尚艺术中心', '2021-06-14 21:00:00', '2021-06-17 23:00:00', 4, 80, 780, 0);
INSERT INTO `boying_show` VALUES (47, 5, '话剧《白鹿原》【九维钜献】陕西人艺戏剧周', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01QNPidS2GdSH6aFEtH_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-23 07:00:00', '2021-06-26 09:00:00', 1, 80, 880, 0);
INSERT INTO `boying_show` VALUES (48, 5, '艺坛不老松——著名滑稽表演艺术家李九松纪念演出', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN01ryUALW2GdSHW7eChu_!!2251059038.png', '无', '深圳', '时尚艺术中心', '2021-06-23 15:00:00', '2021-06-26 17:00:00', 5, 80, 680, 0);
INSERT INTO `boying_show` VALUES (49, 5, '毛猛达 沈荣海 全新作品倾情呈现《石库门的笑声》', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01ztBVQC2GdSHhMfTUt_!!2-item_pic.png', '无', '上海', '文化广场', '2021-06-16 21:00:00', '2021-06-19 23:00:00', 1, 280, 680, 0);
INSERT INTO `boying_show` VALUES (50, 5, '名剧展演 越剧《红楼梦》', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01pRHX6i2GdSHMoSOnp_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-21 12:00:00', '2021-06-24 14:00:00', 3, 180, 880, 0);
INSERT INTO `boying_show` VALUES (51, 5, '【首秀】七七喜剧脱口秀', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01xN2iQE2GdSHjlpXfK_!!2251059038.png', '无', '广州', '美琪大戏院', '2021-06-20 12:00:00', '2021-06-23 14:00:00', 2, 380, 880, 0);
INSERT INTO `boying_show` VALUES (52, 5, '古戏楼版《红楼梦》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01NIz5rc2GdSHkDHHud_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-14 21:00:00', '2021-06-17 23:00:00', 4, 180, 880, 0);
INSERT INTO `boying_show` VALUES (53, 5, '沪剧《雷雨》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01gTadXv2GdSHVQWLVo_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-21 12:00:00', '2021-06-24 14:00:00', 5, 80, 780, 0);
INSERT INTO `boying_show` VALUES (54, 8, '歌舞团 舞剧《朱鹮》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01EYYoGa2GdSHbZdQii_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-21 07:00:00', '2021-06-24 09:00:00', 1, 380, 780, 0);
INSERT INTO `boying_show` VALUES (55, 8, '话剧艺术中心·环球舞台演出季 托尼奖最佳剧本·奥利弗奖最佳喜剧 英国经典闹剧 《糊涂戏班》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01qJk20v2GdSHPFeif0_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-13 15:00:00', '2021-06-16 17:00:00', 1, 380, 580, 0);
INSERT INTO `boying_show` VALUES (56, 8, '黎星工作室 舞蹈剧场《大饭店》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01u4nG252GdSHFKFINu_!!2251059038.png', '无', '北京', '中国大戏院', '2021-06-22 21:00:00', '2021-06-25 23:00:00', 4, 80, 880, 0);
INSERT INTO `boying_show` VALUES (57, 8, '金星舞蹈团演出周 现代舞《我》', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN014tXu6k2GdSHfuRxdw_!!0-item_pic.jpg', '无', '深圳', '时尚艺术中心', '2021-06-17 12:00:00', '2021-06-20 14:00:00', 3, 380, 780, 0);
INSERT INTO `boying_show` VALUES (58, 8, '金星舞蹈团 现代舞《野花》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01BUTcsd2GdSHiHMTmw_!!2251059038.png', '无', '上海', '文化广场', '2021-06-14 07:00:00', '2021-06-17 09:00:00', 5, 280, 680, 0);
INSERT INTO `boying_show` VALUES (59, 8, '金星舞蹈团演出周 现代舞《进行时》', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN01dOFW1I2GdSHkVpNv0_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-21 12:00:00', '2021-06-24 14:00:00', 1, 380, 880, 0);
INSERT INTO `boying_show` VALUES (60, 8, '当代舞蹈剧场《沧浪》', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN01n2Mz1m2GdSHfuRxir_!!0-item_pic.jpg', '无', '广州', '美琪大戏院', '2021-06-14 12:00:00', '2021-06-17 14:00:00', 4, 180, 880, 0);
INSERT INTO `boying_show` VALUES (61, 7, '2020-2021跨年倒计时“奇葩秀” ', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN019ozDcS2GdSHiPtq6c_!!0-item_pic.jpg', '无', '广州', '美琪大戏院', '2021-06-17 15:00:00', '2021-06-20 17:00:00', 4, 280, 780, 0);
INSERT INTO `boying_show` VALUES (62, 7, '【MAO Live呈现】|【全场畅饮】圣诞.不孤单 晚场 ', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01OhbU3S2GdSHjHeM25_!!2251059038.png', '无', '上海', '文化广场', '2021-06-22 15:00:00', '2021-06-25 17:00:00', 3, 80, 880, 0);
INSERT INTO `boying_show` VALUES (63, 7, '【摩登站】2021跨年倒计时重磅大趴“超模DJ荧光派对”-魅惑女王控场，地表最闪亮！', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01q8qfiQ2GdSHf6hc4B_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-16 15:00:00', '2021-06-19 17:00:00', 5, 280, 580, 0);
INSERT INTO `boying_show` VALUES (64, 7, '【全场畅饮】怒放2021 夜场 ', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01hAEZOO2GdSHhiNdcg_!!2251059038.png', '无', '广州', '美琪大戏院', '2021-06-20 18:00:00', '2021-06-23 20:00:00', 4, 80, 780, 0);
INSERT INTO `boying_show` VALUES (65, 7, '【中国新说唱、说唱听我的 人气选手】 出来玩er·全国巡演 ', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN01HiPqZe2GdSHB4qO8k_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-15 15:00:00', '2021-06-18 17:00:00', 2, 280, 580, 0);
INSERT INTO `boying_show` VALUES (66, 7, '【珍珠剧场】阿黛尔&艾德希兰 致敬音乐会', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01uM01x12GdSHMnlKV0_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-17 12:00:00', '2021-06-20 14:00:00', 4, 80, 580, 0);
INSERT INTO `boying_show` VALUES (67, 7, '“繁星闪烁” AKB48 Team SH总选答谢巡回演唱会 ', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN019bIWyQ2GdSHh7uIeP_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-15 18:00:00', '2021-06-18 20:00:00', 4, 380, 880, 0);
INSERT INTO `boying_show` VALUES (68, 7, '韩雪、刘令飞领衔主演音乐剧《白夜行》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01nGg2Nw2GdSHNvXy5S_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-20 18:00:00', '2021-06-23 20:00:00', 4, 180, 580, 0);
INSERT INTO `boying_show` VALUES (69, 7, '音乐剧《献给阿尔吉侬的花束》12月23日-1月3日', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01S0bXpe2GdSHUO4rQ8_!!2251059038.png', '无', '北京', '中国大戏院', '2021-06-15 07:00:00', '2021-06-18 09:00:00', 1, 80, 580, 0);
INSERT INTO `boying_show` VALUES (70, 7, '开心麻花首部悬疑惊悚喜剧《醉后赢家》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01Vv1mQO2GdSFvnmIDs_!!2251059038.png', '无', '上海', '文化广场', '2021-06-12 21:00:00', '2021-06-15 23:00:00', 2, 180, 680, 0);
INSERT INTO `boying_show` VALUES (71, 7, '赖声川编剧、导演，倪妮主演话剧《幺幺洞捌》', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN013bRFhI2GdSHdykcBh_!!0-item_pic.jpg', '无', '广州', '美琪大戏院', '2021-06-12 12:00:00', '2021-06-15 14:00:00', 5, 80, 780, 0);
INSERT INTO `boying_show` VALUES (72, 7, '东野圭吾惊心悬疑舞台剧《片想》', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01DB9Lfj2GdSHc2UTKI_!!0-item_pic.jpg', '无', '广州', '美琪大戏院', '2021-06-17 18:00:00', '2021-06-20 20:00:00', 5, 180, 880, 0);
INSERT INTO `boying_show` VALUES (73, 7, '话剧艺术中心·环球舞台演出季 阿加莎·克里斯蒂传世巨著《无人生还》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01zNhxDZ2GdSHQBpBUH_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-24 21:00:00', '2021-06-27 23:00:00', 1, 280, 580, 0);
INSERT INTO `boying_show` VALUES (74, 6, '话剧艺术中心·环球舞台演出季 阿加莎·克里斯蒂经典法庭大戏 《原告证人》（又译：《控方证人》）', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01Yz8Tv72GdSHVQgjsZ_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-19 12:00:00', '2021-06-22 14:00:00', 2, 280, 780, 0);
INSERT INTO `boying_show` VALUES (75, 6, '开心麻花高糖喜剧《恋爱吧！人类》', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', '无', '深圳', '时尚艺术中心', '2021-06-24 21:00:00', '2021-06-27 23:00:00', 1, 180, 880, 0);
INSERT INTO `boying_show` VALUES (76, 6, '春放 民国知识分子喜剧《四张机》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01CDNTAE2GdSHgYIWyR_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-24 12:00:00', '2021-06-27 14:00:00', 4, 180, 780, 0);
INSERT INTO `boying_show` VALUES (77, 6, '话剧《平凡的世界》[九维钜献] 陕西人艺戏剧周', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01rlBt242GdSHKvABvO_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-23 12:00:00', '2021-06-26 14:00:00', 1, 80, 780, 0);
INSERT INTO `boying_show` VALUES (78, 6, '话剧《四世同堂》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01kj7S8f2GdSHGdjaOZ_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-18 12:00:00', '2021-06-21 14:00:00', 5, 380, 580, 0);
INSERT INTO `boying_show` VALUES (79, 6, '2020圣诞超级枕头大战-全程高能一战入魂-', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i1/2251059038/O1CN01XuIDSq2GdSHJT4EG4_!!2251059038.jpg', '无', '深圳', '时尚艺术中心', '2021-06-24 07:00:00', '2021-06-27 09:00:00', 4, 80, 880, 0);
INSERT INTO `boying_show` VALUES (80, 6, '开心麻花2020年度大戏《了不起的爹地》', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01nQBW6Q2GdSHf0NgCY_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-19 18:00:00', '2021-06-22 20:00:00', 4, 180, 780, 0);
INSERT INTO `boying_show` VALUES (81, 6, '《暗恋桃花源》专属版', 'https://img.alicdn.com/bao/uploaded/i1/2251059038/O1CN01pljSWM2GdSHGfWuJ7_!!2-item_pic.png', '无', '北京', '中国大戏院', '2021-06-16 12:00:00', '2021-06-19 14:00:00', 1, 80, 880, 0);
INSERT INTO `boying_show` VALUES (82, 6, '话剧艺术中心·环球舞台演出季 百老汇悬疑惊悚喜剧 2011~2021 十周年·纪念版 《死亡陷阱》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN01liqivw2GdSHVQOCuz_!!2251059038.jpg', '无', '北京', '中国大戏院', '2021-06-12 21:00:00', '2021-06-15 23:00:00', 2, 380, 880, 0);
INSERT INTO `boying_show` VALUES (83, 6, '开心麻花爆笑舞台剧《婿事待发》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01t1GRQy2GdSGfQfHl9_!!2251059038.png', '无', '深圳', '时尚艺术中心', '2021-06-19 18:00:00', '2021-06-22 20:00:00', 3, 380, 780, 0);
INSERT INTO `boying_show` VALUES (84, 6, '浸入式戏剧《不眠之夜》', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01EOAed82GdSGLVFrr4_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-17 21:00:00', '2021-06-20 23:00:00', 3, 280, 580, 0);
INSERT INTO `boying_show` VALUES (85, 6, '天刀IP国风嘉年华 暨手游剑荡八荒S1开幕式', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01GsVqRp2GdSHqg6uWb_!!2-item_pic.png', '无', '广州', '美琪大戏院', '2021-06-19 07:00:00', '2021-06-22 09:00:00', 5, 180, 880, 0);
INSERT INTO `boying_show` VALUES (86, 4, 'DramaKids艺术剧团·圣诞亲子视听音乐会《铃儿响叮当 Jingle Bells》 ——“一场可以看的音乐会”', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01z8h9Gg2GdSHerWAPp_!!2251059038.jpg', '无', '上海', '文化广场', '2021-06-21 12:00:00', '2021-06-24 14:00:00', 2, 380, 680, 0);
INSERT INTO `boying_show` VALUES (87, 4, '《欢乐马戏》--马戏城中剧场亲子马戏', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN01XQt7KQ2GdSHTQMFaq_!!0-item_pic.jpg', '无', '北京', '中国大戏院', '2021-06-15 12:00:00', '2021-06-18 14:00:00', 5, 280, 780, 0);
INSERT INTO `boying_show` VALUES (88, 4, '开心麻花重磅新戏《贼想得到你》', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01jAyCoN2GdSHFuN7qa_!!0-item_pic.jpg', '无', '深圳', '时尚艺术中心', '2021-06-13 21:00:00', '2021-06-16 23:00:00', 4, 380, 880, 0);
INSERT INTO `boying_show` VALUES (89, 4, '开心麻花首部原创爆笑舞台剧《皇帝的新娘》', 'https://img.alicdn.com/bao/uploaded/i2/2251059038/O1CN011P71Go2GdSHKXhJnJ_!!0-item_pic.jpg', '无', '上海', '文化广场', '2021-06-25 12:00:00', '2021-06-28 14:00:00', 3, 280, 880, 0);
INSERT INTO `boying_show` VALUES (90, 4, '【圣诞特惠】开心麻花爆笑舞台剧《竞演州长》', 'https://img.alicdn.com/bao/uploaded/i4/2251059038/O1CN01Qcl0wP2GdSHKhvtkr_!!0-item_pic.jpg', '无', '上海', '文化广场', '2021-06-12 15:00:00', '2021-06-15 17:00:00', 1, 80, 680, 0);
INSERT INTO `boying_show` VALUES (91, 4, '音乐剧《春之觉醒》', 'https://img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i4/2251059038/O1CN01a8TwFQ2GdSHPxOPP7_!!2251059038.jpg', '无', '广州', '美琪大戏院', '2021-06-23 15:00:00', '2021-06-26 17:00:00', 1, 80, 580, 0);

-- ----------------------------
-- Table structure for boying_stock
-- ----------------------------
DROP TABLE IF EXISTS `boying_stock`;
CREATE TABLE `boying_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '剩余的库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 276 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '演出座次对应的库存' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_stock
-- ----------------------------
INSERT INTO `boying_stock` VALUES (1, 170);
INSERT INTO `boying_stock` VALUES (2, 210);
INSERT INTO `boying_stock` VALUES (3, 180);
INSERT INTO `boying_stock` VALUES (4, 120);
INSERT INTO `boying_stock` VALUES (5, 120);
INSERT INTO `boying_stock` VALUES (6, 140);
INSERT INTO `boying_stock` VALUES (7, 150);
INSERT INTO `boying_stock` VALUES (8, 140);
INSERT INTO `boying_stock` VALUES (9, 180);
INSERT INTO `boying_stock` VALUES (10, 160);
INSERT INTO `boying_stock` VALUES (11, 200);
INSERT INTO `boying_stock` VALUES (12, 160);
INSERT INTO `boying_stock` VALUES (13, 140);
INSERT INTO `boying_stock` VALUES (14, 160);
INSERT INTO `boying_stock` VALUES (15, 150);
INSERT INTO `boying_stock` VALUES (16, 190);
INSERT INTO `boying_stock` VALUES (17, 170);
INSERT INTO `boying_stock` VALUES (18, 210);
INSERT INTO `boying_stock` VALUES (19, 190);
INSERT INTO `boying_stock` VALUES (20, 210);
INSERT INTO `boying_stock` VALUES (21, 150);
INSERT INTO `boying_stock` VALUES (22, 200);
INSERT INTO `boying_stock` VALUES (23, 170);
INSERT INTO `boying_stock` VALUES (24, 180);
INSERT INTO `boying_stock` VALUES (25, 140);
INSERT INTO `boying_stock` VALUES (26, 160);
INSERT INTO `boying_stock` VALUES (27, 190);
INSERT INTO `boying_stock` VALUES (28, 140);
INSERT INTO `boying_stock` VALUES (29, 170);
INSERT INTO `boying_stock` VALUES (30, 160);
INSERT INTO `boying_stock` VALUES (31, 160);
INSERT INTO `boying_stock` VALUES (32, 160);
INSERT INTO `boying_stock` VALUES (33, 160);
INSERT INTO `boying_stock` VALUES (34, 160);
INSERT INTO `boying_stock` VALUES (35, 170);
INSERT INTO `boying_stock` VALUES (36, 200);
INSERT INTO `boying_stock` VALUES (37, 180);
INSERT INTO `boying_stock` VALUES (38, 140);
INSERT INTO `boying_stock` VALUES (39, 160);
INSERT INTO `boying_stock` VALUES (40, 200);
INSERT INTO `boying_stock` VALUES (41, 170);
INSERT INTO `boying_stock` VALUES (42, 160);
INSERT INTO `boying_stock` VALUES (43, 200);
INSERT INTO `boying_stock` VALUES (44, 180);
INSERT INTO `boying_stock` VALUES (45, 170);
INSERT INTO `boying_stock` VALUES (46, 170);
INSERT INTO `boying_stock` VALUES (47, 150);
INSERT INTO `boying_stock` VALUES (48, 160);
INSERT INTO `boying_stock` VALUES (49, 200);
INSERT INTO `boying_stock` VALUES (50, 190);
INSERT INTO `boying_stock` VALUES (51, 170);
INSERT INTO `boying_stock` VALUES (52, 150);
INSERT INTO `boying_stock` VALUES (53, 130);
INSERT INTO `boying_stock` VALUES (54, 170);
INSERT INTO `boying_stock` VALUES (55, 170);
INSERT INTO `boying_stock` VALUES (56, 160);
INSERT INTO `boying_stock` VALUES (57, 120);
INSERT INTO `boying_stock` VALUES (58, 230);
INSERT INTO `boying_stock` VALUES (59, 180);
INSERT INTO `boying_stock` VALUES (60, 210);
INSERT INTO `boying_stock` VALUES (61, 160);
INSERT INTO `boying_stock` VALUES (62, 130);
INSERT INTO `boying_stock` VALUES (63, 150);
INSERT INTO `boying_stock` VALUES (64, 180);
INSERT INTO `boying_stock` VALUES (65, 160);
INSERT INTO `boying_stock` VALUES (66, 180);
INSERT INTO `boying_stock` VALUES (67, 140);
INSERT INTO `boying_stock` VALUES (68, 150);
INSERT INTO `boying_stock` VALUES (69, 160);
INSERT INTO `boying_stock` VALUES (70, 160);
INSERT INTO `boying_stock` VALUES (71, 180);
INSERT INTO `boying_stock` VALUES (72, 150);
INSERT INTO `boying_stock` VALUES (73, 130);
INSERT INTO `boying_stock` VALUES (74, 140);
INSERT INTO `boying_stock` VALUES (75, 170);
INSERT INTO `boying_stock` VALUES (76, 120);
INSERT INTO `boying_stock` VALUES (77, 150);
INSERT INTO `boying_stock` VALUES (78, 150);
INSERT INTO `boying_stock` VALUES (79, 180);
INSERT INTO `boying_stock` VALUES (80, 160);
INSERT INTO `boying_stock` VALUES (81, 180);
INSERT INTO `boying_stock` VALUES (82, 160);
INSERT INTO `boying_stock` VALUES (83, 190);
INSERT INTO `boying_stock` VALUES (84, 180);
INSERT INTO `boying_stock` VALUES (85, 190);
INSERT INTO `boying_stock` VALUES (86, 160);
INSERT INTO `boying_stock` VALUES (87, 170);
INSERT INTO `boying_stock` VALUES (88, 160);
INSERT INTO `boying_stock` VALUES (89, 200);
INSERT INTO `boying_stock` VALUES (90, 160);
INSERT INTO `boying_stock` VALUES (91, 190);
INSERT INTO `boying_stock` VALUES (92, 160);
INSERT INTO `boying_stock` VALUES (93, 130);
INSERT INTO `boying_stock` VALUES (94, 190);
INSERT INTO `boying_stock` VALUES (95, 200);
INSERT INTO `boying_stock` VALUES (96, 180);
INSERT INTO `boying_stock` VALUES (97, 160);
INSERT INTO `boying_stock` VALUES (98, 190);
INSERT INTO `boying_stock` VALUES (99, 180);
INSERT INTO `boying_stock` VALUES (100, 170);
INSERT INTO `boying_stock` VALUES (101, 170);
INSERT INTO `boying_stock` VALUES (102, 170);
INSERT INTO `boying_stock` VALUES (103, 170);
INSERT INTO `boying_stock` VALUES (104, 140);
INSERT INTO `boying_stock` VALUES (105, 160);
INSERT INTO `boying_stock` VALUES (106, 150);
INSERT INTO `boying_stock` VALUES (107, 160);
INSERT INTO `boying_stock` VALUES (108, 150);
INSERT INTO `boying_stock` VALUES (109, 170);
INSERT INTO `boying_stock` VALUES (110, 160);
INSERT INTO `boying_stock` VALUES (111, 150);
INSERT INTO `boying_stock` VALUES (112, 200);
INSERT INTO `boying_stock` VALUES (113, 160);
INSERT INTO `boying_stock` VALUES (114, 180);
INSERT INTO `boying_stock` VALUES (115, 180);
INSERT INTO `boying_stock` VALUES (116, 160);
INSERT INTO `boying_stock` VALUES (117, 190);
INSERT INTO `boying_stock` VALUES (118, 190);
INSERT INTO `boying_stock` VALUES (119, 170);
INSERT INTO `boying_stock` VALUES (120, 160);
INSERT INTO `boying_stock` VALUES (121, 190);
INSERT INTO `boying_stock` VALUES (122, 170);
INSERT INTO `boying_stock` VALUES (123, 200);
INSERT INTO `boying_stock` VALUES (124, 130);
INSERT INTO `boying_stock` VALUES (125, 160);
INSERT INTO `boying_stock` VALUES (126, 140);
INSERT INTO `boying_stock` VALUES (127, 160);
INSERT INTO `boying_stock` VALUES (128, 160);
INSERT INTO `boying_stock` VALUES (129, 180);
INSERT INTO `boying_stock` VALUES (130, 160);
INSERT INTO `boying_stock` VALUES (131, 210);
INSERT INTO `boying_stock` VALUES (132, 170);
INSERT INTO `boying_stock` VALUES (133, 170);
INSERT INTO `boying_stock` VALUES (134, 190);
INSERT INTO `boying_stock` VALUES (135, 180);
INSERT INTO `boying_stock` VALUES (136, 190);
INSERT INTO `boying_stock` VALUES (137, 210);
INSERT INTO `boying_stock` VALUES (138, 180);
INSERT INTO `boying_stock` VALUES (139, 170);
INSERT INTO `boying_stock` VALUES (140, 130);
INSERT INTO `boying_stock` VALUES (141, 150);
INSERT INTO `boying_stock` VALUES (142, 170);
INSERT INTO `boying_stock` VALUES (143, 210);
INSERT INTO `boying_stock` VALUES (144, 190);
INSERT INTO `boying_stock` VALUES (145, 200);
INSERT INTO `boying_stock` VALUES (146, 160);
INSERT INTO `boying_stock` VALUES (147, 210);
INSERT INTO `boying_stock` VALUES (148, 160);
INSERT INTO `boying_stock` VALUES (149, 200);
INSERT INTO `boying_stock` VALUES (150, 170);
INSERT INTO `boying_stock` VALUES (151, 190);
INSERT INTO `boying_stock` VALUES (152, 180);
INSERT INTO `boying_stock` VALUES (153, 210);
INSERT INTO `boying_stock` VALUES (154, 190);
INSERT INTO `boying_stock` VALUES (155, 190);
INSERT INTO `boying_stock` VALUES (156, 170);
INSERT INTO `boying_stock` VALUES (157, 200);
INSERT INTO `boying_stock` VALUES (158, 180);
INSERT INTO `boying_stock` VALUES (159, 170);
INSERT INTO `boying_stock` VALUES (160, 190);
INSERT INTO `boying_stock` VALUES (161, 160);
INSERT INTO `boying_stock` VALUES (162, 170);
INSERT INTO `boying_stock` VALUES (163, 190);
INSERT INTO `boying_stock` VALUES (164, 170);
INSERT INTO `boying_stock` VALUES (165, 170);
INSERT INTO `boying_stock` VALUES (166, 140);
INSERT INTO `boying_stock` VALUES (167, 170);
INSERT INTO `boying_stock` VALUES (168, 140);
INSERT INTO `boying_stock` VALUES (169, 150);
INSERT INTO `boying_stock` VALUES (170, 200);
INSERT INTO `boying_stock` VALUES (171, 180);
INSERT INTO `boying_stock` VALUES (172, 190);
INSERT INTO `boying_stock` VALUES (173, 170);
INSERT INTO `boying_stock` VALUES (174, 150);
INSERT INTO `boying_stock` VALUES (175, 180);
INSERT INTO `boying_stock` VALUES (176, 130);
INSERT INTO `boying_stock` VALUES (177, 180);
INSERT INTO `boying_stock` VALUES (178, 170);
INSERT INTO `boying_stock` VALUES (179, 160);
INSERT INTO `boying_stock` VALUES (180, 170);
INSERT INTO `boying_stock` VALUES (181, 140);
INSERT INTO `boying_stock` VALUES (182, 150);
INSERT INTO `boying_stock` VALUES (183, 180);
INSERT INTO `boying_stock` VALUES (184, 180);
INSERT INTO `boying_stock` VALUES (185, 170);
INSERT INTO `boying_stock` VALUES (186, 180);
INSERT INTO `boying_stock` VALUES (187, 120);
INSERT INTO `boying_stock` VALUES (188, 140);
INSERT INTO `boying_stock` VALUES (189, 180);
INSERT INTO `boying_stock` VALUES (190, 170);
INSERT INTO `boying_stock` VALUES (191, 190);
INSERT INTO `boying_stock` VALUES (192, 180);
INSERT INTO `boying_stock` VALUES (193, 200);
INSERT INTO `boying_stock` VALUES (194, 190);
INSERT INTO `boying_stock` VALUES (195, 190);
INSERT INTO `boying_stock` VALUES (196, 200);
INSERT INTO `boying_stock` VALUES (197, 190);
INSERT INTO `boying_stock` VALUES (198, 160);
INSERT INTO `boying_stock` VALUES (199, 160);
INSERT INTO `boying_stock` VALUES (200, 230);
INSERT INTO `boying_stock` VALUES (201, 210);
INSERT INTO `boying_stock` VALUES (202, 150);
INSERT INTO `boying_stock` VALUES (203, 160);
INSERT INTO `boying_stock` VALUES (204, 130);
INSERT INTO `boying_stock` VALUES (205, 160);
INSERT INTO `boying_stock` VALUES (206, 150);
INSERT INTO `boying_stock` VALUES (207, 160);
INSERT INTO `boying_stock` VALUES (208, 200);
INSERT INTO `boying_stock` VALUES (209, 180);
INSERT INTO `boying_stock` VALUES (210, 210);
INSERT INTO `boying_stock` VALUES (211, 170);
INSERT INTO `boying_stock` VALUES (212, 180);
INSERT INTO `boying_stock` VALUES (213, 150);
INSERT INTO `boying_stock` VALUES (214, 210);
INSERT INTO `boying_stock` VALUES (215, 210);
INSERT INTO `boying_stock` VALUES (216, 200);
INSERT INTO `boying_stock` VALUES (217, 210);
INSERT INTO `boying_stock` VALUES (218, 190);
INSERT INTO `boying_stock` VALUES (219, 220);
INSERT INTO `boying_stock` VALUES (220, 130);
INSERT INTO `boying_stock` VALUES (221, 150);
INSERT INTO `boying_stock` VALUES (222, 140);
INSERT INTO `boying_stock` VALUES (223, 190);
INSERT INTO `boying_stock` VALUES (224, 190);
INSERT INTO `boying_stock` VALUES (225, 190);
INSERT INTO `boying_stock` VALUES (226, 150);
INSERT INTO `boying_stock` VALUES (227, 140);
INSERT INTO `boying_stock` VALUES (228, 140);
INSERT INTO `boying_stock` VALUES (229, 120);
INSERT INTO `boying_stock` VALUES (230, 150);
INSERT INTO `boying_stock` VALUES (231, 160);
INSERT INTO `boying_stock` VALUES (232, 180);
INSERT INTO `boying_stock` VALUES (233, 180);
INSERT INTO `boying_stock` VALUES (234, 140);
INSERT INTO `boying_stock` VALUES (235, 160);
INSERT INTO `boying_stock` VALUES (236, 160);
INSERT INTO `boying_stock` VALUES (237, 180);
INSERT INTO `boying_stock` VALUES (238, 140);
INSERT INTO `boying_stock` VALUES (239, 160);
INSERT INTO `boying_stock` VALUES (240, 140);
INSERT INTO `boying_stock` VALUES (241, 140);
INSERT INTO `boying_stock` VALUES (242, 120);
INSERT INTO `boying_stock` VALUES (243, 120);
INSERT INTO `boying_stock` VALUES (244, 150);
INSERT INTO `boying_stock` VALUES (245, 150);
INSERT INTO `boying_stock` VALUES (246, 130);
INSERT INTO `boying_stock` VALUES (247, 160);
INSERT INTO `boying_stock` VALUES (248, 170);
INSERT INTO `boying_stock` VALUES (249, 170);
INSERT INTO `boying_stock` VALUES (250, 170);
INSERT INTO `boying_stock` VALUES (251, 160);
INSERT INTO `boying_stock` VALUES (252, 170);
INSERT INTO `boying_stock` VALUES (253, 170);
INSERT INTO `boying_stock` VALUES (254, 180);
INSERT INTO `boying_stock` VALUES (255, 160);
INSERT INTO `boying_stock` VALUES (256, 200);
INSERT INTO `boying_stock` VALUES (257, 200);
INSERT INTO `boying_stock` VALUES (258, 180);
INSERT INTO `boying_stock` VALUES (259, 180);
INSERT INTO `boying_stock` VALUES (260, 170);
INSERT INTO `boying_stock` VALUES (261, 220);
INSERT INTO `boying_stock` VALUES (262, 150);
INSERT INTO `boying_stock` VALUES (263, 160);
INSERT INTO `boying_stock` VALUES (264, 170);
INSERT INTO `boying_stock` VALUES (265, 160);
INSERT INTO `boying_stock` VALUES (266, 160);
INSERT INTO `boying_stock` VALUES (267, 140);
INSERT INTO `boying_stock` VALUES (268, 160);
INSERT INTO `boying_stock` VALUES (269, 160);
INSERT INTO `boying_stock` VALUES (270, 160);
INSERT INTO `boying_stock` VALUES (271, 160);
INSERT INTO `boying_stock` VALUES (272, 180);
INSERT INTO `boying_stock` VALUES (273, 150);

-- ----------------------------
-- Table structure for boying_user
-- ----------------------------
DROP TABLE IF EXISTS `boying_user`;
CREATE TABLE `boying_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户加密后的密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '个人头像',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户真实姓名',
  `identity_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户身份证号',
  `age` int(11) NOT NULL DEFAULT 0 COMMENT '年龄',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户性别 1->男,0->女',
  `admin_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '管理员是否删除了该用户,默认为0，即未删除状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_phone`(`phone`) USING BTREE,
  INDEX `index_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200017 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boying_user
-- ----------------------------
INSERT INTO `boying_user` VALUES (200014, 'tongji4m3', '$2a$10$CLIbkQy8emngeePOePO0YOemEx7m4e4pEJVnkmCEUOdFc/KZv4Qfi', '15316162191', '15316162191@163.com', 'https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png', '李翠琪', '450222199811112191', 21, '2021-06-09 11:08:28', 1, 1);
INSERT INTO `boying_user` VALUES (200016, 'sjs', '$2a$10$w8SeTj2vEkJvCV5jlh/DxO5HqJFbujo4jGh8vuIUgz/YYPF9ossbG', '19946254167', '1171011192@qq.com', 'https://tongji4m3.oss-cn-beijing.aliyuncs.com/f_f_object_156_s512_f_object_156_0.png', '石稼晟', '111111111111111111', 21, '2021-06-09 11:16:13', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
