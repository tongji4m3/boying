# boying-common目录结构

```
src
    └─main
        ├─java
        │  └─com
        │      └─tongji
        │          └─boying
        │              └─common
        │                  ├─api
        │                  │      CommonPage.java
        │                  │      CommonResult.java
        │                  │      IErrorCode.java
        │                  │      ResultCode.java
        │                  │
        │                  ├─config
        │                  │      BaseRedisConfig.java
        │                  │      BaseSwaggerConfig.java
        │                  │
        │                  ├─domain
        │                  │      SwaggerProperties.java
        │                  │      WebLog.java
        │                  │
        │                  ├─exception
        │                  │      ApiException.java
        │                  │      Asserts.java
        │                  │      GlobalExceptionHandler.java
        │                  │
        │                  ├─log
        │                  │      WebLogAspect.java
        │                  │
        │                  └─service
        │                      │  RedisService.java
        │                      │
        │                      └─impl
        │                              RedisServiceImpl.java
        │
        └─resources
                logback-spring.xml
```



# 接口访问日志

组合使用slf4j与logback搭建日志框架。

原因是logback直接实现了slf4j的接口，不需要额外引入适配器，不消耗内存和计算开销。而且使用方便，是当下最流行的日志框架。而`Spring boot`默认支持的`slf4j`+`logback`的日志框架。

实现结果是当用户访问到对应的API时就会在程序控制台打印相关日志信息。可以直观的看到哪些接口被用户访问，也可以根据日志信息轻松的debug

同时,产生的日志会记录在磁盘中存档,如果项目上线,可以随时查看日志文件,获取项目的情况

## 日志信息封装类WebLog

Controller层的日志封装类，用于封装需要记录的日志信息，包括操作描述、操作用户，操作时间、消耗时间、url、请求参数，IP地址和返回结果等信息。

## 切面类WebLogAspect

定义了一个日志切面，在环绕通知中获取日志需要的信息，并应用到controller层中所有的public方法中去。

```java
/**
 * 统一日志处理切面
 * 注解@Aspect:作用是把当前类标识为一个切面供容器读取,切面是通知和切点的结合，定义了何时、何地应用通知功能。
 * 注解@Order(1):Spring在加载Bean的时候，有用到order注解,此注解可操作于类、方法、字段，当作用在类时，值越小，则加载的优先级越高！
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect
{
//    使用指定的类WebLogAspect初始化日志对象，方便在日志输出的时候，可以打印出日志信息所属的类。
//    创建日志实例,定义成static final,速度快,节省空间,只能指向本logger
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);
//    定义换行符号
    private static final String LINE_SP = System.lineSeparator();

    /**    定义切入点表达式,切点定义了通知功能被应用的范围。比如日志切面的应用范围就是所有接口，即所有controller层的接口方法。
     *     execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数))
     *     com.tongji.boying包及其子包下关于controller包下的所有类中的public方法都应用切面里的通知
     */
    @Pointcut("execution(public * com.tongji.boying.controller.*.*(..))||execution(public * com.tongji.boying.*.controller.*.*(..))")
    public void webLog()
    {
    }

//    通知方法会在目标方法调用之前执行 里面的参数即为上述定义的webLog切入点表达式,防止重复编程
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable
    {
        LOGGER.info("========================================== Start =========================================="+LINE_SP);
    }

//    returning属性表明允许定义一个ret形参访问目标方法的返回值
    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable
    {
        LOGGER.info("=========================================== End ==========================================="+LINE_SP);
    }

//    环绕通知，可以在切入点前后织入代码，并且可以自由的控制何时执行切点
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable
    {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息(通过Logstash传入Elasticsearch)
        WebLog webLog = new WebLog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class))
        {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(log.value());
        }
        long endTime = System.currentTimeMillis();
        String urlStr = request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpendTime((int) (endTime - startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("url", webLog.getUrl());
        logMap.put("method", webLog.getMethod());
        logMap.put("parameter", webLog.getParameter());
        logMap.put("spendTime", webLog.getSpendTime());
        logMap.put("description", webLog.getDescription());
//        LOGGER.info("{}", JSONUtil.parse(webLog));

//        按格式打印日志信息
        for (String key : logMap.keySet())
        {
            LOGGER.info(key + ": " + logMap.get(key) + LINE_SP);
        }

//        LOGGER.info(Markers.appendEntries(logMap), JSONUtil.parse(webLog).toString());
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args)
    {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++)
        {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null)
            {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null)
            {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value()))
                {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0)
        {
            return null;
        }
        else if (argList.size() == 1)
        {
            return argList.get(0);
        }
        else
        {
            return argList;
        }
    }
}
```

## logback-spring.xml

 springboot 默认使用的也是 logback 日志,通过独立的 xml 配置文件来配置日志打印。按照指定的规则组织配置文件名，并放在 resources 目录下，就能自动被 spring boot 加载

+ `logback-spring.xml`首先定义了一些全局变量，如日志保存路径
+ `logback-spring.xml`其次定义了一个负责将日志输出到控制台的appender，还控制了日志输出格式、输出日志级别规则 
+ `logback-spring.xml`随后定义一个将日志输出到本地磁盘的appender，并且控制了日志文件的一些策略，如文件名称等。
+ `logback-spring.xml`最后控制WebLogAspect的logger日志级别为DEBUG，以及调用appender在控制台进行日志输出

# API

## IErrorCode

封装API的错误码，为一个接口



## ResultCode

枚举了一些常用API操作码:

```java
SUCCESS(200, "操作成功"),
FAILED(500, "操作失败"),
VALIDATE_FAILED(404, "参数检验失败"),
UNAUTHORIZED(401, "暂未登录或token已经过期"),
FORBIDDEN(403, "没有相关权限");
```

## CommonResult

通用返回对象，封装了给前端的返回信息，例如注册：

```json
{
  "code": 200,
  "message": "注册成功",
  "data": null
}
```
也封装一些常用方法供项目中使用，例如直接调用`success`方法传入数据即可。
```java
public static <T> CommonResult<T> success(T data)
{
    return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
}
```
或者直接传入错误码即可：

```java
public static <T> CommonResult<T> failed(IErrorCode errorCode)
{
    return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
}
```

# Swagger

## SwaggerProperties

Swagger自定义配置实体类，包括API文档生成基础路径，文档标题，文档描述等等

## BaseSwaggerConfig

定义了`Swagger`的基础配置，供子模块进行继承的。

`apiInfo(SwaggerProperties swaggerProperties)`方法通过`SwaggerProperties`会在页面上打印`Swagger`的文档信息

而该类预留了一个`public abstract SwaggerProperties swaggerProperties();`供子类自定义配置

例如，在`boying-user`中，

```java
public class SwaggerConfig extends BaseSwaggerConfig
{

    @Override
    public SwaggerProperties swaggerProperties()
    {
        return SwaggerProperties.builder()
                .apiBasePackage("com.tongji.boying.controller")
                .title("boying-userAPI文档")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
```





# Redis配置

+ BaseRedisConfig 负责Redis基础配置
+ RedisService 是接口,提供了Redis常用操作
+ RedisServiceImpl 是redis操作实现类

# 全局异常处理

当我们在Controller中校验失败时，直接抛出该异常，这样就可以达到校验失败返回错误信息的目的了。

## ApiException

自定义API异常，继承自`RuntimeException`,包含一个`IErrorCode`。当我们校验失败时抛出该异常

## Asserts

```
/**
 * 断言处理类，用于抛出各种API异常
 */
public class Asserts
{
    public static void fail(String message)
    {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode)
    {
        throw new ApiException(errorCode);
    }
}
```

## GlobalExceptionHandler

定义一个全局异常的处理类，用于处理全局异常，并返回封装好的CommonResult对象；



```java
@ResponseBody
@ExceptionHandler(value = ApiException.class)
public CommonResult handle(ApiException e)
{
    if (e.getErrorCode() != null)
    {
        return CommonResult.failed(e.getErrorCode());
    }
    return CommonResult.failed(e.getMessage());
}
```

## 使用

在`Service`层，如果逻辑校验失败，直接调用Asserts的fail方法：

```java
if(user==null)
{
	Asserts.fail("用户名不存在");
}
```

就会通过全局异常处理，返回数据给前端。就不需要每次都先在**service**层`return CommonResult.failed("用户名不存在");`,再在**controller层**返回该`CommonResult`了





# 分页

## 分页数据封装类CommonPage

有参数pageNum，pageSize，，totalPage，total，list





# 日志背景材料

### 日志架构

 门面设计模式是面向对象设计模式中的一种，日志框架采用的就是这种模式，类似JDBC的设计理念。它只提供一套接口规范，自身不负责日志功能的实现，目的是让使用者不需要关注底层具体是哪个日志库来负责日志打印机具体的使用细节等。

 在使用slf4j+具体日志库模式时，由于slf4j相当于充当api抽象接口，所以我们的日志打印是也是面向接口编程的，当我们需要更换具体的日志库时，我们只需要引入具体的maven依赖就可以了，并对原有的日志库依赖进行移除，而不需要改动代码。

### slf4j

`The Simple Logging Facade for Java`

只提供一系列日志记录接口和日志工厂，并不提供日志输出的具体实现，所以其必须搭配具体日志实现模块使用

**不推荐**在业务代码中直接使用 Logback，而是要使用 SLF4J这类日志门面模块,**面向接口编程**的一种体现（设计原则之**接口隔离**），日志实现模块是具体的东西，是变化的不断升级的

```java
Logger logger = LoggerFactory.getLogger(HelloWorld.class);
logger.info("Hello World");
```

```java
//常见两种用法：
// using traditional API
        logger.debug("Temperature set to {}. Old temperature was {}.", newT, oldT);

// using fluent API, add arguments one by one and then log message
        logger.atDebug().addArgument(newT).addArgument(oldT).log("Temperature set to {}. Old temperature was {}.");
```

### logback

Logback 构建在三个主要的类上：Logger，Appender 和 Layouts。这三个不同类型的组件一起作用能够让开发者根据消息的类型以及日志的级别来打印日志。

`Logger` 类作为 logback-classic 模块的一部分。`Appender` 与 `Layouts` 接口作为 logback-core 的一部分。作为一个通用的模块，logback-core 没有 logger 的概念。

任何日志 API 的优势在于它能够禁止某些日志的输出，但是又不会妨碍另一些日志的输出。通过假定一个日志空间，这个空间包含所有可能的日志语句，这些日志语句根据开发人员设定的标准来进行分类。在 logback-classic 中，分类是 logger 的一部分，每一个 logger 都依附在 `LoggerContext` 上，它负责产生 logger，并且通过一个树状的层级结构来进行管理。



#### 日志等级

等级（TRACE, DEBUG, INFO, WARN, ERROR）

对于一个给定的名为 *L* 的 logger，它的有效层级为从自身一直回溯到 root logger，直到找到第一个不为空的层级作为自己的层级。

为了确保所有的 logger 都有一个层级，root logger 会有一个默认层级 --- DEBUG

根据定义，打印的方法决定的日志的级别。例如：**L** 是一个 logger 实例，`L.info("...")` 的日志级别就是 INFO。

如果一条的日志的打印级别大于 logger 的有效级别，该条日志才可以被打印出来。这条规则总结如下：

> **基本选择规则**
>
> 日志的打印级别为 *p*，Logger 实例的级别为 *q*，如果 *p* >= *q*，则该条日志可以打印出来。
>
> 各级别的排序为：**TRACE** < **DEBUG** < **INFO** < **WARN** < **ERROR**

可以理解为logger定义的是最终要输出的日志级别，而打印级别是准备将日志打印出来的级别，通过logger级别对打印级别进行筛选

所以如果logger级别为info，他就要输出info及以上级别的日志，这时候如果要打印` logger.debug("调试信息");`,因为debug的优先级比较低，就算无效信息，忽略了

#### appender

logback 允许日志在多个地方进行输出。站在 logback 的角度来说，输出目的地叫做 appender。一个 logger 可以有多个 appender。对于给定的 logger，每一个允许输出的日志都会被转发到该 logger 的所有 appender 中去。

在默认的情况下，appender 是可以重复使用的：logger 可以通过附加到本身的 appender 输出日志，同样的也可以附加到起祖先的身上，并输出日志。因此，如果同一个 appender 附加到多个 logger 身上，那么就导致日志重复打印。

### 依赖说明

logback直接实现了slf4j的接口，不消耗内存和计算开销。

Spring Boot默认集成了Logback，可以开箱即用，非常方便。

`slf4j`可以理解为规则的制定者，是一个抽象层，定义了日志相关的接口。

`log4j`,`logback`,`JDK Logging`都是`slf4j`的实现层

因为spring-boot-starter-logging是Logback的日志实现，而Spring Boot启动项spring-boot-starter又依赖了spring-boot-starter-logging，所以Spring Boot就默认集成了Logback

默认情况下，Spring Boot会自动用Logback作为应用日志框架，并用INFO级别输出到控制台。

依赖在`spring-boot-starter-logging`

博影根模块依赖:

![image-20201024082014226](https://tongji4m3.oss-cn-beijing.aliyuncs.com/image-20201024082014226.png)





### logback-spring.xml

日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot 的高级Proﬁle功能

#### root标签

root logger 通过 `<root>` 元素来进行配置。它只支持一个属性——`level`。它不允许设置其它任何的属性，因为 additivity 并不适用 root logger。而且，root logger 的名字已经被命名为 "ROOT"，也就是说也不支持 name 属性。level 属性的值可以为：TRACE、DEBUG、INFO、WARN、ERROR、ALL、OFF，但是不能设置为 INHERITED 或 NULL。

跟 `<logger>` 元素类似，`<root>` 元素可以包含 0 或多个 `<appender-ref>` 元素。

### AOP

AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

#### 通知（Advice）

通知描述了切面要完成的工作以及何时执行。比如我们的日志切面需要记录每个接口调用时长，就需要在接口调用前后分别记录当前时间，再取差值。

- 前置通知（Before）：在目标方法调用前调用通知功能；
- 后置通知（After）：在目标方法调用之后调用通知功能，不关心方法的返回结果；
- 返回通知（AfterReturning）：在目标方法成功执行之后调用通知功能；
- 异常通知（AfterThrowing）：在目标方法抛出异常后调用通知功能；
- 环绕通知（Around）：通知包裹了目标方法，在目标方法调用之前和之后执行自定义的行为。

#### 连接点（JoinPoint）

通知功能被应用的时机。比如接口方法被调用的时候就是日志切面的连接点。

#### 切点（Pointcut）

切点定义了通知功能被应用的范围。比如日志切面的应用范围就是所有接口，即所有controller层的接口方法。

#### 切面（Aspect）

切面是通知和切点的结合，定义了何时、何地应用通知功能。

#### 引入（Introduction）

在无需修改现有类的情况下，向现有的类添加新方法或属性。