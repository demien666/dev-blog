package com.demien.main.loan.domain.loan;

import com.demien.ddd.base.Repository;
import com.demien.main.loan.domain.client.Client;

import java.util.List;

public interface LoanRepository extends Repository<Loan> {
    List<Loan> getByClient(Client client);
}
