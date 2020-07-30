package com.demien.spbootkafkaproducer.domain.service;

import com.demien.spbootkafkaproducer.domain.message.TestMessageChannels;
import com.demien.spbootkafkaproducer.domain.model.TestEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("testProducerService")
@EnableBinding(TestMessageChannels.class)
public class TestProducerService {

    private final static Logger log = Logger.getLogger(TestProducerService.class.getName());

    private final TestMessageChannels testMessageChannels;

    @Autowired
    public TestProducerService(TestMessageChannels testMessageChannels) {
        this.testMessageChannels = testMessageChannels;
    }

    public void sendTestMessages() {
        for (int i=0; i<10; i++) {
            var idString = Integer.toString(i);
            var testEntity = new TestEntity(idString, "Name " + idString, "Description " + idString);
            log.info("Sending to kafka entitiy: " + testEntity.toString());
            final Message<TestEntity> message = MessageBuilder.withPayload(testEntity).build();
            testMessageChannels.myTestChannel().send(message);
        }
    }
    
}