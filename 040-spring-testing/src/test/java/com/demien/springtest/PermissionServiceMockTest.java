package com.demien.springtest;

import com.demien.springtest.domain.Group;
import com.demien.springtest.domain.User;
import com.demien.springtest.service.PermissionService;
import com.demien.springtest.service.PermissionServiceImpl;
import com.demien.springtest.service.UserGroupService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class PermissionServiceMockTest {

    @Mock
    private UserGroupService userGroupService;

    @InjectMocks
    private PermissionService permissionService = new PermissionServiceImpl();

    final User dummyUser = new User();

    @Before
    public void init() {
        BDDMockito.given(userGroupService.getUserGroups(Matchers.any(User.class))).willReturn(
                Arrays.asList(new Group("mockGroup1"), new Group("mockGroup2"), new Group("mockGroup3") )
        );
    }

    @Test
    public void test() {
        Assert.assertTrue(permissionService.hasAccess(dummyUser, "mockGroup2"));
        Assert.assertFalse(permissionService.hasAccess(dummyUser, "newGroup"));
    }

}
