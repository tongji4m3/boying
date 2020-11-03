# config

## AdminUserDetails

SpringSecurity需要的用户详情

### getAuthorities()

返回当前用户拥有的资源权限,权限命名为:

`resource.getResourceId() + ":" + resource.getName()`

### isEnabled()

根据status标识该账号是否可用,`return admin.getStatus();`

## BoyingSecurityConfig

### userDetailsService()

根据用户名获取登录用户信息

### dynamicSecurityService()

要实现动态路径控制则必须实现该方法,注入`Bean`,目的是加载所有的资源,这通过一个Map实现,存储资源路径url,资源路径值(自定义为了 id:name )



## GlobalCorsConfig

全局跨域相关配置

## MyBatisConfig

MyBatis相关配置，控制dao或者mapper文件扫描位置

## SwaggerConfig

对boying-common的swagger相关组件进行自定义配置。Swagger API文档相关配置，主要是api扫描位置以及标题等

