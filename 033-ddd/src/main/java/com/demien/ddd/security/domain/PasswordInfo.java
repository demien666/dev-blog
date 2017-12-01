package com.demien.ddd.security.domain;

import com.demien.ddd.shared.annotations.ValueObject;

import java.util.Date;

@ValueObject
public class PasswordInfo {

    private String password;
    private Date changed;

    public PasswordInfo(String password) {
        this.password = password;
        changed = new Date();
    }

    public Date getChanged() {
        return changed;
    }

    public String getPassword() {
        return password;
    }

}
