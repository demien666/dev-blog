package com.demien.testloan.entity;

import com.demien.testloan.enums.LoanState;
import com.demien.testloan.vo.ClientId;
import com.demien.testloan.vo.LoanId;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {
    private LoanId loanId;
    private final ClientId clientId;
    private final BigDecimal amount;
    private final LocalDate deadline;
    private final LoanState loanState;

    public Loan(ClientId clientId, BigDecimal amount) {
        this(clientId, amount, LocalDate.now(), LoanState.CREATED);
    }

    private Loan(ClientId clientId, BigDecimal amount, LocalDate deadline, LoanState loanState) {
        this.clientId = clientId;
        this.amount = amount;
        this.deadline = deadline;
        this.loanState = loanState;
    }

    public Loan extendBy(int days) {
        return new Loan(clientId, amount, deadline.plusDays(days), LoanState.EXTENDED);
    }


}
