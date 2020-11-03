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