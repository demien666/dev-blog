package com.demien.gradletest;

public class TestHiProcessor implements TestProcessor {

    @Override
    public String getGreetingWord() {
        return "Hi";
    }
}
