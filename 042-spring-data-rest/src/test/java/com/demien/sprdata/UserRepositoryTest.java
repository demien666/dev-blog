package com.demien.sprdata;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.demien.sprdata.domain.User;
import com.demien.sprdata.repository.UserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TestEntityManager em;

	@Test
	public void sortTest() {
		Sort sort = new Sort(Sort.Direction.ASC, "group_id").and(new Sort(Sort.Direction.DESC, "name"));
		Iterable<User> users = userRepository.findAll(sort);
		User first = users.iterator().next();
		assertTrue(first.getGroup().getId() == 1001L);
		assertTrue(first.getName().equals("Victor"));
	}

	@Test
	public void pagingTest() {
		final PageRequest pageRequest = PageRequest.of(0, 2);
		final Page<User> userPage = userRepository.findAll(pageRequest);
		assertTrue(userPage.getNumberOfElements() == 2);
		assertTrue(userPage.getTotalPages() == 4);

	}

	@Test
	public void findTest() {
		List<User> users = userRepository.findByName("Joe");
		assertTrue(users.size() == 1);
		assertTrue(users.get(0).getName().equals("Joe"));

		Long countByGroupName = userRepository.countByGroupName("ADM");
		assertTrue(countByGroupName == 3L);

		users = userRepository.findByGroupName("ADM");
		assertTrue(users.size() == 3);

		users = userRepository.queryByName("a");
		assertTrue(users.size() == 5);
		assertTrue(users.get(0).getName().contains("a"));

		users = userRepository.namedQueryByName("Charles");
		assertTrue(users.size() == 1);
		assertTrue(users.get(0).getId() == 104L);

		users = userRepository.nativeQueryByName("Mario");
		assertTrue(users.size() == 1);
		assertTrue(users.get(0).getId() == 105L);

	}

}
