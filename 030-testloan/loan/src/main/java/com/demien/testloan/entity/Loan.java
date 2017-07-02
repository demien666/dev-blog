package com.demien.testloan.entity;

import com.demien.testloan.vo.ClientId;
import com.demien.testloan.vo.LoanId;

import java.math.BigDecimal;
import java.util.Date;

public class Loan {
    private LoanId loanId;
    private ClientId clientId;
    private BigDecimal amount;
    private Date deadline;


}
