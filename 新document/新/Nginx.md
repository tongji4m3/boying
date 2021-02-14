# 介绍

+ 高性能的HTTP和反向代理服务器，占用内存小，并发能力强
+ 可以作为静态页面的web服务器
+ 注重性能，能经受高负载，支持超过50000个并发连接数



# 功能

# 反向代理

## 正向代理

在客户端（浏览器）配置代理服务器，依赖代理服务器来访问真正的服务器

![img](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/_CopyPix_1612261754_4.png)

## 反向代理

+ 客户对代理无感知，客户端不需要做如何配置
+ 反向代理服务器和目标服务器对外就是一个服务器
+ 暴露的是代理服务器地址，隐藏了真实服务器IP地址

![img](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/_CopyPix_1612262060_5.png)

# 负载均衡

通过反向代理服务器，把请求通过Nginx平均分配到不同的服务器中

![img](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/_CopyPix_1612262371_6.png)

# 动静结合

加快网站的解析速度，把动态页面和静态页面由不同的服务器来解析，加快解析速度，降低单个服务器的压力

![img](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/_CopyPix_1612262513_7.png)

# epoll多路复用

## 分类

+ java bio模型，阻塞进程式
+ select多路复用 变更触发轮询查找，有1024数量上限
+ 

# master worker进程模型

# 协程机制