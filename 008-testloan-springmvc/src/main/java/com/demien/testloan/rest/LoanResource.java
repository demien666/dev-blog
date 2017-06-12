package com.demien.testloan.rest;

import static com.demien.testloan.AppConst.CHECK_MAX_AMOUNT;
import static com.demien.testloan.AppConst.CHECK_MAX_IP_DAY_REQUESTS;
import static com.demien.testloan.AppConst.DEFAULT_RATE;
import static com.demien.testloan.AppConst.ERROR_AMOUNT_IS_NULL;
import static com.demien.testloan.AppConst.ERROR_DATE_IS_NULL;
import static com.demien.testloan.AppConst.ERROR_WRONG_AMOUNT_FORMAT;
import static com.demien.testloan.AppConst.ERROR_WRONG_DATE_FORMAT;
import static com.demien.testloan.AppConst.ERROR_WRONG_USER_ID;
import static com.demien.testloan.AppConst.FORMATTER;
import static com.demien.testloan.AppConst.REJECT_REASON_BAD_TIME;
import static com.demien.testloan.AppConst.REJECT_REASON_IP_DAY_ATTEMPTS;
import static com.demien.testloan.AppConst.RESULT_EXTENDED;
import static com.demien.testloan.AppConst.RESULT_OK;
import static com.demien.testloan.AppConst.RESULT_REJECTED;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demien.testloan.domain.Loan;
import com.demien.testloan.domain.User;
import com.demien.testloan.service.ILoanService;
import com.demien.testloan.service.IUserService;

@Controller
@RequestMapping(value = "/loan")
public class LoanResource extends BaseResource<Loan> {
    // http://localhost:8080/testloan/services/rest/loan/sayhello
    // http://localhost:8080/testloan/services/rest/loan/create?amount=66&date=01.01.2014
    private ILoanService loanService;
    private IUserService userService;

    private static int checkHourFrom;
    private static int checkHourTo;

    // services
    public static final String SERVICE_MAIN = "loan";
    public static final String SERVICE_CREATE_LOAN = "createLoan";
    public static final String SERVICE_EXTEND_LOAN = "extendLoan";

    public LoanResource(final ILoanService loanService, final IUserService userService) {
        super(Loan.class, loanService);
        this.loanService = loanService;
        this.userService = userService;
    }

    public void setCheckHourFrom(final int checkHourFrom) {
        LoanResource.checkHourFrom = checkHourFrom;
    }


    public void setCheckHourTo(final int checkHourTo) {
        LoanResource.checkHourTo = checkHourTo;
    }


    @SuppressWarnings("deprecation")
    public boolean isTimeInHourInterval(final int from, final int to) {
        Date d = new Date();
        int hour = d.getHours();

        if (hour >= from && hour <= to) {
            return true;
        } else {
            return false;
        }

    }

    @SuppressWarnings("deprecation")
    public boolean isDateToday(final Date loanDate) {
        Date d = new Date();
        if (loanDate.getYear() == d.getYear()
                && loanDate.getMonth() == d.getMonth()
                && loanDate.getDay() == d.getDay()) {
            return true;
        } else {
            return false;
        }

    }

    public int getIpRequestTodayCount(final String ipAddr) {
        int cnt = 0;
        List<Loan> loans = loanService.getAll();
        for (Loan eachLoan : loans) {
            if (eachLoan.getIpAddr().equals(ipAddr)
                    && isDateToday(eachLoan.getCreateDate())) {
                cnt++;
            }
        }
        return cnt;
    }

    private String checkLoan(final String ipAddr, final Float amount) {
        StringBuilder result = new StringBuilder();
        // 1 check by max amount and hours
        if (amount >= CHECK_MAX_AMOUNT && isTimeInHourInterval(checkHourFrom, checkHourTo)) {
            result.append(REJECT_REASON_BAD_TIME);
        }
        // 2 check by ip addr
        if (getIpRequestTodayCount(ipAddr) >= CHECK_MAX_IP_DAY_REQUESTS) {
            result.append(REJECT_REASON_IP_DAY_ATTEMPTS);
        }

        return result.toString();
    }

    @RequestMapping(method=RequestMethod.GET, value="/createLoan")
    public @ResponseBody String createLoan(@PathVariable final String amount,
    		@PathVariable final String date,
    		@PathVariable final Integer userId) {
        String ipAddr = "";
        try {
        	/*
            Message message = PhaseInterceptorChain.getCurrentMessage();
            HttpServletRequest request = (HttpServletRequest) message
                    .get(AbstractHTTPDestination.HTTP_REQUEST);
            ipAddr = request.getLocalAddr();
            */
        } catch (NullPointerException e) { // for working with JUnit
            // nothing - checking for JUnit
        }

        Loan loan = new Loan();
        // checks for input params
        StringBuilder error = new StringBuilder();
        if (amount == null || amount.length() < 1) {
            error.append(ERROR_AMOUNT_IS_NULL);
        }
        if (date == null || amount.length() < 1) {
            error.append(ERROR_DATE_IS_NULL);
        }
        try {
            loan.setAmount(Float.parseFloat(amount));
        } catch (Exception e) {
            error.append(ERROR_WRONG_AMOUNT_FORMAT);
        }
        try {
            loan.setDeadLine(FORMATTER.parse(date));
        } catch (Exception e) {
            error.append(ERROR_WRONG_DATE_FORMAT);
        }

        User user = userService.get(userId);
        if (user == null) {
            error.append(ERROR_WRONG_USER_ID);
        }

        // main check procedure
        error.append(checkLoan(ipAddr, loan.getAmount()));

        if (error.length() == 0) {
            loan.setRate(DEFAULT_RATE);
            loan.setIpAddr(ipAddr);
            loan.setState(Loan.STATE.OPEN.toString());
            loan.setCreateDate(new Date());
            loan.setUser(user);
            addEntity(loan);
            return RESULT_OK;
        } else {
            return RESULT_REJECTED + error.toString();
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/extendLoan")
    public @ResponseBody String extendloan(@PathVariable("id") final Integer id) {
        loanService.extendLoan(id);
        return RESULT_EXTENDED;
    }

}
