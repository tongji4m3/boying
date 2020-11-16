CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '所属用户Id',
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接收人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接收电话',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '城市',
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '城区',
  `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '街道',
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '街道后的详细地址',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的收货地址表' ROW_FORMAT = Dynamic;

CREATE TABLE `admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `login_time` datetime(0) NULL COMMENT '最后登录时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

CREATE TABLE `admin_role`  (
  `admin_role_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`admin_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

CREATE TABLE `boying_show`  (
  `show_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `category_id` int NOT NULL COMMENT '所属的目录',
  `poster` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '该演唱会的海报信息',
  `details` blob NULL COMMENT '存储该演唱会等的图文信息',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该演唱会的最低价',
  `max_price` decimal(10, 2) NULL COMMENT '该演唱会的最高价',
  `weight` int NULL COMMENT '该演出展示的优先基本,0为不展示',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '具体演出地址',
  `day_start` date NULL COMMENT '演出开始日期',
  `day_end` date NULL COMMENT '演出结束日期',
  PRIMARY KEY (`show_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '演唱会信息表' ROW_FORMAT = Dynamic;

CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL COMMENT '上级分类的编号：0表示一级分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '目录名称',
  `weight` int NOT NULL COMMENT '用于排序,0则不显示',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '该目录的图标',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录描述',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目录表,可标识是演唱会,还是相声表演等类型,一共有两级目录' ROW_FORMAT = Dynamic;

CREATE TABLE `frequent`  (
  `frequent_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '所属用户Id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '真实姓名',
  `identity_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '常用购票人的身份证号码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '联系电话',
  PRIMARY KEY (`frequent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '实际观看演出的人, 观演人, 或者叫观众' ROW_FORMAT = Dynamic;

CREATE TABLE `menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL COMMENT '父级ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '菜单名称',
  `level` int NOT NULL DEFAULT 1 COMMENT '菜单级数',
  `sort` int NOT NULL DEFAULT 1 COMMENT '菜单排序',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '前端名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '前端图标',
  `hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '控制前端是否隐藏',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台菜单表，用于控制后台用户可以访问的菜单，支持隐藏、排序和更改名称、图标。' ROW_FORMAT = Dynamic;

CREATE TABLE `resource`  (
  `resource_id` int NOT NULL AUTO_INCREMENT,
  `resource_category_id` int NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '资源URL',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '资源描述',
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台资源表，用于控制后台用户可以访问的接口，使用了Ant路径的匹配规则，可以使用通配符定义一系列接口的权限。' ROW_FORMAT = Dynamic;

CREATE TABLE `resource_category`  (
  `resource_category_id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sort` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`resource_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台资源分类表，在细粒度进行权限控制时，可能资源会比较多，所以设计了个资源分类的概念，便于给角色分配资源。' ROW_FORMAT = Dynamic;

CREATE TABLE `review`  (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL COMMENT '该评论所属用户Id',
  `show_id` int NOT NULL COMMENT '该评论所属演出Id',
  `star` int NOT NULL COMMENT '对演出打出的星级',
  `content` blob NOT NULL COMMENT '评论内容',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `like_count` int NULL COMMENT '点赞数量',
  `unlike_count` int NULL COMMENT '踩数量',
  `report_count` int NULL COMMENT '举报数量',
  `parent_id` int NULL COMMENT '如果是0则是单独评论,否则是对某个影评的评论',
  `weight` int NULL COMMENT '评论权重,管理员操作',
  `user_delete` tinyint(1) NULL COMMENT '用户是否删除了该评论',
  PRIMARY KEY (`review_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户对订单产生的评价信息表' ROW_FORMAT = Dynamic;

CREATE TABLE `role`  (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色描述',
  `admin_count` int NOT NULL DEFAULT 0 COMMENT '后台用户数量',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int NOT NULL DEFAULT 1 COMMENT '用于排序',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

CREATE TABLE `role_menu`  (
  `role_menu_id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台角色菜单关系表，多对多关系，可以给一个角色分配多个菜单。' ROW_FORMAT = Dynamic;

CREATE TABLE `role_resource`  (
  `role_resource_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `resource_id` int NOT NULL,
  PRIMARY KEY (`role_resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台角色资源关系表，多对多关系，可以给一个角色分配多个资源。' ROW_FORMAT = Dynamic;

CREATE TABLE `show_class`  (
  `show_class_id` int NOT NULL AUTO_INCREMENT,
  `show_session_id` int NOT NULL COMMENT '所属场次Id',
  `name` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属哪个级别,例如\"学生单日票\", \"预售单日票\", \"全价单日票\", \"PRO单日票\"等等',
  `capacity` int NOT NULL COMMENT '该级别座位的容量',
  `price` double(10, 2) NOT NULL COMMENT '该级别座位的定价',
  PRIMARY KEY (`show_class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '对于某个演唱会的某个场次的票档信息表.包括每个级别的容量,价格等' ROW_FORMAT = Dynamic;

CREATE TABLE `show_session`  (
  `show_session_id` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL COMMENT '所属演唱会Id',
  `start_time` datetime(0) NOT NULL COMMENT '演出场次开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '演出场次结束时间',
  `weight` int NOT NULL COMMENT '上映后,已下架等,以及显示的优先级',
  PRIMARY KEY (`show_session_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '演唱会的场次,例如一个演唱会可能在多地巡回演出,在一个地方也可能有多个时间场次' ROW_FORMAT = Dynamic;

CREATE TABLE `ticket`  (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL COMMENT '所属订单Id',
  `show_class_id` int NOT NULL COMMENT '所属座次Id',
  `qr_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '二维码图片,供观影人验证入场',
  PRIMARY KEY (`ticket_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户真实姓名',
  `identity_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户身份证号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '用户性别 1->男,0->女',
  `status` tinyint(1) NULL COMMENT '帐号启用状态：0->禁用；1->启用',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '个人头像',
  `default_frequent` int NULL COMMENT '默认观影者',
  `default_address` int NULL COMMENT '默认地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

CREATE TABLE `user_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '所属用户Id',
  `show_id` int NULL COMMENT '所属演出Id',
  `show_session_id` int NOT NULL COMMENT '所属场次Id',
  `address_id` int NULL COMMENT '这些票要邮寄到什么地方',
  `frequent_id` int NULL COMMENT '这些票的实际观演人',
  `status` int NOT NULL COMMENT '待评价,已完成(1,2)',
  `time` datetime(0) NOT NULL COMMENT '订单支付时间',
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '订单支付方式',
  `user_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '该订单对用户是否可见,即用户是否删除了该订单',
  `money` double(10, 2) ZEROFILL NULL COMMENT '订单总金额',
  `ticket_count` int NULL COMMENT '票的总数',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单表' ROW_FORMAT = Dynamic;

ALTER TABLE `address` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `admin_role` ADD FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `admin_role` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `resource` ADD FOREIGN KEY (`resource_category_id`) REFERENCES `resource_category` (`resource_category_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `review` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `review` ADD FOREIGN KEY (`show_id`) REFERENCES `boying_show` (`show_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `role_menu` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `role_menu` ADD FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `role_resource` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `role_resource` ADD FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `show_session` ADD FOREIGN KEY (`show_id`) REFERENCES `boying_show` (`show_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `user_order` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `user_order` ADD FOREIGN KEY (`show_id`) REFERENCES `boying_show` (`show_id`) ON DELETE CASCADE ON UPDATE CASCADE;

