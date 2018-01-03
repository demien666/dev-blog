package com.demien.main.loan.application.impl;

import com.demien.main.loan.application.api.LoanService;
import com.demien.main.loan.application.api.PermissionException;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.loan.domain.loan.*;
import com.demien.main.security.domain.user.Permission;
import com.demien.main.security.domain.user.User;
import com.demien.main.system.application.SessionContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {

    //@Inject
    LoanRepository loanRepository;

    //@Inject
    LoanFactory loanFactory;

    //@Inject
    LoanHistoryFactory loanHistoryFactory;

    // @Inject
    SessionContext sessionContext;

    public static final String PERMISSION_VIOLATION = "Current user don't have required permission";


    @Override
    public BigDecimal getRate(Client client, BigDecimal amount) {
        return null;
    }

    public void checkPermission(Permission permission) {
        User user = sessionContext.getCurrentUser();
        if (!user.hasPermission(permission)) throw new PermissionException(permission, user, PERMISSION_VIOLATION);
    }

    @Override
    public Optional<Loan> createLoan(Client client, BigDecimal amount, BigDecimal rate, Date loanEndDate, Date loanBeginDate) {
        checkPermission(Permission.CREATE_LOAN);

        Loan loan = loanFactory.create(amount, rate, client, loanEndDate, loanBeginDate);
        loanRepository.save(loan);

        loan.getHistory().add(loanHistoryFactory.create(loan, LoanAction.CREATE_LOAN));

        return Optional.ofNullable(loan);
    }

    @Override
    public Optional<Loan> updateLoan(Long loanId, BigDecimal pay) {
        checkPermission(Permission.UPDATE_LOAN);
        Loan loan = loanRepository.findById(loanId);

        //if (!loan.canBeUpdated()) throw



        return null;
    }

    @Override
    public Optional<Loan> extendLoan(Long loanId, Date newEndDate) {
        return null;
    }

    @Override
    public Optional<Loan> deleteLoan(Long loanId) {
        return null;
    }
}
