package com.example.spisopbackend.utils.aspects.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLog extends LogAspect{

    // Pointcut to match all public methods in your service layer
    @Pointcut("execution(* com.example.spisopbackend.service..*(..))")
    public void methods() {}
    // Before advice
    @Before("methods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // After returning advice
    @AfterReturning(pointcut = "methods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned with value {}",
                joinPoint.getSignature().toShortString(), result);
    }

    // After throwing advice
    @AfterThrowing(pointcut = "methods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Method {} threw exception {}",
                joinPoint.getSignature().toShortString(), error.getMessage());
    }

}
