package com.demien.spring.integration.activators;

import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;

public class FailedPaymentActivator {
    @ServiceActivator
    public void handleFailedOrder(Message<MessageHandlingException> message) {
        String payment=(String)message.getPayload().getFailedMessage().getPayload();
        System.out.println("FAILED:"+payment+" WITH ERROR:"+message.getPayload().getMessage());
    }
}
