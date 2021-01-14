package com.tongji.boying.common.log;

import com.tongji.boying.common.domain.WebLog;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 统一日志处理切面
 * 注解@Aspect:作用是把当前类标识为一个切面供容器读取,切面是通知和切点的结合，定义了何时、何地应用通知功能。
 * 注解@Order(1):Spring在加载Bean的时候，有用到order注解,此注解可操作于类、方法、字段，当作用在类时，值越小，则加载的优先级越高！
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {
    /**
     * 使用指定的类WebLogAspect的全限定类名为logger的名字来实例化一个logger,方便在日志输出的时候，打印出日志信息所属的类。
     * 创建日志实例,定义成static final,速度快,节省空间,只能指向本logger
     * 定义换行符号,方便日志打印时换行
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);
    private static final String LINE_SP = System.lineSeparator();

    /**
     * 定义切入点表达式,切点定义了通知功能被应用的范围。比如日志切面的应用范围就是所有接口，即所有controller层的接口方法。
     * execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数))
     * com.tongji.boying包及其子包下关于controller包下的所有类中的public方法都应用切面里的通知
     */
    @Pointcut("execution(public * com.tongji.boying.controller.*.*(..))||execution(public * com.tongji.boying.*.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 通知方法会在目标方法调用之前执行 里面的参数即为上述定义的webLog切入点表达式,防止重复编程
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        /**如果一条的日志的打印级别大于 logger 的有效级别，该条日志才可以被打印出来
         日志级别在logback-spring.xml配置文件中设为了debug,即只输出debug及以上的日志信息
         该条日志的打印级别为info,级别更高,所以该信息能打印(TRACE < DEBUG < INFO < WARN < ERROR
         该条日志标识了访问该接口的日志开始位置*/
        LOGGER.info("========================================== Start ==========================================" + LINE_SP);
    }

    /**
     * returning属性表明允许定义一个ret形参访问目标方法的返回值
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
//
        LOGGER.info("=========================================== End ===========================================" + LINE_SP);
    }

    /**    环绕通知，可以在切入点前后织入代码，并且可以自由的控制何时执行切点
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Date startDate = new Date(startTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(startDate);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog webLog = new WebLog();

        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(log.value());
        }

        long endTime = System.currentTimeMillis();
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setStartTime(startTime);
        webLog.setSpendTime((int) (endTime - startTime));

        Map<String, Object> logMap = new LinkedHashMap<>();
        String spendTime = webLog.getSpendTime() + "ms";

        logMap.put("description", webLog.getDescription());
        logMap.put("url", webLog.getUrl());
        logMap.put("method", webLog.getMethod());
        logMap.put("parameter", webLog.getParameter());
        logMap.put("result", webLog.getResult());
        logMap.put("startTime", time);
        logMap.put("spendTime", spendTime);

        for (String key : logMap.keySet()) {
            LOGGER.info(key + ": " + logMap.get(key) + LINE_SP);
        }

        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        }
        else if (argList.size() == 1) {
            return argList.get(0);
        }
        else {
            return argList;
        }
    }
}
