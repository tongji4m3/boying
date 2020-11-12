# application.yml

## 多环境动态切换yml配置

在实际的的开发中，对于一个工程，经常会有多种环境配置，例如开发环境、测试环境、生产环境等。在不同的环境下，配置有可能是不一样的，比如接口地址、数据库连接配置等。为了避免频繁的修改配置文件，我们想要简便地切换各种环境配置。好在SpringBoot提供了这样的功能，可以很方便地切换不同场景下的配置。
	
对开发配置和生产环境做了配置。上面的配置是公共配置，下面我们分别配置了开发和生产的配置。`spring.profiles`表示配置的名称，`spring.profiles.active`表示要激活的环境，值和要切换的`spring.profiles`名称一致。默认激活的就是dev开发配置。
	
如果`spring.profiles.active`没有指定值，那么只会加载通用的配置。
	
工程打成jar包后，我们可以在运行的时候对配置进行选择，而不需要每次打包前都手动去修改`spring.profiles.active`的值。
	
例如在生产环境，我们可以使用release配置执行jar包`java -jar xxx.jar --spring.profiles.active=release`

如若配置:

```xml
spring:
  profiles:
    active: dev #默认为开发环境
```

会启动:`application.yml`与`application-dev.yml`

# config

## BoyingSecurityConfig

boying-security模块相关配置,在里面为UserDetailsService定义一个适用与boying-user组件的获取登录用户信息的方法。而且不添加基于路径的动态权限控制

## GlobalCorsConfig

全局跨域相关配置

## JacksonConfig

Jackson相关配置类,使得json不返回null的字段

## MyBatisConfig

MyBatis相关配置，控制dao或者mapper文件扫描位置

## SwaggerConfig

对boying-common的swagger相关组件进行自定义配置。Swagger API文档相关配置，主要是api扫描位置以及标题等