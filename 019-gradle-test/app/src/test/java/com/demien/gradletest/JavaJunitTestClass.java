package com.demien.gradletest;

import org.junit.Assert;
import org.junit.Test;

public class JavaJunitTestClass {

    @Test
    public void itHaveToBeTest() {
        Assert.assertTrue(true);
        System.out.println("Hello from java JUnit test class");
    }
}
