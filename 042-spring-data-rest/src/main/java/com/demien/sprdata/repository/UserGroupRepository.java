package com.demien.sprdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.demien.sprdata.domain.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

	Iterable<UserGroup> findAll();

	long count();

}
