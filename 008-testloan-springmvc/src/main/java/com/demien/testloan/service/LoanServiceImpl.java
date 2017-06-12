package com.demien.testloan.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.demien.testloan.AppConst;
import com.demien.testloan.dao.IBaseDAO;
import com.demien.testloan.domain.Loan;

public class LoanServiceImpl extends BaseServiceImpl<Loan> implements ILoanService {

    public LoanServiceImpl(final IBaseDAO<Loan> dao) {
        super(Loan.class, dao);
    }

    @Transactional
    @Override
    public void extendLoan(final Integer id) {
        Loan oldLoan=get(id);
        Loan newLoan=oldLoan.clone();
        oldLoan.setState(Loan.STATE.EXTENDED.toString());
        oldLoan.setChangeDate(new Date());

        newLoan.setRate(newLoan.getRate()*AppConst.EXTEND_RATE_MUL);
        Date d=new Date();
        d.setTime(d.getTime()+AppConst.EXTEND_INTERVAL);
        newLoan.setDeadLine(d);
        newLoan.setParentId(id);
        update(oldLoan);
        save(newLoan);
    }

}
