package com.demien.main.loan.domain.loan;

import com.demien.ddd.annotations.ValueObject;
import com.demien.main.security.domain.user.User;

import java.util.Date;

@ValueObject
public class LoanHistory {
    private Date date;
    private LoanAction action;
    private User user;


    public LoanHistory(Date date, LoanAction action, User user) {
        this.date = date;
        this.action = action;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public LoanAction getAction() {
        return action;
    }

    public User getUser() {
        return user;
    }
}
