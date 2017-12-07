package com.demien.main.loan.domain.loan;

import com.demien.ddd.base.AbstractRepository;
import com.demien.main.loan.domain.client.Client;

import java.util.List;

public class LoanRepository extends AbstractRepository<Loan> {
    public List<Loan> getByClient(Client client) {
        return filter(c -> c.getClient().getId().equals(client.getId()));
    }
}
