package com.example.familyTracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.example")
@EnableAutoConfiguration
public class FamilyTrackingApplication {

	public static void main(String[] args) {

		SpringApplication.run(FamilyTrackingApplication.class, args);
	}
}

