package com.demien.ddd;

import com.demien.ddd.application.exceptons.DomainException;
import com.demien.ddd.group.domain.Group;
import com.demien.ddd.group.infrastructure.GroupService;
import com.demien.ddd.user.domain.User;
import com.demien.ddd.user.domain.UserStatus;
import com.demien.ddd.user.infrastructure.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class MainTest {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Test
    public void test() throws DomainException {

        Group group1 = groupService.create(new Group("Test1"));
        Group group2 = groupService.create(new Group("Test2"));

        User user11 = userService.create("1.1", group1.getId());
        User user21 = userService.create("2.1", group2.getId());
        User user22 = userService.create("2.2", group2.getId());

        group2.disable();
        Assert.assertTrue(user11.getStatus() == UserStatus.ENABLED);
        Assert.assertTrue(user21.getStatus() == UserStatus.DISABLED);
        Assert.assertTrue(user22.getStatus() == UserStatus.DISABLED);


    }


}
