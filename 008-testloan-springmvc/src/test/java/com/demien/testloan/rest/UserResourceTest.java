package com.demien.testloan.rest;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.demien.testloan.domain.User;
import com.demien.testloan.service.IUserService;
import com.demien.testloan.utils.ObjectDataPopulator;

public class UserResourceTest extends BaseResourceTest<User> {

    private static IUserService userService;

    private UserResource resource;
    private User testUser;


    public UserResourceTest() {
        super(User.class);
    }

    @Before
    public void init() throws Exception {
        userService = mock(IUserService.class);
        resource = new UserResource(userService);
        testUser=new User();
        ObjectDataPopulator.populate(testUser);
    }


    @Test
    public void createUserTest() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        resource.createUser(testUser.getLogin(), testUser.getPassword());
        verify(userService).save(captor.capture());
        assertEquals(testUser.getLogin(), captor.getValue().getLogin());
        assertEquals(testUser.getPassword(), captor.getValue().getPassword());
    }

    @Test
    public void updateUserTest() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        resource.updateUser(testUser.getId(), testUser.getLogin(), testUser.getPassword());
        verify(userService).update(captor.capture());
        assertEquals(testUser.getId(), captor.getValue().getId());
        assertEquals(testUser.getLogin(), captor.getValue().getLogin());
        assertEquals(testUser.getPassword(), captor.getValue().getPassword());
    }

    @Test
    public void deleteUserTest() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        resource.deleteUser(testUser.getId());
        verify(userService).delete(captor.capture());
        assertEquals(testUser.getId(), captor.getValue().getId());
    }



}
