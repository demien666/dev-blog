package com.demien.aoplogging;

public class TestService {
    private TestDao dao;

    public void setDao(TestDao dao) {
        this.dao = dao;
    }


    public void doServiceAction() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dao.doDaoAction();
    }
}
