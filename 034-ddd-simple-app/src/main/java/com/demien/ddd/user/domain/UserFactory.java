package com.demien.ddd.user.domain;

import com.demien.ddd.application.exceptons.DomainException;
import com.demien.ddd.application.annotations.DDDFactory;
import com.demien.ddd.group.domain.Group;
import com.demien.ddd.group.infrastructure.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DDDFactory
@Component
public class UserFactory {

    private final GroupRepository groupRepository;

    @Autowired
    public UserFactory(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public User create(String name, Group group) throws DomainException {
        if (group == null) throw new DomainException("Group is not found!");
        if (!group.isEnabled()) throw new DomainException("Group is disabled!");
        if (name == null || name.length() == 0) throw new DomainException("User name can not be empty!");

        return new User(name, UserStatus.ENABLED, group);
    }


    public User create(String name, long groupId) throws DomainException {
        final Group group = groupRepository.findById(groupId);
        if (group == null) throw new DomainException("Group is not found!");
        return create(name, group);
    }

}
