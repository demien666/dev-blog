package com.demien.aoplogging;

public class TestUIController {
    private TestService service;

    public void setService(TestService service) {
        this.service = service;
    }

    public void doSomeAction() {
            service.doServiceAction();
    }
}
