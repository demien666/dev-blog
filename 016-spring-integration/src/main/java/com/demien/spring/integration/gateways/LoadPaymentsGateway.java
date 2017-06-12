package com.demien.spring.integration.gateways;

import com.demien.spring.integration.dto.Payment;

import java.util.Collection;

public interface LoadPaymentsGateway {
    void loadPayments(Collection<Payment> payments);
}
