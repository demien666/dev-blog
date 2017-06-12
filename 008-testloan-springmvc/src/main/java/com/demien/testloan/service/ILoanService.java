package com.demien.testloan.service;

import com.demien.testloan.domain.Loan;

public interface ILoanService extends IBaseService<Loan> {
  void extendLoan(Integer id);	

}
