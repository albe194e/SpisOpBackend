package com.example.spisopbackend.utils.aspects;

import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleExceptions {
    Class<? extends Throwable>[] exception();
    HttpStatus[] status();
}
