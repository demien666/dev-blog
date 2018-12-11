package com.demien.springtest;

import com.demien.springtest.domain.Group;
import com.demien.springtest.domain.User;
import com.demien.springtest.service.PermissionService;
import com.demien.springtest.service.PermissionServiceImpl;
import com.demien.springtest.service.UserGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PermissionServiceStubTest.TestConfig.class)
public class PermissionServiceStubTest {

    @Configuration
    static class TestConfig {

        @Bean
        @Qualifier("UserGroupService-main")
        public UserGroupService testUserGroupService() {
            return new UserGroupServiceTestImpl();
        }

        @Bean
        @Qualifier("PermissionService-test")
        public PermissionService testPermissionService() {
            return new PermissionServiceImpl();
        }

    }

    static class UserGroupServiceTestImpl implements UserGroupService {

        @Override
        public List<Group> getUserGroups(User user) {
            return Arrays.asList(
                    new Group("stubGroup1"), new Group("stubGroup2")
            );
        }
    }

    final User dummyUser = new User();

    @Autowired
    @Qualifier("PermissionService-test")
    private PermissionService permissionService;


    @Test
    public void calculateSum() {
        assertTrue(permissionService.hasAccess(dummyUser, "stubGroup2"));
        assertFalse(permissionService.hasAccess(dummyUser, "stubGroup5"));
    }

}
