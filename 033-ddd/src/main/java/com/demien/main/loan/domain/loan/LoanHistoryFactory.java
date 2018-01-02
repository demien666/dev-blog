package com.demien.main.loan.domain.loan;

import com.demien.ddd.annotations.Factory;
import com.demien.main.system.application.SessionContext;

import java.util.Date;

@Factory
public class LoanHistoryFactory {

    //@Inject
    private SessionContext sessionContext;

    public LoanHistory create(Loan loan, LoanAction action) {
        return new LoanHistory(new Date(), action, sessionContext.getCurrentUser());
    }

}
