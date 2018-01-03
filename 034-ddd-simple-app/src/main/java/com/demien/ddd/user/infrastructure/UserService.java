package com.demien.ddd.user.infrastructure;

import com.demien.ddd.base.annotations.DDDService;
import com.demien.ddd.group.domain.Group;
import com.demien.ddd.group.infrastructure.GroupRepository;
import com.demien.ddd.user.domain.User;
import com.demien.ddd.user.domain.UserFactory;
import com.demien.ddd.user.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DDDService
@Component
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, GroupRepository groupRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userFactory = userFactory;
    }

    public User createUser(String name, long groupId) {
        final Group group = groupRepository.findById(groupId);
        if (!group.isEnabled()) throw new RuntimeException("Group is disabled!");
        User user = userFactory.create(name, group);
        userRepository.save(user);
        return user;
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user.getId());
    }

    public void enableDisableUsersByGroup(Long groupId, boolean enabled) {
        this.userRepository.getUsersByGroupId(groupId).forEach(user -> user.setStatus(enabled ? UserStatus.ENABLED : UserStatus.DISABLED));


    }
}
