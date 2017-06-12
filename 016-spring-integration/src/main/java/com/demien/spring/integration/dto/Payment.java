package com.demien.spring.integration.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    BigDecimal amount;
    String description;

    public Payment(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    public boolean isVip() {
        if (amount!=null && amount.compareTo(new BigDecimal(10000))==1) {
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
