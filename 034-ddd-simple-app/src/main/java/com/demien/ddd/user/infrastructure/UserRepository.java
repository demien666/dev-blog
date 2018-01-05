package com.demien.ddd.user.infrastructure;

import com.demien.ddd.application.base.BaseRepository;
import com.demien.ddd.application.annotations.DDDRepository;
import com.demien.ddd.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@DDDRepository
@Component
public class UserRepository extends BaseRepository<User> {

    public List<User> getUsersByGroupId(long groupId) {
        return filter(user -> user.getGroup().getId() == groupId);
    }

}
