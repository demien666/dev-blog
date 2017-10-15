package com.demien.spring.lifecycle;

import com.demien.spring.lifecycle.annotations.Profiling;
import com.demien.spring.lifecycle.annotations.RandomInt;

import javax.annotation.PostConstruct;

@Profiling
public class SimpleMessanger implements Messenger {

    private String messageText="NULL";

    @RandomInt(min=2, max=5)
    private int lifeTime=-1;

    public SimpleMessanger() {
        System.out.print("Contructor: ");
        printMessage();
    }

    @PostConstruct
    public void init() {
        System.out.print("Init: ");
        printMessage();
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public void printMessage() {
            System.out.println("message:"+messageText+" lifetime:"+lifeTime);
    }
}
