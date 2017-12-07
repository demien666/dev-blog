package com.demien.main.security.domain.user;

import com.demien.ddd.annotations.Factory;

import java.util.Arrays;

@Factory
public class UserFactory {

    public User createUser(String login, String password, Permission... permissions) {
        final PasswordInfo passwordInfo = new PasswordInfo(password);
        final User user = new User(login, passwordInfo);
        Arrays.asList(permissions).forEach(p -> user.getPermissions().add(p));
        return user;
    }

}
