package com.example.spisopbackend.utils.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    @Around("@annotation(handleExceptions)")
    public Object handleExceptions(ProceedingJoinPoint pjp, HandleExceptions handleExceptions) {
        try {
            return pjp.proceed();
        } catch (Throwable ex) {
            logger.error("Exception in method: {} with message: {}", pjp.getSignature(), ex.getMessage());

            Class<? extends Throwable>[] exceptions = handleExceptions.exception();
            HttpStatus[] statuses = handleExceptions.status();

            for (int i = 0; i < exceptions.length; i++) {
                if (exceptions[i].isInstance(ex)) {
                    logger.error(ex.getMessage());
                    String errorMessage = "Error: " + ex.getMessage();
                    return ResponseEntity.status(statuses[i]).body(errorMessage);  // Return error message
                }
            }
            // Log the unhandled exception for debugging
            logger.info(String.format("Unhandled exception: " + ex.getMessage()));
            // You might want to return a general error response here instead of a RuntimeException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }


}
