package ru.itis.logger.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Slf4j
public class LoggerAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restControllerMethods() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethods() {
    }

    @Pointcut("@annotation(ru.itis.logger.annotation.Logging)")
    public void loggingMethods() {
    }

    private static final String BEFORE_MESSAGE = "before: method {} call args={}";

    private static final String AFTER_MESSAGE = "after returning: method {} result={}";

    private static final String AFTER_THROWING_MESSAGE = "after throwing: method {} throw exception";

    private static final String AROUND_MESSAGE = "around: method {} execution time={}";

    @Before("loggingMethods() || restControllerMethods() || serviceMethods()")
    public void before(JoinPoint joinPoint) {
        log.info(BEFORE_MESSAGE, joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "loggingMethods() || restControllerMethods() || serviceMethods()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info(AFTER_MESSAGE, joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "loggingMethods() || restControllerMethods() || serviceMethods()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error(AFTER_THROWING_MESSAGE, joinPoint.getSignature(), exception);
    }

    @Around("loggingMethods() || restControllerMethods() || serviceMethods()")
    public Object call(ProceedingJoinPoint proceedingJoinPoint) {
        Object res;
        try {
            long startTime = System.currentTimeMillis();
            res = proceedingJoinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            log.info(AROUND_MESSAGE, proceedingJoinPoint.getSignature(), executionTime);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
