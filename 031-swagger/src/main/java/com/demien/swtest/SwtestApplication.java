package com.demien.swtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
			"com.demien.swtest.config", "com.demien.swtest.rest", "com.demien.swtest.service"
			}
)
public class SwtestApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SwtestApplication.class, args);
	}
}
