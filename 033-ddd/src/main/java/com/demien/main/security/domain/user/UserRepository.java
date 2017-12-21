package com.demien.main.security.domain.user;

import com.demien.ddd.annotations.Repository;
import com.demien.ddd.base.AbstractInMemoryRepository;

@Repository
public class UserRepository extends AbstractInMemoryRepository<User> {
}
