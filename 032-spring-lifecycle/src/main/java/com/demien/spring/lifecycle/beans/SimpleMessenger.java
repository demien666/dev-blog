package com.demien.spring.lifecycle.beans;

import com.demien.spring.lifecycle.annotations.GeneratedName;
import com.demien.spring.lifecycle.annotations.Profiling;

import java.time.LocalTime;

@Profiling
public class SimpleMessenger implements Messenger {

    private String messageText = "NULL";
    private String symbol = "";

    @GeneratedName(minLength = 5, maxLength = 10)
    private String name;

    public SimpleMessenger() {
        System.out.print("Constructor: ");
        printMessage();
    }

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
        System.out.println(messageText + ", " + name+symbol+" ["+ LocalTime.now()+"]");
    }

    @Override
    public void setUp(String symbol) {
        this.symbol = symbol;
        System.out.print("Setup: ");
        printMessage();
    }
}
