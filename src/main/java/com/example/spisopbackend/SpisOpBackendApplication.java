package com.example.spisopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpisOpBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpisOpBackendApplication.class, args);
	}

}
