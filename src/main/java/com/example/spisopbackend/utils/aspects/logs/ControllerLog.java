package com.example.spisopbackend.utils.aspects.logs;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLog implements Logger {

    private final String controllerIndentationIn = LIGHT_GREEN + "-->" + RESET;
    private final String controllerIndentationReturn = LIGHT_GREEN + "<--" + RESET;

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
    public void beforeGetRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationIn + BLUE + "[GET]:" + RESET + " {}", request.getRequestURI());
        }
    }


    @Before("postMethods()")
    public void logPostRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationIn + GREEN + "[POST]:" + RESET + " {}", request.getRequestURI());
            log.info(controllerIndentationIn + "Request Payload: " + LIGHT_GREEN + "{}"+ RESET, Arrays.toString(joinPoint.getArgs()));
        }
    }

    @Before("putMethods()")
    public void logPutRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationIn + YELLOW + "[PUT]:" + RESET + " {}", request.getRequestURI());
            log.info(controllerIndentationIn + "Request Payload: " + LIGHT_YELLOW + "{}"+ RESET, Arrays.toString(joinPoint.getArgs()));
        }
    }

    @Before("deleteMethods()")
    public void logDeleteRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationIn + RED + "[DELETE]:" + RESET + " {}", request.getRequestURI());
        }
    }

    @AfterReturning(pointcut = "getMethods() && execution(* *(..))", returning = "result")
    public void afterGetRequests(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationReturn + BLUE + "[GET] Success: " + RESET + " {}", request.getRequestURI());
            log.info(controllerIndentationReturn + "Response: " + LIGHT_BLUE + "{}" + RESET, result);
        }
    }

    @AfterReturning(pointcut = "postMethods() && execution(* *(..))", returning = "result")
    public void afterPostRequests(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationReturn + GREEN + "[POST] Success: " + RESET + " {}", request.getRequestURI());
            log.info(controllerIndentationReturn + "Response: " + LIGHT_GREEN + "{}" + RESET, result);
        }
    }

    @AfterReturning(pointcut = "putMethods() && execution(* *(..))", returning = "result")
    public void afterPutRequests(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationReturn + YELLOW + "[PUT] Success: " + RESET + " {}", request.getRequestURI());
            log.info(controllerIndentationReturn + "Response: " + LIGHT_YELLOW + "{}" + RESET, result);
        }
    }

    @AfterReturning(pointcut = "deleteMethods() && execution(* *(..))")
    public void afterDeleteRequests(JoinPoint joinPoint) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            log.info(controllerIndentationReturn + RED + "[DELETE] Success: " + RESET + " {}", request.getRequestURI());
        }
    }


    // Helper method to retrieve HttpServletRequest
    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }
}
