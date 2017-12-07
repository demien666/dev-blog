package com.demien.main.loan.application.api;

import com.demien.main.loan.domain.client.Client;
import com.demien.main.loan.domain.loan.Loan;
import com.demien.main.security.domain.user.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface LoanService {
    BigDecimal getRate(Client client, BigDecimal amount);

    Optional<Loan> createLoan(Client client, User user, BigDecimal amount, BigDecimal rate);

    Optional<Loan> updateLoan(Loan loan, BigDecimal pay);

    Optional<Loan> extendLoan(Loan loan, Date newEndDate);

    Optional<Loan> deleteLoan(Loan loan);

}
