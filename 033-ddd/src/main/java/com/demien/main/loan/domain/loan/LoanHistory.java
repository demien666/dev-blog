package com.demien.main.loan.domain.loan;

import com.demien.ddd.annotations.ValueObject;
import com.demien.main.security.domain.user.User;

import java.util.Date;

@ValueObject
public class LoanHistory {
    private Date date;
    private String action;
    private User user;


    public LoanHistory(Date date, String action, User user) {
        this.date = date;
        this.action = action;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public User getUser() {
        return user;
    }
}
