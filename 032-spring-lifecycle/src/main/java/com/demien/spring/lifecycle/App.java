package com.demien.spring.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        Messenger messenger = ctx.getBean(Messenger.class);

        while (true) {
            Thread.sleep(3000);
            messenger.printMessage();
        }



    }
}
