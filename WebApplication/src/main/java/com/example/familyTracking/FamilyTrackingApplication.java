package com.example.familyTracking;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class FamilyTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyTrackingApplication.class, args);
	}

	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger(FamilyTrackingApplication.class);
	}
}


