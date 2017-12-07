package com.demien.main.shared.domain;

import com.demien.ddd.annotations.ValueObject;

@ValueObject
public class ContactInfo {
    private String email;
    private String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
