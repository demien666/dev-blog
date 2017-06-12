package com.demien.spock;

public class AnotherJavaClass {
    TestJavaClass testJavaClass;

    public void setTestJavaClass(TestJavaClass testJavaClass) {
        this.testJavaClass = testJavaClass;
    }

    public String introduceYourself() {
        return testJavaClass.sayHi("I'm AnotherJavaClass");
    }

    public String getHistory() {
        StringBuilder result=new StringBuilder();
        for (String s:testJavaClass.getHistoryItems()) {
            if (result.length()>0) {
                result.append(",");
            }
            result.append(s);
        }
        return result.toString();
    }
}
