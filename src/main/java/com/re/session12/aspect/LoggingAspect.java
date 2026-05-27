package com.re.session12.aspect;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.re.session12.controller.StudentController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with args {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "execution(* com.re.session12.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(Exception ex) {
        logger.warn("Exception thrown: {}", ex.getMessage());
    }

    @Around("execution(* com.re.session12.controller.StudentController.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        logger.info("phương thức {} chạy trong {} ms", joinPoint.getSignature().getName(), duration);
        return result;
    }
}
