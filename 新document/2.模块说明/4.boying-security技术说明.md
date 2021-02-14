# 背景知识

## 权限系统

权限系统就是：明确操作人员可在平台内能做什么。即什么样的人，可以做什么样的事，这并不难理解，我们的用户是所有可以登录该平台的人员。

“用户管理”、“角色管理”和“权限管理”

RBAC即：权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。

用户与角色一一对应，一个用户对应一个角色；同一个角色可对应多个后台操作页面

RBAC结构可通过“角色”搭建用户与权限之间的关系，可在创建角色时绑定相应权限，再匹配到用户，可提高整体的效率以及稳定性。

## 认证

用户认证通过后，将用户信息保存在会话中，如token等。基于JWT令牌，会话通过token保存。认证是保护用户身份的合法性。

## 授权

更细粒度的对隐私数据进行划分。授权是用户认证通过根据用户的权限来控制用户访问资源的过程。



## RBAC

“用户-角色-权限”管理是 **“访问控制”** 的一种实现方式，更为专业的叫法为 **RBAC**（Role-Based Access Control），即基于角色的权限访问控制。

权限的三种粒度：**菜单权限、操作/功能权限、数据权限**。

## 用户管理

- **用户信息：** 显示用户的基本信息（昵称、联系方式、角色、部门等）
- **组织架构：** 显示、配置（增删改）组织架构，一般为树结构
- **用户操作：** 为用户分配角色（多对多）、组织架构（多对多），删除用户
- **用户黑白名单：** 对特殊用户进行特别控制

## 角色管理

- **角色信息：** 显示角色的基本信息（名称、权限等）
- **角色操作：** 根据需要增删角色、为角色分配权限（多对多，按不同粒度分配，并实现权限的互斥性检验）

## 权限管理

权限一般有如下三种粒度：

- **菜单权限：** 访问某一菜单（页面、路由）的权限
- **操作/功能权限：** 进行某一操作或使用某一功能的权限（如删除用户的权限）
- **数据权限：** 访问某种数据（表、字段）的权限，或对可操作数据量的控制

## 技术要点

- 用户、角色、权限、组织架构表结构设计
- 用户身份验证、授权、会话管理，用户信息的加密存储
- 不同粒度权限的具体实现

## 权限管理

- 菜单管理：可以实现对后台管理系统左侧菜单的管理，支持更换图标、更换名称、控制菜单显示和排序；
- 资源管理：实现了基于访问路径的后台动态权限控制，控制的权限可以精确到接口级别；
- 角色管理：可以自定义角色，并为角色分配菜单和资源；
- 后台用户管理：可以对后台用户进行管理并分配角色，支持分配多个角色。





# SecurityConfig

对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑,其他的所有类都是为它服务的

## BoyingSecurityConfig

实现了对`SecurityConfig`的扩展,定义了查询用户的逻辑

```java
/**
 * mall-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BoyingSecurityConfig extends SecurityConfig
{

    @Autowired
    private UserService userService;

    /**
     * 注解@Bean放在方法上，产生一个Bean并且交给Spring容器管理
     * 定义用户信息
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService()
    {
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }
}
```

## RestfulAccessDeniedHandler

处理`SecurityConfig`中没有权限访问时自定义返回结果

## RestAuthenticationEntryPoint

处理`SecurityConfig`中未登录或登录过期，自定义返回结果

## JwtAuthenticationTokenFilter

处理`SecurityConfig`,在用户名和密码校验前添加的过滤器，如果请求中有jwt的token且有效，会取出token中的用户名，然后调用SpringSecurity的API进行登录操作。

## IgnoreUrlsConfig

处理`SecurityConfig`中的白名单,用于不需要保护的资源路径允许访问

```java
/**
 * 用于配置白名单资源路径
 * 将大量的参数配置在 application.yml 文件中，
 * 通过 @ConfigurationProperties 注解，我们可以方便的获取这些参数值
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig
{

    private List<String> urls = new ArrayList<>();

}
```


## JwtTokenUtil

 JwtToken生成的工具类

token有一部分储存了**用户名、创建时间、过期时间**,其中创建时手动传入了用户名,当前时间。然后程序根据配置文件中的**token有效时间**+当前时间得到过期时间。

```java


//用于根据登录用户信息生成token userDetails.getUsername() new Date()
generateToken(UserDetails userDetails) 
//从token中获取登录用户的信息
getUserNameFromToken(String token)
//判断token是否还有效,判断条件为用户名是否还是该用户&&token是否过期
validateToken(String token, UserDetails userDetails)
    
//刷新token，当原来的token没过期，且30分钟内没刷新时可以。操作就是把时间更新，重新生成token
refreshHeadToken(String oldToken)
```



## UserDetailsService

SpringSecurity定义的核心接口，用于根据用户名获取用户信息

```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
}
```

```java
/**
 * boying-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BoyingSecurityConfig extends SecurityConfig
{

    @Autowired
    private UserService userService;

    /**
     * 注解@Bean放在方法上，产生一个Bean并且交给Spring容器管理
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService()
    {
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }
}
```

## UserDetails

SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限）

```java
public interface UserDetails extends Serializable 
{
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
```

在boying-user模块下，需要一个`BoyingUserDetails`实现`UserDetails`接口
```java
/**
 * SpringSecurity需要的用户详情
 */
public class BoyingUserDetails implements UserDetails
{
    private User user;

    public BoyingUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    //账号未过期
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    //账号未被锁定
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    //凭证未过期
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return user.getStatus();
    }

    public User getUser()
    {
        return user;
    }
}
```



# 基于路径的动态权限控制

修改Spring Security的配置类SecurityConfig，当有动态权限业务类时在FilterSecurityInterceptor过滤器前添加我们的动态权限过滤器。这里在创建动态权限相关对象时，还使用了@ConditionalOnBean这个注解，当没有动态权限业务类时就不会创建动态权限相关对象，实现了有动态权限控制和没有这两种情况的兼容。其他模块需要动态权限控制时，只要创建一个DynamicSecurityService对象就行

## DynamicSecurityFilter
动态权限过滤器，用于实现基于路径的动态权限过滤。对OPTIONS请求直接放行（防止跨域问题）。对白名单请求直接放行，所有的鉴权操作都会在`super.beforeInvocation(fi)`中进行

## DynamicSecurityMetadataSource
自己实现SecurityMetadataSource接口的getAttributes方法，用于获取当前访问路径所需资源。
		后台资源规则被缓存在了一个Map对象之中

`private static Map<String, ConfigAttribute> configAttributeMap = null;`，

所以当后台资源发生变化时，我们需要清空缓存的数据，当修改后台资源时，需要调用clearDataSource方法来清空缓存的数据。

通过`private DynamicSecurityService dynamicSecurityService;`获取后台资源规则

## DynamicAccessDecisionManager

实现AccessDecisionManager接口来实现权限校验，对于没有配置资源的接口我们直接允许访问，对于配置了资源的接口，我们把访问所需资源和用户拥有的资源进行比对，如果匹配则允许访问。

## DynamicSecurityService

动态权限相关业务类，自定义的一个动态权限业务接口，其主要用于加载所有的后台资源规则。

# AOP优化

给获取用户信息和获取用户的资源信息这两个操作添加缓存操作，当我们修改用户信息和资源信息时都需要删除缓存中的数据

## RedisCacheAspect

Redis缓存切面，防止Redis宕机影响正常业务逻辑

因为作为缓存，我们所希望的是，如果Redis宕机了，我们的业务逻辑不会有影响。要保证缓存业务类中的方法执行不影响正常的业务逻辑，就需要在所有方法中添加`try catch`逻辑。使用AOP，我们可以在一个地方写上`try catch`逻辑，然后应用到所有方法上去。

注意只对命名为`xxxCacheService`的起作用

## CacheException

自定义注解，有该注解的缓存方法会抛出异常。例如验证码存储，如果我们的Redis宕机了，我们的验证码存储接口需要的是报错，而不是返回执行成功。

# 其他模块

要实现安全框架，只需要实现`SecurityConfig`与`UserDetails`即可，其中如果要实现基于路径的动态权限控制，只要再包含以下方法实现即可

```java
@Bean
public DynamicSecurityService dynamicSecurityService() 
{
}
```