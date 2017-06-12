package com.demien.spring.integration.activators;

import org.springframework.integration.annotation.ServiceActivator;

public class PaymentServiceActivator {

    @ServiceActivator
    public void processPayment(String payment) throws Exception {
         if (payment.toUpperCase().contains("TEST")) {
             throw new Exception("Test payment was not processed");
         }
        // some logic have to be here
        System.out.println("PROCESSED:"+payment);
    }
}
