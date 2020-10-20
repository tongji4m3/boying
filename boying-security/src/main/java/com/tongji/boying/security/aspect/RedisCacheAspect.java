package com.tongji.boying.security.aspect;

import com.tongji.boying.security.annotation.CacheException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Redis缓存切面，防止Redis宕机影响正常业务逻辑
 * 定义一个切面，在相关缓存业务类上面应用，在它的环绕通知中直接处理掉异常，保障后续操作能执行。
 * 要保证缓存业务类中的方法执行不影响正常的业务逻辑，就需要在所有方法中添加`try catch`逻辑。
 * 使用AOP，我们可以在一个地方写上`try catch`逻辑，然后应用到所有方法上去。
 */
@Aspect
@Component
@Order(2)
public class RedisCacheAspect
{
    private static Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("execution(public * com.tongji.boying.service.*CacheService.*(..))")
    public void cacheAspect()
    {
    }

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;
        try
        {
            result = joinPoint.proceed();
        }
        catch (Throwable throwable)
        {
            //有CacheException注解的方法需要抛出异常
            if (method.isAnnotationPresent(CacheException.class))
            {
                throw throwable;
            }
            else
            {
                LOGGER.error(throwable.getMessage());
            }
        }
        return result;
    }

}
