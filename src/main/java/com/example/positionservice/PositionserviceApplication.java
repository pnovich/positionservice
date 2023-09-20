package com.example.positionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PositionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionserviceApplication.class, args);
	}

}
