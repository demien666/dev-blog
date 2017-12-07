package com.demien.main.loan.domain.loan;

import com.demien.ddd.annotations.AggregateRoot;
import com.demien.ddd.base.AbstractEntity;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.security.domain.user.User;

import java.math.BigDecimal;
import java.util.Date;

@AggregateRoot
public class Loan extends AbstractEntity {

    private BigDecimal amount;
    private BigDecimal rate;

    private Client client;
    private User creator;
    private Date createDate = new Date();
    private Date loanBeginDate;
    private Date loanEndDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getLoanBeginDate() {
        return loanBeginDate;
    }

    public void setLoanBeginDate(Date loanBeginDate) {
        this.loanBeginDate = loanBeginDate;
    }

    public Date getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }
}
