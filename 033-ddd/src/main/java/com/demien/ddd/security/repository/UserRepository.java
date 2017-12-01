package com.demien.ddd.security.repository;

import com.demien.ddd.security.domain.User;
import com.demien.ddd.shared.annotations.Repository;
import com.demien.ddd.shared.repository.AbstractRepository;

@Repository
public class UserRepository extends AbstractRepository<User> {
}
