package com.demien.spock;

import java.util.ArrayList;
import java.util.List;

public class TestJavaClass {

    private List<String> history=new ArrayList<String>();

    public void checkName(String name) {
        if (name==null) {
            throw new IllegalArgumentException("name is null!");
        }
    }

    public String sayHi(String name) {
        checkName(name);
        history.add(name);
        return "Hi, "+name;
    }

    public List<String> getHistoryItems() {
        return history;
    }

}

