---
title: Java基础--JavaWeb
date: 2020-03-25
author: tongji4m3
top: false
cover: false
coverImg: /images/1.jpg
toc: true
mathjax: false
summary: Java基础知识之JavaWeb,待完善,之后再看的时候会继续补充。
categories: Java基础
tags:

  - Java基础
  - JavaWeb
---

1. `BeanPropertyRowMapper<User>(User.class)`封装必须数据库和类的字段名字一样，不然会封装为null，且如果没有匹配会报错
2. session和直接存储attribute的不同，作用域不一样。request.setAttribute()是只在此action的下一个forward需要使用的时候使用；session.setAttribute 作用域是整个会话期间，在所有的页面都使用这些数据的时候使用。
3. 导入的jar包直接放在web-INF的lib目录下，不要再分细的包，不然不行
4. maven管理jar包，依赖的jar直接添加在已经给的dependences里面，不然不行
5. 直接在右边maven里面，toncat:run就行
6. 加scope不然包冲突
7. 设置jdk，不然可能用不了，继续在pom插件，添加了模板jdk_use，可以直接使用了
8. 还要设置tomcat7(设置了快捷键tomcat7)，不然跑不了，然后运行 tomcat7:run
9. jedis操作的时候，要开启redis的server和client
10. 自己的服务器安装了mysql之后,得再阿里云开放3306端口
11. JDBCUtils.class.getResourceAsStream,无论如何都找到了
12. mysql设置编码,插入中文
13. 新建maven项目,在pom.xml里面配置jdk和tomcat7,jar包配置,试跑一下
14. 做好大概的思维导图,大致明确逻辑,建好层级目录
15. 先放入几个util类,新建数据库,实现User,Userdao,并测试
16. 导入jquery,css,boostrap等
17. 写注册页面的基本表单
18. 正则表达式简单校验输入内容
19. 不支持发行版本5 settings中找到java compiler 将右边改成jdk9,直接快捷键jdk9
20. oracle不支持:mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Dfile=D:\Oracle_10g_10.2.0.4_JDBC_ojdbc14.jar
21. idea静态资源热部署,[https://bbs.csdn.net/topics/392365951?list=9345374](https://bbs.csdn.net/topics/392365951?list=9345374)

热部署后,运行,刷新的时候按ctrl+F9就行了

22. ```
	#mybatis版本问题,不加这个不会自动扫描包
	mybatis.type-aliases-package=com.tongji
	```

23. 有时pom不主动下依赖,就maven-clean再maven-install

24. 还报错就reload项目

25. springboot热启动:ctrl+F9

26. BeanPropertyRowMapper<User>(User.class)封装必须数据库和类的字段名字一样，不然会封装为null，且如果没有匹配会报错

27. session和直接存储attribute的不同，作用域不一样。request.setAttribute()是只在此action的下一个forward需要使用的时候使用；session.setAttribute 作用域是整个会话期间，在所有的页面都使用这些数据的时候使用。

28. 导入的jar包直接放在web-INF的lib目录下，不要再分细的包，不然不行

29. maven管理jar包，依赖的jar直接添加在已经给的dependences里面，不然不行

30. 直接在右边maven里面，toncat:run就行

31. 加scope不然包冲突

32. 设置jdk，不然可能用不了，继续在pom插件，添加了模板jdk_use，可以直接使用了

33. 还要设置tomcat7(设置了快捷键tomcat7)，不然跑不了，然后运行 tomcat7:run

34. jedis操作的时候，要开启redis的server和client

35. 自己的服务器安装了mysql之后,得再阿里云开放3306端口

36. JDBCUtils.class.getResourceAsStream,无论如何都找到了

37. mysql设置编码,插入中文

38. 新建maven项目,在pom.xml里面配置jdk和tomcat7,jar包配置,试跑一下

39. 做好大概的思维导图,大致明确逻辑,建好层级目录

40. 先放入几个util类,新建数据库,实现User,Userdao,并测试

41. 导入jquery,css,boostrap等

42. 写注册页面的基本表单

43. 正则表达式简单校验输入内容

44. settings code templates other web servlet Annotated给servlet加上doPost

45. mysql安装:设置用户名,密码,然后配置环境变量:

	> 环境变量-系统变量-添加 C:\Program Files\MySQL\MySQL Server 5.5\bin

46. maven镜像: C:\Users\12549\.m2 下新建settings.xml文件,复制其他地方的内容进去,并且在mirrors标签下添加:

	```
	<mirror>
	      <id>nexus-aliyun</id>
	      <mirrorOf>central</mirrorOf>
	      <name>Nexus aliyun</name>
	      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
	</mirror>
	
	```

	