package com.demien.reactweb;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class RandomNumberController {

	private final Log log = LogFactory.getLog(RandomNumberController.class);

	@RequestMapping("/random")
	public Flux<Integer> random() {
		return Flux.interval(Duration.ofSeconds(5)).map(i -> {
			this.log.info("iteration:" + i);
			return generateRandomNumber();
		}).log();
	}

	private int generateRandomNumber() {
		return (int) (Math.random() * 1000);
	}

}
