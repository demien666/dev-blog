package com.demien.testloan.integration.test;

import static com.demien.testloan.AppConst.RESULT_OK;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.demien.testloan.AppConfig;
import com.demien.testloan.domain.User;
import com.demien.testloan.rest.UserResource;
import com.demien.testloan.service.IUserService;
import com.demien.testloan.utils.ObjectDataPopulator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserIT extends BaseIT<User> {

    @Autowired
    private IUserService userService;

    private User testUser;

    public UserIT() {
        super(UserResource.SERVICE_MAIN, User.class);
    }

    @Before
    public void clear() throws Exception {
        userService.deleteAll();
        testUser = new User();
        ObjectDataPopulator.populate(testUser);
    }

    private List<User> getAllUsers() {
        return userService.getAll();
    }

    private String createUser(final String login, final String password) throws Exception {
        String result = client.sendGetStringResult(SERVICE_URL
                + UserResource.SERVICE_CREATE_USER + "?login=" + login
                + "&password=" + password);
        assertNotNull(result);
        return result;
    }

    @Test
    public void createUserTest() throws Exception {
        int cnt = getAllUsers().size();
        assertEquals(cnt, 0);

        String result = createUser(testUser.getLogin(), testUser.getPassword());
        assertEquals(result, RESULT_OK);
        List<User> users = getAllUsers();
        assertEquals(1, users.size());
        User returnUser = users.get(0);
        assertEquals(testUser.getLogin(), returnUser.getLogin());
        assertEquals(testUser.getPassword(), returnUser.getPassword());
    }

    @Test
    public void updateUserTest() throws Exception {
        // create user
        String result = createUser(testUser.getLogin(), testUser.getPassword());
        assertEquals(result, RESULT_OK);
        List<User> users = getAllUsers();
        assertEquals(1, users.size());
        User returnUser = users.get(0);
        User newUser = new User();
        ObjectDataPopulator.populate(newUser);
        returnUser.setLogin(newUser.getLogin());
        returnUser.setPassword(newUser.getPassword());
        // update user
        result = client.sendGetStringResult(SERVICE_URL
                + UserResource.SERVICE_UPDATE_USER + "?id=" + returnUser.getId()
                + "&login=" + returnUser.getLogin() + "&password="
                + returnUser.getPassword());
        assertNotNull(result);
        // process results
        users = getAllUsers();
        assertEquals(1, users.size());
        User returnUser2 = users.get(0);

        assertEquals(newUser.getLogin(), returnUser2.getLogin());
        assertEquals(newUser.getPassword(), returnUser2.getPassword());
    }

    @Test
    public void deleteUser() throws Exception {
        // create user
        String result = createUser(testUser.getLogin(), testUser.getPassword());
        assertEquals(result, RESULT_OK);
        List<User> users = getAllUsers();
        assertEquals(1, users.size());
        User returnUser = users.get(0);
        // delete user
        result = client.sendGetStringResult(SERVICE_URL
                + UserResource.SERVICE_DELETE_USER + "?id=" + returnUser.getId());
        assertNotNull(result);
        users = getAllUsers();
        assertEquals(0, users.size());

    }

}
