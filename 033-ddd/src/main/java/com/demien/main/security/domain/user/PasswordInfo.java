package com.demien.main.security.domain.user;

import com.demien.ddd.annotations.ValueObject;

import java.util.Date;

@ValueObject
public class PasswordInfo {

    private String password;
    private Date changed = new Date();

    public PasswordInfo(String password) {
        this.password = password;
    }

    public Date getChanged() {
        return changed;
    }

    public String getPassword() {
        return password;
    }

}
