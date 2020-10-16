CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `receiver` varchar(64) NOT NULL,
  `phone` varchar(64) NOT NULL,
  `province` varchar(64) NULL COMMENT '省份',
  `city` varchar(64) NULL COMMENT '城市',
  `region` varchar(64) NULL COMMENT '城区',
  `street` varchar(64) NULL COMMENT '街道',
  `details` varchar(64) NOT NULL COMMENT '街道后的详细地址',
  `default` tinyint(1) NULL COMMENT '是否是默认地址,bool类型',
  PRIMARY KEY (`address_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '用户的收货地址表';

CREATE TABLE `admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `icon` varchar(64) NULL COMMENT '头像',
  `email` varchar(64) NULL,
  `create_time` datetime NULL COMMENT '创建时间',
  `login_time` datetime NULL COMMENT '最后登录时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`admin_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台用户表';

CREATE TABLE `admin_role`  (
  `admin_role_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`admin_role_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台用户和角色关系表';

CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL COMMENT '上级分类的编号：0表示一级分类',
  `name` varchar(64) NOT NULL,
  `level` int NULL COMMENT '分类级别',
  `weight` int NOT NULL COMMENT '用于排序,0则不显示',
  `icon` varchar(64) NULL COMMENT '该目录的图标',
  `description` varchar(255) NULL,
  PRIMARY KEY (`category_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '目录表,可标识是演唱会,还是相声表演等类型,一共有两级目录';

CREATE TABLE `class`  (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `showSessionId` int NOT NULL,
  `grade` int NOT NULL COMMENT '所属哪个级别,例如A等座,B等座',
  `capacity` int NOT NULL COMMENT '该级别座位的容量',
  `price` decimal(10, 2) NOT NULL COMMENT '该级别座位的定价',
  PRIMARY KEY (`class_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '对于某个演唱会的某个场次的座位级别信息表.包括每个级别的容量,价格等';

CREATE TABLE `frequent_buyers`  (
  `frequent_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(32) NOT NULL,
  `identity_number` varchar(32) NOT NULL COMMENT '常用购票人的身份证号码',
  `default` tinyint(1) NOT NULL COMMENT '是否为默认常用购票人',
  PRIMARY KEY (`frequent_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8;

CREATE TABLE `menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NULL COMMENT '父级ID',
  `create_time` datetime NULL COMMENT '创建时间',
  `title` varchar(64) NULL COMMENT '菜单名称',
  `level` int NULL COMMENT '菜单级数',
  `sort` int NULL COMMENT '菜单排序',
  `icon` varchar(255) NULL,
  `hidden` tinyint(1) NULL COMMENT '控制前端是否隐藏',
  PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台菜单表，用于控制后台用户可以访问的菜单，支持隐藏、排序和更改名称、图标。';

CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `show_session_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` int NOT NULL COMMENT ' 已取消,待付款,待使用,待评价',
  `time` datetime NOT NULL,
  `address_id` int NULL,
  PRIMARY KEY (`order_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '用户订单表';

CREATE TABLE `resource`  (
  `resource_id` int NOT NULL AUTO_INCREMENT,
  `resource_category_id` int NULL,
  `create_time` datetime NULL,
  `name` varchar(255) NULL,
  `url` varchar(255) NULL COMMENT '资源URL',
  `description` varchar(255) NULL COMMENT '资源描述',
  PRIMARY KEY (`resource_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台资源表，用于控制后台用户可以访问的接口，使用了Ant路径的匹配规则，可以使用通配符定义一系列接口的权限。';

CREATE TABLE `resource_category`  (
  `resource_category_id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL,
  `name` varchar(64) NULL,
  `sort` int NULL,
  PRIMARY KEY (`resource_category_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台资源分类表，在细粒度进行权限控制时，可能资源会比较多，所以设计了个资源分类的概念，便于给角色分配资源。';

CREATE TABLE `review`  (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `show_id` int NOT NULL,
  `star` int NOT NULL,
  `content` blob NOT NULL,
  `time` datetime NULL,
  PRIMARY KEY (`review_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '用户对订单产生的评价信息表';

CREATE TABLE `role`  (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(255) NULL COMMENT '角色描述',
  `admin_count` int NULL COMMENT '后台用户数量',
  `create_time` datetime NULL COMMENT '创建时间',
  `status` tinyint NULL COMMENT '启用状态：0->禁用；1->启用',
  `sort` int NULL COMMENT '用于排序',
  PRIMARY KEY (`role_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台用户角色表';

CREATE TABLE `role_menu`  (
  `role_menu_id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台角色菜单关系表，多对多关系，可以给一个角色分配多个菜单。';

CREATE TABLE `role_resource`  (
  `role_resource_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `resource_id` int NOT NULL,
  PRIMARY KEY (`role_resource_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '后台角色资源关系表，多对多关系，可以给一个角色分配多个资源。';

CREATE TABLE `show`  (
  `show_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `category_id` int NOT NULL COMMENT '所属的目录',
  `poster` varchar(64) NULL COMMENT '该演唱会的海报信息',
  `details` blob NULL COMMENT '存储该演唱会等的图文信息',
  `min_price` decimal(10, 2) NULL COMMENT '该演唱会的最低价',
  PRIMARY KEY (`show_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '演唱会信息表';

CREATE TABLE `show_session`  (
  `show_session_id` int NOT NULL,
  `show_id` int NOT NULL COMMENT '所属哪个演唱会',
  `city` varchar(64) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NULL,
  `address` varchar(64) NOT NULL COMMENT '具体演出地址',
  `weight` int NOT NULL COMMENT '上映后,已下架等,以及显示的优先级',
  PRIMARY KEY (`show_session_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '演唱会的场次,例如一个演唱会可能在多地巡回演出,在一个地方也可能有多个时间场次';

CREATE TABLE `ticket`  (
  `ticketId` int NOT NULL AUTO_INCREMENT,
  `frequentId` int NOT NULL,
  `classId` int NOT NULL,
  PRIMARY KEY (`ticketId`)
);

CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NULL,
  `password` varchar(32) NOT NULL,
  `real_name` varchar(32) NULL COMMENT '用户真实姓名',
  `identity_number` varchar(32) NULL COMMENT '用户身份证号',
  `phone` varchar(32) NOT NULL,
  `email` varchar(32) NULL,
  `age` int NULL,
  `gender` tinyint(1) NULL COMMENT '用户性别 1->男,0->女',
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COMMENT = '用户表';

ALTER TABLE `order` ADD FOREIGN KEY (`showSessionId`) REFERENCES `show_session` (`showSessionId`);
ALTER TABLE `review` ADD FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`);
ALTER TABLE `ticket` ADD FOREIGN KEY (`frequentId`) REFERENCES `frequent_buyers` (`frequentId`);

