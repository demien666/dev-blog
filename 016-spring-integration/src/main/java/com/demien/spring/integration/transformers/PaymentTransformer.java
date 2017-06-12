package com.demien.spring.integration.transformers;

import com.demien.spring.integration.dto.Payment;

public interface PaymentTransformer {
    String paymentToSting(Payment payment);
}
