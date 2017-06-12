package com.demien.spring.integration;

import com.demien.spring.integration.dto.Payment;
import com.demien.spring.integration.gateways.LoadPaymentsGateway;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {

    static LoadPaymentsGateway gateway;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/expences-context.xml");

        Payment payment1=new Payment("First payment", new BigDecimal(666) );
        Payment payment2=new Payment("Second payment", new BigDecimal(20000) );
        Payment payment3=new Payment("Null payment", null );
        Payment payment4=new Payment("Test payment", new BigDecimal(1) );


        List<Payment> payments=new ArrayList<Payment>();
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
        payments.add(payment4);

        gateway=(LoadPaymentsGateway)context.getBean("inPaymentGateway");


        gateway.loadPayments(payments);
        context.close();
    }
}
