package com.demien.springtest.service;

import com.demien.springtest.domain.Group;
import com.demien.springtest.domain.User;
import java.util.List;

public interface UserGroupService {

    List<Group> getUserGroups(User user);

}
