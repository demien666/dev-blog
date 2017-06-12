package com.demien.spring.integration.interceptor;

import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;

public class LogInterceptor extends ChannelInterceptorAdapter {

    public Message<?> preSend(Message<?> message,
                              MessageChannel channel) {
        System.out.println("[["+channel.toString()+"]] "+message.getPayload());
        return message;
    }

}
