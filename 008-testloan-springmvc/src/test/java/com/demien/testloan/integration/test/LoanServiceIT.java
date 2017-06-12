package com.demien.testloan.integration.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.demien.testloan.AppConfig;
import com.demien.testloan.domain.Loan;
import com.demien.testloan.service.ILoanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class LoanServiceIT {

    @Autowired
    private ILoanService loanService;

    @Test
    public void test() {
        Loan loan = new Loan();
        loanService.save(loan);
    }

}
