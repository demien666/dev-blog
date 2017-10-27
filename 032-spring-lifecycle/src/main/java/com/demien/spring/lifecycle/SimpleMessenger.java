package com.demien.spring.lifecycle;

import com.demien.spring.lifecycle.annotations.GeneratedName;
import com.demien.spring.lifecycle.annotations.Profiling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Profiling
public class SimpleMessenger implements Messenger {

    private String messageText = "NULL";

    @GeneratedName(minLength = 5, maxLength = 10)
    private String name;

    public SimpleMessenger() {
        System.out.print("Constructor: ");
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
        System.out.println();
        System.out.println(messageText + ", " + name+" ["+ LocalTime.now()+"]");
    }
}
