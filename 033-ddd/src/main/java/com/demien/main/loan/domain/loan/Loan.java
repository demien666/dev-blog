package com.demien.main.loan.domain.loan;

import com.demien.ddd.annotations.AggregateRoot;
import com.demien.ddd.base.AbstractEntity;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.security.domain.user.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AggregateRoot
public class Loan extends AbstractEntity {

    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal payed = new BigDecimal(0);

    private Client client;
    private User creator;
    private Date loanBeginDate;
    private Date loanEndDate;
    private List<LoanHistory> history;

    public Loan(BigDecimal amount, BigDecimal rate, Client client, User creator, Date loanEndDate, Date loanBeginDate) {
        this.amount = amount;
        this.rate = rate;
        this.client = client;
        this.creator = creator;
        this.loanEndDate = loanEndDate;
        this.loanBeginDate = loanBeginDate;
    }

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

    public List<LoanHistory> getHistory() {
        return history;
    }

    public void pay(BigDecimal money) {
        this.payed.add(money);
    }

    public BigDecimal getPayed() {
        return payed;
    }

    public  boolean canBeUpdated() {
        return true;
    }
}
