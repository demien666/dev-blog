package com.demien.springtest;

import com.demien.springtest.config.MainConfig;
import com.demien.springtest.domain.User;
import com.demien.springtest.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfig.class)
public class PermissionServiceSpringTest {

    final User dummyUser = new User();

    @Autowired
    @Qualifier("PermissionService-main")
    private PermissionService permissionService;


    @Test
    public void calculateSum() {
        assertTrue(permissionService.hasAccess(dummyUser, "first"));
        assertFalse(permissionService.hasAccess(dummyUser, "zero"));
    }
}