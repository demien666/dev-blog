package com.demien.main.loan.domain.loan;

import com.demien.ddd.annotations.Factory;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.system.application.SessionContext;

import java.math.BigDecimal;
import java.util.Date;

@Factory
public class LoanFactory {

    //@Inject
    private SessionContext sessionContext;

    public Loan create(BigDecimal amount, BigDecimal rate, Client client, Date loanEndDate, Date loanBeginDate) {
        return new Loan(amount, rate, client, sessionContext.getCurrentUser(), loanEndDate, loanBeginDate);
    }
}
