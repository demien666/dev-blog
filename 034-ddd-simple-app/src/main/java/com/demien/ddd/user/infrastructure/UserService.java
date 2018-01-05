package com.demien.ddd.user.infrastructure;

import com.demien.ddd.application.exceptons.DomainException;
import com.demien.ddd.application.base.BaseService;
import com.demien.ddd.application.annotations.DDDService;
import com.demien.ddd.user.domain.User;
import com.demien.ddd.user.domain.UserFactory;
import com.demien.ddd.user.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DDDService
@Component
public class UserService extends BaseService<User> {

    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory) {
        super(userRepository);

        this.userFactory = userFactory;
    }

    public User create(String name, long groupId) throws DomainException {
        User user = userFactory.create(name, groupId);
        repository.save(user);
        return user;
    }

    public void enableDisableUsersByGroup(Long groupId, boolean enabled) {
        ((UserRepository) repository).getUsersByGroupId(groupId).forEach(user -> {
            user.setStatus(enabled ? UserStatus.ENABLED : UserStatus.DISABLED);
            repository.save(user);
        });
    }
}
