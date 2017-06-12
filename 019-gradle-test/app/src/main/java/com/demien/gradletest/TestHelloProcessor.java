package com.demien.gradletest;

public class TestHelloProcessor implements TestProcessor {

    @Override
    public String getGreetingWord() {
        return "Hello";
    }
}
