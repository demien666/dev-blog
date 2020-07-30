package com.demien.spbootkafkaconsumer.domain.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface TestMessageChannels {
    
    @Input("myTestChannel")
    MessageChannel myTestChannel();
    
}