package com.demien.gradletest;

public class TestClass {

    TestProcessor testProcessor;

    public void setTestProcessor(TestProcessor testProcessor) {
        this.testProcessor = testProcessor;
    }

    public String greeting(String name) {
        return testProcessor.getGreetingWord() +", "+name;
    }
}
