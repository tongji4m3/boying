
# boying
<a href="#"><img src="https://img.shields.io/badge/qq号-1254931237-red.svg" alt="qq号"></a>   <a href="https://github.com/tongji4m3/boying-web" alt="boying-web"><img src="https://img.shields.io/badge/前端地址-boying_web-blue.svg"></a>   <a href="https://github.com/tongji4m3/boying-admin-web" alt="boying-admin-web"><img src="https://img.shields.io/badge/前端admin地址-boying admin web-yellow.svg"></a>   <a href="#License" alt="License"><img src="https://img.shields.io/github/license/tongji4m3/boying"></a>

# 开发进度

> - [x]  数据库设计
> - [x]  开发环境搭建
> - [x]  后端用户模块
> - [x]  后端管理员权限管理模块
> - [x]  后端管理员模块
> - [x]  前端用户模块
> - [x]  前端管理员模块
> - [x]  数据爬虫批量导入
> - [x]  项目软工文档
> - [x]  项目JavaEE文档
> - [x]  项目技术说明文档(Github)
> - [ ]  项目自动化部署
> - [ ]  项目优化，如Redis集群加速，Mysql索引优化


# 项目简介

**boying**是一个娱乐票务平台，包括**前台演出门票购买系统**及**后台管理系统**。

前台系统包括：用户模块，目录模块，演出模块，订单模块

后台系统包括：权限管理，报表管理，用户管理，演出管理，场次管理，订单管理

该项目为**boying**的后端实现，基于`SpringBoot`实现。 **boying**平台前端采用`Vue`开发

# 项目地址
- 演出门票购买系统前端项目地址:[boying-web](https://github.com/tongji4m3/boying-web)
- 后台管理前端项目地址:[boying-admin-web](https://github.com/tongji4m3/boying-admin-web)
- 项目演示地址(演出门票购买界面):http://47.103.203.188:8080/boying-user/index.html
- 项目演示地址(后台管理页面):http://47.103.203.188:8080/boying-admin/index.html
- 后端演出门票购买系统接口地址:http://47.103.203.188:8000/swagger-ui.html
- 后端后台管理系统接口地址:http://47.103.203.188:7000/swagger-ui.html

# 项目介绍
## 项目结构
```
boying
    ├── admin 后台管理及权限管理模块
    ├── user 前台演出门票购买模块
    ├── technical_documents 项目的技术文档 
    ├── document 存放一些资源文件以及项目说明文档
    ├── LICENSE 项目使用的开源许可证
    └── README.md 项目的README说明文档
```

## 模块介绍

### 依赖关系

+ boying-common：封装通用的一些组件
+ boying-mbg：依赖boying-common，自动生成SQL代码，包含增删改查
+ boying-security：依赖boying-common，安全管理模块，负责对user，admin
+ boying-admin：依赖boying-mbg，boying-security
+ boying-user：依赖boying-mbg，boying-security

## 开发工具

| 工具          | 说明                   | 官网                                |
| ------------- | ---------------------- | ----------------------------------- |
| IntelliJ IDEA | java开发工具           | https://www.jetbrains.com/idea/     |
| Navicat       | 数据库设计工具         | http://www.formysql.com/xiazai.html |
| Typora        | Markdown编辑器         | https://typora.io/                  |
| ProcessOn     | 流程图绘制工具         | https://www.processon.com/          |
| XFTP 6        | 上传文件至阿里云服务器 | https://www.netsarang.com/en/xftp/  |

## 技术选型

| 技术             | 版本    | 说明                |
| ---------------- | ------- | ------------------- |
| JDK              | 1.8     | Java开发工具包JDK   |
| Spring Boot      | 2.3.0   | 容器+MVC框架        |
| Spring Security  | 5.1.4   | 认证和授权框架      |
| MyBatis          | 3.4.6   | ORM框架             |
| MyBatisGenerator | 1.3.3   | 数据层代码生成      |
| PageHelper       | 5.1.8   | MyBatis物理分页插件 |
| Swagger-UI       | 2.9.2   | 文档生产工具        |
| Redis            | 5.0     | 分布式缓存          |
| Docker           | 18.09.0 | 应用容器引擎        |
| Druid            | 1.1.10  | 数据库连接池        |
| JWT              | 0.9.0   | JWT登录支持         |
| Lombok           | 1.18.6  | 简化对象封装工具    |
| MySQL            | 5.7.30  | 关系型数据库        |

## 架构图
### 数据库架构图
#### 业务逻辑模块

![image-20210505103048998](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/image-20210505103048998.png)

#### 权限系统模块

![image-20210505103123041](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/image-20210505103123041.png)



# 环境配置

## 本地搭建步骤(Windows)

### 前端

```
//默认连接的是远程后端与远程数据库
进入 boying-web 或者 boying-admin-web 项目根目录
在命令行下输入如下指令:
npm install
npm run serve
访问:http://localhost:8080/
//如果同时打开boying-web与boying-admin-web会出现端口冲突，请自行配置
//如果要连接自己的后端，请在src\assets\api.js文件中,更改 API_URL: "http://自己的ip地址:自己的端口号"
```

### 后端

```
//默认连接的是远程Mysql和Redis
用Idea打开boying项目
在右侧的Maven中选择Lifecycle中的package,安装相关依赖.随后选择Reload All Maven Projects
若要启动boying-user后端,则运行boying-user子系统的src\main\java\com\tongji\boying\BoyingUserApplication.java中的main方法
启动成功后,则可以访问swagger接口:http://localhost:8000/swagger-ui.html
若要启动boying-admin后端,则运行boying-admin子系统的src\main\java\com\tongji\boying\BoyingAdminApplication.java
启动成功后,则可以访问swagger接口:http://localhost:7000/swagger-ui.html
```

## 数据库搭建步骤

数据库的搭建依赖与docker，请先根据网上教程安装好docker

docker常用命令:

```java
启动        systemctl start docker

重启docker服务   systemctl restart  docker

停止所有的容器 docker stop $(docker ps -aq)

删除所有容器 docker rm $(docker ps -aq)
```



### Mysql搭建

```
docker pull mysql:5.7
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:5.7
```



### Redis搭建

```
docker pull redis
docker run -d --name redis -p 6379:6379 redis --requirepass "redis"
```



## 后端搭建步骤

要更换数据库,则在boying项目中的boying-user，boying-admin子系统中的src\main\resources\application-dev.yml与src\main\resources\application-prod.yml中更换mysql与redis的地址

需要在服务器安装好java环境（JDK8）

需要先通过maven把boying整个项目install，然后把boying\boying-user\target\boying-user.jar与boying\boying-user\target\boying-admin.jar上传到服务器上

### 后端用户模块

```
netstat -tunlp |grep 8000

kill -s 9 30973(根据上一步查询出来的进程id)

nohup java -jar boying-user.jar 

随后可以在http://自己的IP地址:8000/swagger-ui.html 访问用户模块swagger接口
```

### 后端管理员模块

```
netstat -tunlp |grep 7000

kill -s 9 30973(根据上一步查询出来的进程id)

nohup java -jar boying-admin.jar 

随后可以在http://自己的IP地址:7000/swagger-ui.html 访问管理员模块swagger接口
```

## 前端搭建步骤

需要在boying-user、boying-admin的根路径下使用npm run build命令

随后把根路径下的对应dist文件上传到服务器/root/webapps路径下

```
docker pull tomcat
docker run --name tomcat -p 8080:8080 -v /root/webapps:/usr/local/tomcat/webapps -d tomcat 
当把dist文件放入/root/webapp并修改名为boying-user、boying-admin即可访问
访问路径：
http://自己IP地址:8080/boying-user
http://自己IP地址:8080/boying-admin
```



## 常用操作

### 清空redis缓存

redis缓存仍然存在,导致修改了数据库,在远程API查询数据仍然没有改变

```
docker exec -it redis redis-cli -a "redis"
flushall
```

# License

boying is [MIT License](https://github.com/tongji4m3/community/blob/master/LICENSE)

Copyright (c) 2020 tongji4m3