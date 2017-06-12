package com.demien.testloan.integration.test;

import static com.demien.testloan.AppConst.CHECK_MAX_AMOUNT;
import static com.demien.testloan.AppConst.CHECK_MAX_IP_DAY_REQUESTS;
import static com.demien.testloan.AppConst.ERROR_WRONG_USER_ID;
import static com.demien.testloan.AppConst.FORMATTER;
import static com.demien.testloan.AppConst.REJECT_REASON_BAD_TIME;
import static com.demien.testloan.AppConst.REJECT_REASON_IP_DAY_ATTEMPTS;
import static com.demien.testloan.AppConst.RESULT_EXTENDED;
import static com.demien.testloan.AppConst.RESULT_OK;
import static com.demien.testloan.AppConst.RESULT_REJECTED;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.demien.testloan.AppConfig;
import com.demien.testloan.domain.Loan;
import com.demien.testloan.domain.User;
import com.demien.testloan.rest.BaseResource;
import com.demien.testloan.rest.LoanResource;
import com.demien.testloan.service.ILoanService;
import com.demien.testloan.service.IUserService;
import com.demien.testloan.utils.ObjectDataPopulator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class LoanIT extends BaseIT<Loan> {

    @Autowired
    private ILoanService loanService;

    @Autowired
    private IUserService userService;

    @Autowired
    private LoanResource loanResource;

    public LoanIT() {
        super(LoanResource.SERVICE_MAIN, Loan.class);
    }

    @Before
    public void clear() throws Exception {
        loanService.deleteAll();
        userService.deleteAll();
        // create test user
        User user=new User();
        ObjectDataPopulator.populate(user);
        userService.save(user);
    }

    private Integer getTestUserId() {
        User user=userService.getAll().get(0);
        return user.getId();
    }

    @SuppressWarnings("unchecked")
    private List<Loan> getAllLoans() throws Exception {
        List<Loan> loans = (List<Loan>) client.sendGetObjectResult(SERVICE_URL
                +  BaseResource.SERVICE_GET_ALL, Loan.class, true);
        assertNotNull(loans);
        return loans;
    }

    private String createLoan(final Float amount, final Date date, final Integer userId) throws Exception {
        String result=client.sendGetStringResult(SERVICE_URL+LoanResource.SERVICE_CREATE_LOAN+"?amount="+amount+"&date="+FORMATTER.format(date)+"&userid="+userId);
        assertNotNull(result);
        return result;
    }

    @Test
    public void loanCreationSuccessfullTest() throws Exception {
        List<Loan> loans =getAllLoans();
        int cnt=loans.size();
        assertEquals(0, cnt);
        // create
        String result=createLoan(1f, new Date(), getTestUserId());
        assertEquals(result, RESULT_OK);
        loans =getAllLoans();
        assertEquals(1, loans.size());
        //check for user
        Loan loan=loans.get(0);
        assertEquals(getTestUserId(), loan.getUser().getId());
    }

    @Test
    public void loanCreationRejectedByMaxIpRequestTest() throws Exception {
        // create 3 requests from one ip
        for (int i=0; i<CHECK_MAX_IP_DAY_REQUESTS; i++) {
            String result=createLoan(1f, new Date(), getTestUserId());
            assertEquals(result, RESULT_OK);
        }
        // next should fail
        String result=createLoan(1f, new Date(), -666);
        assertTrue(result.contains(RESULT_REJECTED));
        assertTrue(result.contains(REJECT_REASON_IP_DAY_ATTEMPTS));
        assertTrue(result.contains(ERROR_WRONG_USER_ID));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void loanCreationRejectedByBadTimeAndMaxAmountTest() throws Exception {
        // set "wrong time"
        Date d=new Date();
        loanResource.setCheckHourFrom(d.getHours()-1);
        loanResource.setCheckHourTo(d.getHours()+1);

        String result=createLoan(CHECK_MAX_AMOUNT+1, new Date(), getTestUserId());

        assertTrue(result.contains(RESULT_REJECTED));
        assertTrue(result.contains(REJECT_REASON_BAD_TIME));
    }

    @Test
    public void testLoanExtend() throws Exception {
        String result=createLoan(1f, new Date(), getTestUserId());
        assertEquals(result, RESULT_OK);
        List<Loan> loans =getAllLoans();
        int cnt=loans.size();
        Loan loan=loans.get(0);
        result=client.sendGetStringResult(SERVICE_URL+LoanResource.SERVICE_EXTEND_LOAN+"?id="+loan.getId());
        assertEquals(result, RESULT_EXTENDED);
        loans =getAllLoans();
        assertEquals(cnt+1, loans.size());
        // check for correct ID/PARENT ID
        int cnt1=0;
        int cnt2=0;
        for (Loan eachLoan:loans) {
            if (eachLoan.getId().equals(loan.getId())) {
                cnt1++;
                assertEquals(Loan.STATE.EXTENDED.toString(), eachLoan.getState());
            }
            if (eachLoan.getParentId()!=null && eachLoan.getParentId().equals(loan.getId())) {
                cnt2++;
                assertEquals(Loan.STATE.OPEN.toString(), eachLoan.getState());
                assertTrue(eachLoan.getRate()>loan.getRate());
                assertTrue(eachLoan.getDeadLine().getTime()>loan.getDeadLine().getTime());
            }
        }
        assertEquals(1,cnt1);
        assertEquals(1,cnt2);

    }


}
