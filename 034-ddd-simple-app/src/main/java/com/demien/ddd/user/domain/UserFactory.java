package com.demien.ddd.user.domain;

import com.demien.ddd.base.annotations.DDDFactory;
import com.demien.ddd.application.SystemContext;
import com.demien.ddd.group.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DDDFactory
@Component
public class UserFactory {

    private SystemContext systemContext;

    @Autowired
    public UserFactory(SystemContext systemContext) {
        this.systemContext = systemContext;
    }

    public User create(String name, Group group) {
        return new User(name, UserStatus.ENABLED, group, systemContext.getCurrentUser());
    }

}
