package com.demien.jmxtest;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class App {

    static TestJmxConfig mBean = new TestJmxConfig();

    public static void registerMbean() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.demien.jmxtest:type=TestJmxConfig");
        mbs.registerMBean(mBean, name);

    }

    public static void main(String[] args) throws Exception {
        registerMbean();
        while (true) {
            System.out.println("mbeanData: RunningTime="+mBean.getRunningTime()+"  Value="+mBean.getValue());
            Thread.sleep(10000);
        }
    }
}
