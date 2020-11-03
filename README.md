
# boying
<a href="#"><img src="https://img.shields.io/badge/qq号-1254931237-red.svg" alt="qq号"></a>   <a href="https://github.com/tongji4m3/boying-web" alt="boying-web"><img src="https://img.shields.io/badge/前端地址-boying_web-blue.svg"></a>   <a href="https://github.com/tongji4m3/boying-admin-web" alt="boying-admin-web"><img src="https://img.shields.io/badge/前端admin地址-boying admin web-yellow.svg"></a>   <a href="#License" alt="License"><img src="https://img.shields.io/github/license/tongji4m3/boying"></a>


# 开发规范

1. 统一在**develop**分支下进行提交
2. 每次提交前(最好在写代码前),先`pull`最新的版本,避免代码冲突
3. 每次提交说明修改了哪些地方
4. 遵循统一的代码风格

# 开发进度

> - [x]  数据库设计
> - [x]  开发环境搭建
> - [x]  后端用户模块
> - [x]  后端管理员权限管理模块
> - [ ]  后端管理员模块
> - [ ]  前端用户模块
> - [ ]  前端管理员模块
> - [ ]  数据爬虫批量导入
> - [ ]  项目自动化部署
> - [ ]  项目优化,如Redis集群加速,Mysql索引优化
> - [ ]  项目软工文档
> - [ ]  项目JavaEE文档
> - [ ]  项目技术说明文档(Github)
> - [ ]  项目功能完善与扩展
> - [ ]  项目扩展,如微服务,小程序

# 项目简介

**boying**是一个娱乐票务平台，包括**前台演出门票购买系统**及**后台管理系统**。

前台系统包括:用户模块,观影人模块,地址模块,演出模块,订单模块,评价模块

后台系统包括:权限管理,报表管理,用户管理,演出管理,场次管理,订单管理,评论管理

该项目为**boying**的后端实现,基于`SpringBoot`实现，并采用`Jenkins`自动化部署。 **boying**平台前端采用`Vue`开发,项目地址以及演示地址见下方。

# 项目地址
- 文档地址:
- 演出门票购买系统前端项目地址:[boying-web](https://github.com/tongji4m3/boying-web)
- 后台管理前端项目地址:[boying-admin-web](https://github.com/tongji4m3/boying-admin-web)
- 项目演示地址(演出门票购买界面):
- 项目演示地址(后台管理页面):
- 后端演出门票购买系统接口地址:http://47.103.203.188:8000/swagger-ui.html
- 后端后台管理系统接口地址:

# 项目介绍
## 项目结构
```
boying
    ├── boying-admin 后台管理及权限管理模块
    ├── boying-common 封装项目通用代码模块
    ├── boying-mbg MyBatisGenerator自动生成数据库操作代码
    ├── boying-security SpringSecurity认证与权限通用代码模块
    ├── boying-user 前台演出门票购买模块
    ├── docs docsify生成项目文档网站
    ├── document 存放一些资源文件以及项目说明文档
    ├── LICENSE 项目使用的开源许可证
    ├── pom.xml 整体项目的pom文件
    └── README.md 项目的README说明文档
```

## 模块介绍

+ [项目分模块构建说明](document\3.各模块说明文档\项目分模块构建说明.md)



## 业务逻辑介绍



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

![博影-用户逻辑模块](https://tongji4m3.oss-cn-beijing.aliyuncs.com/博影-用户逻辑模块.png)

#### 权限系统模块

 ![博影-权限系统](https://tongji4m3.oss-cn-beijing.aliyuncs.com/博影-权限系统.png)



### 用例图

![博影-用例图](https://tongji4m3.oss-cn-beijing.aliyuncs.com/博影-ER图.png)



### 数据库ER图

![博影-ER图](https://tongji4m3.oss-cn-beijing.aliyuncs.com/博影-用例图.jpg)





# 环境搭建

## 文档结构说明

```
document
    ├─0_未归档
    ├─1.SQL
    ├─2.资源
    ├─3.各模块说明文档
    ├─4.业务逻辑说明文
    ├─5.技术说明文档
    └─6.内存数据库文档
```

## 开发工具

| 工具          | 说明                   | 官网                                |
| ------------- | ---------------------- | ----------------------------------- |
| IntelliJ IDEA | java开发工具           | https://www.jetbrains.com/idea/     |
| Navicat       | 数据库设计工具         | http://www.formysql.com/xiazai.html |
| Typora        | Markdown编辑器         | https://typora.io/                  |
| ProcessOn     | 流程图绘制工具         | https://www.processon.com/          |
| XFTP 6        | 上传文件至阿里云服务器 | https://www.netsarang.com/en/xftp/  |


## 搭建步骤

### Windows

### Linux自动化部署

# License

boying is [MIT License](https://github.com/tongji4m3/community/blob/master/LICENSE)

Copyright (c) 2020 tongji4m3