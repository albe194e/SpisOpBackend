package com.example.spisopbackend.utils.aspects.logs;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLog extends LogAspect{

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(@org.springframework.web.bind.annotation.GetMapping * *(..))")
    public void getMethods() {}

    // Pointcut for POST requests
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(@org.springframework.web.bind.annotation.PostMapping * *(..))")
    public void postMethods() {}

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(@org.springframework.web.bind.annotation.PutMapping * *(..))")
    public void putMethods() {}

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(@org.springframework.web.bind.annotation.DeleteMapping * *(..))")
    public void deleteMethods() {}

    @Before("getMethods()")
    public void logGetRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            logger.info("GET Request: {} FROM: {}", request.getMethod(), request.getRequestURI());
        }
        logger.info("Entering method: {}", joinPoint.getSignature().toShortString());
    }

    @Before("postMethods()")
    public void logPostRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            logger.info("POST Request: {} FROM: {}", request.getMethod(), request.getRequestURI());
            logger.info("Request Payload: {}", Arrays.toString(joinPoint.getArgs()));
        }
        logger.info("Entering method: {}", joinPoint.getSignature().toShortString());
    }

    @Before("putMethods()")
    public void logPutRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            logger.info("PUT Request: {} FROM: {}", request.getMethod(), request.getRequestURI());
            logger.info("Request Payload: {}", Arrays.toString(joinPoint.getArgs()));
        }
        logger.info("Entering method: {}", joinPoint.getSignature().toShortString());
    }

    @Before("deleteMethods()")
    public void logDeleteRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            logger.info("DELETE Request: {} FROM: {}", request.getMethod(), request.getRequestURI());
        }
        logger.info("Entering method: {}", joinPoint.getSignature().toShortString());
    }


    // Helper method to retrieve HttpServletRequest
    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }
}
