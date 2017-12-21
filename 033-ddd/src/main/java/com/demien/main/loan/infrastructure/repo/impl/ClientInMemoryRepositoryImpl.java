package com.demien.main.loan.infrastructure.repo.impl;

import com.demien.ddd.annotations.Repository;
import com.demien.ddd.base.AbstractInMemoryRepository;
import com.demien.main.loan.domain.client.Client;
import com.demien.main.loan.domain.client.ClientRepository;

@Repository
public class ClientInMemoryRepositoryImpl extends AbstractInMemoryRepository<Client> implements ClientRepository {
}
