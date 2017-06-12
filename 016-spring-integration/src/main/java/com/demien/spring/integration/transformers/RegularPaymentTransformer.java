package com.demien.spring.integration.transformers;

import com.demien.spring.integration.dto.Payment;
import org.springframework.integration.annotation.Transformer;

public class RegularPaymentTransformer implements PaymentTransformer {

    @Override
    @Transformer
    public String paymentToSting(Payment payment) {
        return payment.getDescription()+" "+payment.getAmount();
    }


}
