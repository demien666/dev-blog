package com.demien.sprdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.demien.sprdata.domain.UserGroup;
import com.demien.sprdata.repository.UserGroupRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserGroupRepositoryTest {

	@Autowired
	private UserGroupRepository groupRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	public void findAllTest() {
		final List<UserGroup> groups = new ArrayList<>();
		groupRepository.findAll().forEach(group -> groups.add(group));
		assertEquals(4, groups.size());
	}

	@Test
	public void checkUserGroupCount() {
		assertEquals(4, groupRepository.count());

	}

	@Test
	public void findOne() {
		Optional<UserGroup> opGroup = groupRepository.findById(1001L);
		assertTrue(opGroup.isPresent());
		assertEquals("ADM", opGroup.get().getName());

		groupRepository.deleteById(1001L);
		opGroup = groupRepository.findById(1001L);
		assertFalse(opGroup.isPresent());

	}

	@Test
	public void createNewTest() {
		final UserGroup newGroup = new UserGroup();
		newGroup.setDescription("Created");
		groupRepository.save(newGroup);
		assertTrue(newGroup.getId() != null);

		em.flush();
		final Optional<UserGroup> loaded = groupRepository.findById(newGroup.getId());
		assertTrue(loaded.isPresent());
		assertTrue(loaded.get().getDescription().equals("Created"));

		groupRepository.deleteById(newGroup.getId());

	}

}
