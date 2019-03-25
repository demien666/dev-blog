package com.demien.sprdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprdataApplication {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(SprdataApplication.class);
		app.run(args);
	}

}
