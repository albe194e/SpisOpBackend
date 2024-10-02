package com.example.spisopbackend.utils.aspects.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryLog extends LogAspect{

    // Pointcut to match all public methods in your service layer
    @Pointcut("execution(* com.example.spisopbackend.repository..*(..))")
    public void methods() {}
    // Before advice
    @Before("methods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("STARTING QUERY: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // After returning advice
    @AfterReturning(pointcut = "methods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("QUERY {} returned with value {}",
                joinPoint.getSignature().toShortString(), result);
    }

    // After throwing advice
    @AfterThrowing(pointcut = "methods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("QUERY {} threw exception {}",
                joinPoint.getSignature().toShortString(), error.getMessage());
    }

}
