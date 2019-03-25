package com.demien.sprdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demien.sprdata.domain.User;

//http://localhost:8080/users/
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Iterable<User> findAll();

	long count();

	List<User> findByName(String name);

	Long deleteByName(String name);

	Long countByGroupName(String groupName);

	// find by parent entity : Group
	List<User> findByGroupName(String name);

	// defining custom query
	@Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%', :name, '%') ")
	List<User> queryByName(@Param("name") String name);

	// using named query defined in entity class
	List<User> namedQueryByName(@Param("name") String name);

	@Query(value = "SELECT * FROM User WHERE name = :name ", nativeQuery = true)
	List<User> nativeQueryByName(@Param("name") String name);

}
