CREATE TABLE `admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '后台用户表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `boying_show`  (
  `show_id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' ',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `category_id` int(11) NOT NULL COMMENT '所属的目录',
  `poster` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '该演唱会的海报图文信息(url)',
  `details` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '存储该演唱会等的图文信息',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该演唱会的最低价',
  `max_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该演唱会的最高价',
  `weight` int(11) NULL DEFAULT NULL COMMENT '该演出展示的优先基本,0为不展示',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '具体演出地址',
  `day_start` date NULL DEFAULT NULL COMMENT '演出开始日期',
  `day_end` date NULL DEFAULT NULL COMMENT '演出结束日期',
  PRIMARY KEY (`show_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '演唱会信息表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '上级分类的编号：0表示一级分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '目录名称',
  `weight` int(11) NOT NULL COMMENT '用于排序,0则不显示',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '该目录的图标',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录描述',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目录表,可标识是演唱会,还是相声表演等类型,一共有两级目录' ROW_FORMAT = DYNAMIC;

CREATE TABLE `show_class`  (
  `show_class_id` int(11) NOT NULL AUTO_INCREMENT,
  `show_session_id` int(11) NOT NULL COMMENT '所属场次Id',
  `name` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属哪个级别,例如\"学生单日票\", \"预售单日票\", \"全价单日票\", \"PRO单日票\"等等',
  `capacity` int(11) NOT NULL COMMENT '该级别座位的容量',
  `price` double(10, 2) NOT NULL COMMENT '该级别座位的定价',
  `stock` int NULL COMMENT '该级别座位的库存',
  PRIMARY KEY (`show_class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '对于某个演唱会的某个场次的票档信息表.包括每个级别的容量,价格等' ROW_FORMAT = DYNAMIC;

CREATE TABLE `show_session`  (
  `show_session_id` int(11) NOT NULL AUTO_INCREMENT,
  `show_id` int(11) NOT NULL COMMENT '所属演唱会Id',
  `start_time` datetime(0) NOT NULL COMMENT '演出场次开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '演出场次结束时间',
  `weight` int(11) NOT NULL COMMENT '上映后,已下架等,以及显示的优先级',
  PRIMARY KEY (`show_session_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '演唱会的场次,例如一个演唱会可能在多地巡回演出,在一个地方也可能有多个时间场次' ROW_FORMAT = DYNAMIC;

CREATE TABLE `ticket`  (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '所属订单Id',
  `show_class_id` int(11) NOT NULL COMMENT '所属座次Id',
  `qr_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '二维码图片,供观影人验证入场',
  PRIMARY KEY (`ticket_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户真实姓名',
  `identity_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户身份证号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '用户性别 1->男,0->女',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '帐号启用状态：0->禁用；1->启用',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '个人头像',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `user_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '所属用户Id',
  `show_id` int(11) NULL DEFAULT NULL COMMENT '所属演出Id',
  `show_session_id` int(11) NOT NULL COMMENT '所属场次Id',
  `status` int(11) NOT NULL COMMENT '待观看，已完成,已退订单(1,2,3)',
  `time` datetime(0) NOT NULL COMMENT '订单提交时间',
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '订单支付方式',
  `user_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '该订单对用户是否可见,即用户是否删除了该订单',
  `money` double(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '订单总金额',
  `ticket_count` int(11) NULL DEFAULT NULL COMMENT '票的总数',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单表' ROW_FORMAT = DYNAMIC;

