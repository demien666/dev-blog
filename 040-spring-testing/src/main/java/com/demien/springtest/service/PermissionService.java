package com.demien.springtest.service;

import com.demien.springtest.domain.User;

public interface PermissionService {
    boolean hasAccess(User user, String groupName);
}
