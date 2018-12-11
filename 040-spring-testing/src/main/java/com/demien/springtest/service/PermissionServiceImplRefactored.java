package com.demien.springtest.service;

import com.demien.springtest.domain.Group;
import com.demien.springtest.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Qualifier("PermissionService-main")
public class PermissionServiceImplRefactored implements PermissionService {

    @Autowired
    //@Qualifier("UserGroupService-main")
    private UserGroupService userGroupService;

    public boolean contains(List<Group> groups, String groupName) {
        return groups.stream().anyMatch(group -> group.getName().equals(groupName));
    }

    @Override
    public boolean hasAccess(User user, String groupName) {
        log.info("calling userGroupService");
        return contains(userGroupService.getUserGroups(user), groupName);
    }


}