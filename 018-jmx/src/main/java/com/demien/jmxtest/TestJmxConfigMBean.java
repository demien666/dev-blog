package com.demien.jmxtest;

public interface TestJmxConfigMBean {
    String getValue();
    void setValue(String value);

    Long getRunningTime();
    void resetRunnigTime();
}
