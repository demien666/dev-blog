package com.demien.spbootkafkaconsumer.domain.service;

import com.demien.spbootkafkaconsumer.domain.message.TestMessageChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@EnableBinding(TestMessageChannels.class)
public class TestConsumerService {

    private final static Logger log = Logger.getLogger(TestConsumerService.class.getName());

    @StreamListener("myTestChannel")
    public void consumeTestEntity(byte[] testEntity) {
        log.info("Received from kafka: " + new String(testEntity));
    }
}