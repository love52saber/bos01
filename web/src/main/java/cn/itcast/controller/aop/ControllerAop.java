package cn.itcast.controller.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerAop {

    @Pointcut("execution(* cn.itcast.controller.*.*(..))")
    private void pointcut() {

    }

    private static ThreadLocal<HttpServletRequest> requestThreadLocal;
    private static Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    @Around("pointcut()")
    //controller统一日志打印
    public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        logger.info("程序执行时间:--"+(System.currentTimeMillis()-startTime)+"  ms");
        return result;
    }
}
