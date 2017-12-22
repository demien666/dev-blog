package com.demien.main.loan.infrastructure;

import com.demien.ddd.base.AbstractInMemoryRepository;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.loan.domain.loan.Loan;
import com.demien.main.loan.domain.loan.LoanRepository;

import java.util.List;

public class LoanInMemoryRepositoryImpl extends AbstractInMemoryRepository<Loan> implements LoanRepository {
    public List<Loan> getByClient(Client client) {
        return filter(c -> c.getClient().getId().equals(client.getId()));
    }
}
