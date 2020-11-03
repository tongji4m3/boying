---
title: Github常用命令与使用
date: 2020-07-25
author: tongji4m3
top: false
cover: false
coverImg: /images/1.jpg
toc: true
mathjax: false
summary: 一些常用工具的笔记,主要写了Github常用命令与使用
categories: 常用工具
tags:

  - 工具
  - Github
---



# Github基础知识

## 分支策略

### master(稳定版)

只用于发布重大版本,所有提供给用户使用的正式版本,都在该主分支上发布

### develop(开发版)

日常开发,对外发布时才与`master`合并

### 临时分支

临时分支使用后应该删除,使得代码库常设分支始终只有`master`与`develop`

+ 功能分支:从develop中分出的分支。为了开发某种特定功能而存在,开发完并入develop。命名为`feature/feature1`
+ 预发布分支:在develop合并到master之前，为了预发布版本进行测试而存在。预发布结束后，必须合并进develop和master。命名为：`release/release1`
+ 修补bug分支:从master分支分出来的，修补结束后合并进develop和master两个分支中。命名为：`hotfix/fixbug1`

## idea中Git的使用

1. `git checkout -b login` 开发时创建一个新分支

2. `git checkout master`切换分支

4. `git branch`查看分支

4. `git status`查看状态

6. `git add . `将所有文件加入暂存区

7. `git commit -m 描述信息 ` 提交到本地仓库

7. `git push`推送到`Github`上

8. `git pull`是`git fetch`后跟`git merge FETCH_HEAD`的缩写。取回远程主机某个分支的更新，再与本地的指定分支

​	

# Github优雅指南

## 基础

+ 可以用 `GitHub`的 `Pin `功能把自己认为写得好的项目钉在自己的首页
+ 尽量将 `README `文档写的规范。应该有一份高质量的文档。
+ 在`README `文档里涵盖**持续集成**、**测试覆盖率**、**代码质量**等
+ 项目的代码结构要清晰，代码抽象合理，文件名、变量名等命名合理易读
+ 创建仓库名,例如:`tongji4m3/tongji4m3`将会显示在`Github`首页

## READEME文档

最好达到以下要求:

+ 项目目录

+ 徽章

+ 项目简介， 添加一个封面或横幅图片，`1280x650px`，项目的功能特点，项目 Logo，项目GIf截图

+ 项目说明文档

+ 项目部署地址

+ 项目的组织结构，代码规范

+ 开源许可证

+ 项目首页的`about`页面

+ 项目只包含`master`,`develop`分支

+ 每次更新提交`Summary`写具体详细

+ 项目详细的环境配置说明，如何安装部署

	

## 徽章

徽章（Badge）是对项目进行标记和说明的，不仅简洁美观清晰易读，而且可以自动更新相关数据。

徽章生成地址：[shields.io](https://shields.io/)

常用的徽标主要有持续集成状态、项目版本信息、代码测试覆盖率、项目支持平台、项目语言、代码分析等。

### 自定义徽章

模板：`https://img.shields.io/badge/徽标标题-徽标内容-徽标颜色.svg`

## 持续集成

Continuous Integration，简称CI，意思是，在一个项目中，任何人对代码库的任何改动，都会触发CI服务器自动对项目进行构建，自动运行测试，甚至自动部署到测试环境。这样做的好处就是，随时发现问题，随时修复。因为修复问题的成本随着时间的推移而增长，越早发现，修复成本越低。

Travis CI是在线托管的CI服务，用Travis来进行持续集成，不需要自己搭服务器

直接用你的GitHub账号登录Travis CI的网站：https://travis-ci.org/

第一次登录时，授权给Travis访问你的GitHub代码库，然后，把需要CI的代码库选上：

默认情况下，Travis会在代码push时收到GitHub通知，然后自动获取最新代码，进行CI。

但是我们还是需要编写一个`.travis.yml`文件来告诉Travis一些项目信息，比如用的Java还是Python，用的JDK 8还是JDK 6，等等。

假设我们用的Java和JDK 8，这个`.travis.yml`就这么写：

```
language: java

jdk:
  - oraclejdk8
```

然后，放到代码库的根目录下



stars:>100000



## .gitignore配置

```
#忽略所有.svn目录
.svn/
#忽略所有target目录
target/
#忽略所有.idea目录
.idea/
#忽略所有.iml文件
*.iml
```



```
git rm -r --cached .
git add .
git commit -m "update .gitignore"
git push
```



## 添加开源许可

MIT 最自由,任何人都可以售卖我的软件，甚至可以用我的名字促销

BSD 和 Apache 协议也很自由,不允许用作者本人名义促销和保护作者版权

GPL 可以说最霸道,对代码的修改部分也必须是 GPL 的，同时基于 GPL 代码而开发的代码也必须按照 GPL 发布

## 生成项目组织结构图

tree  >> 生成的位置

例如:`tree  >> C:\Users\12549\Desktop\tree.txt`

## 生成表格

**快捷键** :`ctrl T`

```
|  表头   | 表头  |
|  ----  | ----  |
| 单元格  | 单元格 |
| 单元格  | 单元格 |
```



## 文档编写

设置页面开启 **GitHub Pages** 功能并选择 `master branch /docs folder` 选项

```java
npm i docsify-cli -g  //安装
docsify init ./docs   //初始化项目
docsify serve docs  //本地预览项目
```

**[docsify官方教程](https://docsify.js.org/#/zh-cn/)**





