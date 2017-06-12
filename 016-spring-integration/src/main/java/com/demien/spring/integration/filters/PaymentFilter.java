package com.demien.spring.integration.filters;

import com.demien.spring.integration.dto.Payment;
import org.springframework.integration.annotation.Filter;

public class PaymentFilter {

    @Filter
    public boolean checkMandatoryFields(Payment payment) {
        if (payment==null || payment.getAmount()==null) {
            System.out.println("REJECTED:"+payment);
            return false;
        } else {
            return true;
        }
    }
}
