package com.demien.spbootkafkaproducer;

import com.demien.spbootkafkaproducer.domain.service.TestProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpbootKafkaProducerApplication {

	public static void main(String[] args) {
		final var applicationContext = SpringApplication.run(SpbootKafkaProducerApplication.class, args);
		new SpbootKafkaProducerApplication().run(applicationContext);
	}

	public void run(ApplicationContext applicationContext) {
		TestProducerService testProducerService = applicationContext.getBean(TestProducerService.class);
		testProducerService.sendTestMessages();
	}

}
