package com.demien.main.loan.domain.loan;

import com.demien.main.loan.domain.client.Client;

import java.util.List;

public interface LoanRepository {
    List<Loan> getByClient(Client client);
}
