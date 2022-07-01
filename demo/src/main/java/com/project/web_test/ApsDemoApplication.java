package com.project.web_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApsDemoApplication.class, args);
	}

}
