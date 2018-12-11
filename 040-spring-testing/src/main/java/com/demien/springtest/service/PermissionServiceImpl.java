package com.demien.springtest.service;

import com.demien.springtest.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("PermissionService-main")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    //@Qualifier("UserGroupService-main")
    private UserGroupService userGroupService;

    @Override
    public boolean hasAccess(User user, String groupName) {
        log.info("calling userGroupService");
        return userGroupService.getUserGroups(user).stream().anyMatch(group -> group.getName().equals(groupName));
    }
}