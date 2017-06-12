package com.demien.testloan.rest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.demien.testloan.AppConst;
import com.demien.testloan.domain.Loan;
import com.demien.testloan.domain.User;
import com.demien.testloan.service.ILoanService;
import com.demien.testloan.service.IUserService;
import com.demien.testloan.utils.ObjectDataPopulator;

public class LoanResourceTest extends BaseResourceTest<Loan> {

    private static ILoanService loanService;
    private static IUserService userService;

    private LoanResource resource;

    private final int DAY_SECONDS=1000*60*60*24;

    public LoanResourceTest() {
        super(Loan.class);
    }

    @Before
    public void init() {
        loanService=mock(ILoanService.class);
        userService=mock(IUserService.class);
        resource=new LoanResource(loanService, userService);
    }

    @Test
    public void isDateTodayTest() {
        Date d=new Date();
        assertTrue(resource.isDateToday(d));
        assertFalse(resource.isDateToday(new Date(d.getTime() - DAY_SECONDS)));
        assertFalse(resource.isDateToday(new Date(d.getTime() + DAY_SECONDS)));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void isTimeInHourIntervalTest(){
        Date d = new Date();
        int hour = d.getHours();
        assertTrue(resource.isTimeInHourInterval(hour, hour+1));
        assertFalse(resource.isTimeInHourInterval(hour+1, hour+2));
        assertFalse(resource.isTimeInHourInterval(hour-2, hour-1));
    }

    public Loan getTestLoan(final String ipAddr, final Date createDate) {
        Loan loan=new Loan();
        loan.setIpAddr(ipAddr);
        loan.setCreateDate(createDate);
        return loan;
    }

    @Test
    public void getIpRequestTodayCountTest() {
        String ipAddr="0.0.0.0";
        List<Loan> loans=new ArrayList<Loan>();
        Date today=new Date();
        Date notToday=new Date(today.getTime()-DAY_SECONDS);
        loans.add(getTestLoan(ipAddr, today));
        loans.add(getTestLoan(ipAddr, notToday));
        loans.add(getTestLoan("", today));
        loans.add(getTestLoan("", notToday));

        when(loanService.getAll()).thenReturn(loans);
        int cnt=resource.getIpRequestTodayCount(ipAddr);
        assertEquals(1, cnt);
    }

    @Test
    public void createLoanTest () throws Exception {
        User user=new User();
        ObjectDataPopulator.populate(user);
        Loan loan=new Loan();
        ObjectDataPopulator.populate(loan);
        when(userService.get(user.getId())).thenReturn(user);
        ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
        String dateStr=AppConst.FORMATTER.format(loan.getDeadLine());
        resource.createLoan(loan.getAmount().toString(), dateStr, user.getId());
        verify(userService).get(user.getId());
        verify(loanService).save(captor.capture());
        assertEquals(loan.getAmount(), captor.getValue().getAmount());
        assertEquals(dateStr, AppConst.FORMATTER.format(captor.getValue().getDeadLine()) );
        assertEquals(user.getId(), captor.getValue().getUser().getId());
    }

    @Test
    public void extendLoanTest() {
        Integer id=666;
        resource.extendloan(id);
        verify(loanService).extendLoan(id);
    }


}

