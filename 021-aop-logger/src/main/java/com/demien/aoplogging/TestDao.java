package com.demien.aoplogging;

public class TestDao {

    public void doDaoAction(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done!");
    }

}
