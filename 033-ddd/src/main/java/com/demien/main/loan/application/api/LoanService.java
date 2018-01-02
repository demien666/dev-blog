package com.demien.main.loan.application.api;

import com.demien.main.loan.domain.client.Client;
import com.demien.main.loan.domain.loan.Loan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface LoanService {
    BigDecimal getRate(Client client, BigDecimal amount);

    Optional<Loan> createLoan(Client client, BigDecimal amount, BigDecimal rate, Date loanEndDate, Date loanBeginDate);

    Optional<Loan> updateLoan(Long loanId, BigDecimal pay);

    Optional<Loan> extendLoan(Long loanId, Date newEndDate);

    Optional<Loan> deleteLoan(Long loanId);

}
