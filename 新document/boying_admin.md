后台管理系统包含报表管理、用户管理、目录管理、演出管理、订单管理、活动管理、权限管理模块。

菜单管理用于控制前端菜单的显示和隐藏，资源管理用来控制后端接口的访问权限。

| 技术             | 版本  | 说明           |
| ---------------- | ----- | -------------- |
| Spring Boot      | 2.3.0 | 容器+MVC框架   |
| Spring Security  | 5.1.4 | 认证和授权框架 |
| MyBatis          | 3.4.6 | ORM框架        |
| MyBatisGenerator | 1.3.3 | 数据层代码生成 |
| Redis            | 5.0   | 分布式缓存     |
| JWT              | 0.9.0 | JWT登录支持    |

整合SpringSecurity和JWT实现后台用户的登录和授权功能



SpringSecurity是一个强大的可高度定制的认证和授权框架，对于Spring应用来说它是一套Web安全标准。SpringSecurity注重于为Java应用提供认证和授权功能，像所有的Spring项目一样，它对自定义需求具有强大的扩展性。



JWT是JSON WEB TOKEN的缩写，它是基于 RFC 7519 标准定义的一种可以安全传输的的JSON对象，由于使用了数字签名，所以是可信任和安全的。

### JWT的组成

- JWT token的格式：header.payload.signature

- header中用于存放签名的生成算法

    ```json
    {"alg": "HS512"}Copy to clipboardErrorCopied
    ```

- payload中用于存放用户名、token的生成时间和过期时间

    ```json
    {"sub":"admin","created":1489079981393,"exp":1489684781}Copy to clipboardErrorCopied
    ```

- signature为以header和payload生成的签名，一旦header和payload被篡改，验证将失败

    ```java
    //secret为加密算法的密钥
    String signature = HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)Copy to clipboardErrorCopied
    ```

#### JWT实现认证和授权的原理

- 用户调用登录接口，登录成功后获取到JWT的token；
- 之后用户每次调用接口都在http的header中添加一个叫Authorization的头，值为JWT的token；
- 后台程序通过对Authorization头中信息的解码及数字签名校验来获取其中的用户信息，从而实现认证和授权。

mall项目实现方式是Spring Security，相当于把安全功能封装成了一个工具包`mall-security`，然后其他模块通过依赖该工具包来实现权限管理，比如`mall-admin`模块。





## 菜单管理

菜单主要是指管理后台左侧的菜单，管理功能可用于控制其隐藏显示及更换图片名称和排序，目前仅支持二级菜单。

- 查看菜单列表，可以控制隐藏显示及删除；
- 添加及编辑菜单，可以更改菜单的基本属性，不过只能添加前端项目`路由中定义`的菜单，并且`前端名称`要与前端项目中定义的`路由名称`一致；
- 菜单排序，给菜单设置排序后，菜单将按照设置的排序降序进行显示。



## 资源管理

所谓资源就是后台的接口，可以是单个接口，也可以是一系列接口的集合。这里我们使用了基于Ant的路径匹配，当后台用户访问某个接口时，如果这个后台用户分配了该资源就可以访问，否则无法访问。默认情况下，如果你没有对某个接口配置资源，则该资源直接允许访问。

- 查看资源列表，目前的资源是按控制器级别配置的，即一个控制器中所有的接口定义为一个资源，也可以配置到接口级别；
- 添加及编辑资源，这里我们添加了一个资源分类的概念，便于以后的资源分配；





## 角色管理

用于对后台用户角色进行管理，我们可以给角色分配指定的菜单和资源，这样被分配了角色的后台用户就可以访问这些菜单和资源了。





springSecurity本质上是一个过滤器链

认证、授权

![image-20210214161505848](https://tongji2021.oss-cn-shanghai.aliyuncs.com/img/image-20210214161505848.png)

## @PreAuthorize

注解适合于进入方法前的权限校验

```
@EnableGlobalMethodSecurity(prePostEnabled = true)
```

@PreAuthorize("hasAuthority('pms:product:create')")

# FilterSecurityInterceptor.class

是一个方法级的权限过滤器，基本位于过滤器的最底部

```
implements Filter 
```

```
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    FilterInvocation fi = new FilterInvocation(request, response, chain);
    this.invoke(fi);
}
```

```
public void invoke(FilterInvocation fi) throws IOException, ServletException {
    if (fi.getRequest() != null && fi.getRequest().getAttribute("__spring_security_filterSecurityInterceptor_filterApplied") != null && this.observeOncePerRequest) {
        fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
    } else {
        if (fi.getRequest() != null && this.observeOncePerRequest) {
            fi.getRequest().setAttribute("__spring_security_filterSecurityInterceptor_filterApplied", Boolean.TRUE);
        }

//执行之前的过滤器
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
        }

        super.afterInvocation(token, (Object)null);
    }

}
```

# UsernamePasswordAuthenticationFilter.class

根据用户名、密码进行校验

```java
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (this.postOnly && !request.getMethod().equals("POST")) {
        throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    } else {
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
```

# UserDetailsService

继承他实现用户名密码

# PasswordEncoder