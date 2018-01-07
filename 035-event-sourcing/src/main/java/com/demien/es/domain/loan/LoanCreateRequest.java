package com.demien.es.domain.loan;

public class LoanCreateRequest {

    private final String clientId;
    private final String amount;
    private final String startDate;
    private final String endDate;


    public LoanCreateRequest(String clientId, String amount, String startDate, String endDate) {
        this.clientId = clientId;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAmount() {
        return amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
