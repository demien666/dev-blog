package com.demien.main.loan.application.impl;

import com.demien.main.loan.application.api.LoanService;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.loan.domain.loan.Loan;
import com.demien.main.loan.domain.loan.LoanRepository;
import com.demien.main.security.domain.user.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {

    //@Inject
    LoanRepository loanRepository;


    @Override
    public BigDecimal getRate(Client client, BigDecimal amount) {
        return null;
    }

    @Override
    public Optional<Loan> createLoan(Client client, User user, BigDecimal amount, BigDecimal rate) {
        return null;
    }

    @Override
    public Optional<Loan> updateLoan(Loan loan, BigDecimal pay) {
        return null;
    }

    @Override
    public Optional<Loan> extendLoan(Loan loan, Date newEndDate) {
        return null;
    }

    @Override
    public Optional<Loan> deleteLoan(Loan loan) {
        return null;
    }
}
