package com.demien.aoplogging;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/application-context.xml");
        TestUIController controller=(TestUIController)context.getBean("controller");
        controller.doSomeAction();
    }
}
