package com.demien.ddd.user.infrastructure;

import com.demien.ddd.base.BaseRepository;
import com.demien.ddd.base.annotations.DDDRepository;
import com.demien.ddd.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DDDRepository
@Component
public class UserRepository extends BaseRepository<User> {

    public List<User> getUsersByGroupId(long groupId) {
        return filter(user -> user.getGroup().getId() == groupId);
    }

}
