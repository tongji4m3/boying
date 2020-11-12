
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