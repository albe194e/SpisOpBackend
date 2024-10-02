package com.example.spisopbackend.utils.aspects.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryLog implements Logger {

    private final String repositoryIndentationIn = YELLOW + "---------->" + RESET;
    private final String repositoryIndentationOut = YELLOW + "<----------" + RESET;
    private final int maxMethodNameLength = 30; // Adjust this value based on the longest method name

    // Pointcut to match all public methods in your repository layer
    @Pointcut("execution(* com.example.spisopbackend.repository..*(..))")
    public void methods() {}

    // Before advice
    @Before("methods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info(repositoryIndentationIn + "STARTING QUERY: " + YELLOW + "{}" + RESET + " " + padRight(methodName, maxMethodNameLength) + "with arguments: " + LIGHT_GREEN + "{}" + RESET,
                methodName,
                joinPoint.getArgs());
    }

    // After returning advice
    @AfterReturning(pointcut = "methods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info(repositoryIndentationOut + "QUERY:          " + YELLOW + "{}" + RESET + " " + padRight(methodName, maxMethodNameLength) + "returned with value " + YELLOW + "{}" + RESET,
                methodName, result);
    }

    // Helper method to pad the method name to a fixed length
    private String padRight(String str, int n) {
        return String.format("%-" + n + "s", str);
    }
}