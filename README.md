
# boying
<a href="#"><img src="https://img.shields.io/badge/qq号-1254931237-red.svg" alt="qq号"></a>   <a href="https://github.com/tongji4m3/boying-web" alt="boying-web"><img src="https://img.shields.io/badge/前端地址-boying_web-blue.svg"></a>   <a href="https://github.com/tongji4m3/boying-admin-web" alt="boying-admin-web"><img src="https://img.shields.io/badge/前端admin地址-boying admin web-yellow.svg"></a>   <a href="#License" alt="License"><img src="https://img.shields.io/github/license/tongji4m3/boying"></a>


# 开发规范

1. 统一在**develop**分支下进行提交
2. 每次提交前(最好在写代码前),先`pull`最新的版本,避免代码冲突
3. 每次提交说明修改了哪些地方
4. 遵循统一的代码风格

## 开发进度

> - [x]  数据库设计
> - [x]  开发环境搭建
> - [x]  后端用户模块
> - [ ]  后端管理员模块
> - [ ]  前端用户模块
> - [ ]  前端管理员模块
> - [ ]  数据爬虫批量导入
> - [ ]  项目自动化部署
> - [ ]  项目优化,如Redis集群加速,Mysql索引优化
> - [ ]  项目软工文档
> - [ ]  项目JavaEE文档
> - [ ]  项目技术说明文档(Github)
> - [ ]  项目功能扩展

## 项目简介

**boying**是一个娱乐票务平台，包括前台演唱会门票购买系统及后台权限管理系统。

该项目为**boying**的后端实现,基于`SpringBoot`实现，并采用`Jenkins`自动化部署。 **boying**平台前端采用`Vue`开发,项目地址以及演示地址见下方。

## 项目地址
- 文档地址:
- 前端用户购买项目地址:[boying-web](https://github.com/tongji4m3/boying-web)
- 前端后台管理项目地址:[boying-admin-web](https://github.com/tongji4m3/boying-admin-web)
- 项目演示地址(用户购买界面):
- 项目演示地址(后台管理页面):
- 后端接口地址:http://47.103.203.188:8000/swagger-ui.html

## 项目介绍
实现技术,包含的模板
### 项目结构
树形图
### 技术选型



| 技术             | 版本    | 说明                |
| ---------------- | ------- | ------------------- |
| Spring Boot      | 2.3.0   | 容器+MVC框架        |
| Spring Security  | 5.1.4   | 认证和授权框架      |
| MyBatis          | 3.4.6   | ORM框架             |
| MyBatisGenerator | 1.3.3   | 数据层代码生成      |
| PageHelper       | 5.1.8   | MyBatis物理分页插件 |
| Swagger-UI       | 2.9.2   | 文档生产工具        |
| Redis            | 5.0     | 分布式缓存          |
| Docker           | 18.09.0 | 应用容器引擎        |
| Druid            | 1.1.10  | 数据库连接池        |
| OSS              | 2.5.0   | 对象存储            |
| JWT              | 0.9.0   | JWT登录支持         |
| Lombok           | 1.18.6  | 简化对象封装工具    |
| MySQL            | 5.7.30  | 关系型数据库        |

### 架构图
#### 系统架构图
#### 业务架构图
#### 模块介绍



## 环境搭建
### 开发工具
### 开发环境
### 搭建步骤



## License

boying is [MIT License](https://github.com/tongji4m3/community/blob/master/LICENSE)

Copyright (c) 2020 tongji4m3