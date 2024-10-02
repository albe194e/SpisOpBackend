package com.example.spisopbackend.utils.aspects.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLog implements Logger {

    private final String serviceIndentationIn = BLUE + "------>" + RESET;
    private final String serviceIndentationOut = BLUE + "<------" + RESET;

    // Pointcut to match all public methods in your service layer
    @Pointcut("execution(* com.example.spisopbackend.service..*(..)) && !execution(* com.example.spisopbackend.service..*.toDto(..))")
    public void methods() {}

    // Before advice
    @Before("methods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info(serviceIndentationIn + "Entering method: "+ BLUE + "{}" + RESET + " with arguments: " + BLUE + "{}" + RESET,
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // After returning advice
    @AfterReturning(pointcut = "methods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info(serviceIndentationOut + "Method:          " + BLUE + "{}" + RESET+  " returned with value " + BLUE + "{}" + RESET,
                joinPoint.getSignature().toShortString(), result);
    }
}