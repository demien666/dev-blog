package com.demien.spbootkafkaproducer.domain.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestMessageChannels {
    
    @Output("myTestChannel")
    MessageChannel myTestChannel();
    
}