INSERT INTO `admin`(`admin_id`, `username`, `password`, `icon`, `email`, `create_time`, `login_time`, `status`) VALUES (50, 'tongji4m3', '$2a$10$UT7yv6COd1/w5a9KdyTwvOD7cmMKSV.iwKDL8A7p1W8mldCOhZIQq', 'string', '1254931237@qq.com', '2020-11-02 14:13:56', '2020-11-02 19:44:59', 1);
INSERT INTO `admin`(`admin_id`, `username`, `password`, `icon`, `email`, `create_time`, `login_time`, `status`) VALUES (51, 'temp', '$2a$10$E4MgPMSFaZKGfhRiBLEKTe/.7ve4X6ly0QZvT8iakcLm4vzfLhJ5C', 'temp', 'temp1@qq.com', '2020-11-02 19:09:41', '2020-12-09 08:07:19', 1);
INSERT INTO `admin`(`admin_id`, `username`, `password`, `icon`, `email`, `create_time`, `login_time`, `status`) VALUES (52, 'fc', '$2a$10$4iYiC11ULPqIw4nVwQtuwuRFLp/XTp1dzQfxE9rnx1WifdhxpBAgG', 'string', '1289079621@qq.com', '2020-12-05 15:25:53', '2020-12-07 09:55:36', 1);


INSERT INTO `user`(`user_id`, `username`, `phone`, `password`, `real_name`, `identity_number`, `email`, `age`, `gender`, `status`, `icon`, `default_frequent`, `default_address`, `create_time`) VALUES (53, 'user1', '15868187446', '$2a$10$VMq1C7rzA6Bo/lDPLZso7OrXI1HX2c5HVVy5t5grHwFwHZhsHkvDq', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2020-11-30 14:16:46');


INSERT INTO `role` VALUES (1, 'root', 'root拥有一切权限', 0, '2020-11-02 16:52:45', 1, 1);
INSERT INTO `role` VALUES (2, '初始管理员', '暂时没有任何权限', 0, '2020-11-02 16:54:38', 1, 1);
INSERT INTO `role` VALUES (3, '权限管理员', '拥有权限系统相关所有权限', 0, '2020-11-02 19:08:31', 1, 1);


INSERT INTO `resource_category` VALUES (1, '2020-10-30 16:15:11', '报表模块', 1);
INSERT INTO `resource_category` VALUES (2, '2020-10-30 16:15:34', '用户模块', 1);
INSERT INTO `resource_category` VALUES (3, '2020-10-30 16:15:47', '演出模块', 1);
INSERT INTO `resource_category` VALUES (4, '2020-10-30 16:15:58', '订单模块', 1);
INSERT INTO `resource_category` VALUES (5, '2020-10-30 16:16:09', '评论模块', 1);
INSERT INTO `resource_category` VALUES (6, '2020-10-30 16:16:23', '权限模块', 1);



INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (1, 0, '1', 1, NULL, NULL);
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (50, 0, '123', 1, 'ewqe', 'qewqe');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (51, 0, '1234', 1, 'ewqe', 'qewqe');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (52, 0, '32132', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (53, 0, '31321321', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (54, 0, '321321', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (55, 0, '321321321', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (56, 0, '421412', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (57, 0, '32132132132', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (58, 0, '21321321321', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (59, 0, '12321321', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (60, 0, '999', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (61, 0, '4214231432', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (62, 0, '6543654365', 1, '', '');
INSERT INTO `category`(`category_id`, `parent_id`, `name`, `weight`, `icon`, `description`) VALUES (63, 0, '78741238748721', 1, '', '');

INSERT INTO `boying_show`(`show_id`, `name`, `category_id`, `poster`, `details`, `min_price`, `max_price`, `weight`, `city`, `address`, `day_start`, `day_end`) VALUES (1, 'fcsb', 1, '1', NULL, 100.00, 200.00, 1, '上海', '上海', '2020-12-08', '2020-12-09');


INSERT INTO `admin_role` VALUES (1, 50, 1);
INSERT INTO `admin_role` VALUES (2, 50, 3);
INSERT INTO `admin_role` VALUES (3, 51, 2);

INSERT INTO `menu` VALUES (1, 0, '2020-11-02 18:29:13', '权限', 0, 1, 'ums', 'ums', 0);
INSERT INTO `menu` VALUES (2, 1, '2020-11-02 18:29:51', '用户列表', 1, 1, 'admin', 'ums-admin', 0);
INSERT INTO `menu` VALUES (3, 1, '2020-11-02 18:30:13', '角色列表', 1, 1, 'role', 'ums-role', 0);
INSERT INTO `menu` VALUES (4, 1, '2020-11-02 18:30:53', '菜单列表', 1, 1, 'menu', 'ums-menu', 0);
INSERT INTO `menu` VALUES (5, 1, '2020-11-02 18:31:13', '资源列表', 1, 1, 'resource', 'ums-resource', 0);


INSERT INTO `resource` VALUES (1, 6, '2020-10-30 16:53:55', '后台管理员管理', '/admin/**', '对后台管理员进行管理所需资源');
INSERT INTO `resource` VALUES (2, 6, '2020-10-30 17:07:01', '后台菜单管理', '/menu/**', '对后台菜单进行管理所需资源');
INSERT INTO `resource` VALUES (3, 6, '2020-10-30 17:07:02', '后台资源目录管理', '/resourceCategory/**', '对后台资源目录进行管理所需资源');
INSERT INTO `resource` VALUES (4, 6, '2020-10-30 17:07:03', '后台资源管理', '/resource/**', '对后台资源进行管理所需资源');
INSERT INTO `resource` VALUES (5, 6, '2020-10-30 17:07:04', '后台角色管理', '/role/**', '对后台角色进行管理所需资源');


INSERT INTO `role_menu` VALUES (1, 1, 1);
INSERT INTO `role_menu` VALUES (2, 2, 1);
INSERT INTO `role_menu` VALUES (3, 3, 1);
INSERT INTO `role_menu` VALUES (4, 4, 1);
INSERT INTO `role_menu` VALUES (5, 5, 1);


INSERT INTO `role_resource` VALUES (1, 1, 1);
INSERT INTO `role_resource` VALUES (2, 1, 2);
INSERT INTO `role_resource` VALUES (3, 1, 3);
INSERT INTO `role_resource` VALUES (4, 1, 4);
INSERT INTO `role_resource` VALUES (5, 1, 5);

