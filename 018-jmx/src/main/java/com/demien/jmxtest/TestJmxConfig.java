package com.demien.jmxtest;

import java.util.Date;

public class TestJmxConfig implements TestJmxConfigMBean {

    private String value="Start value";
    private Date startTime=new Date();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value=value;
    }

    public Long getRunningTime() {
        Date currectTime=new Date();
        return currectTime.getTime()-startTime.getTime();
    }

    public void resetRunnigTime() {
        startTime=new Date();
    }
}
