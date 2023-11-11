package com.daas.challenges.superheroes.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Around("@annotation(ExecutionTimeLogger)")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        String caller = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        logger.info("==> {}, method = {}", caller, method);
        Object result = joinPoint.proceed();
        double executionTimeInSeconds = (System.currentTimeMillis() - start) / 1000d;
        logger.info("<== {}, method = {}, executed in = {}s", caller, method, executionTimeInSeconds);

        return result;
    }
}
