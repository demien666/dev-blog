package com.demien.main.loan.application.api;

import com.demien.main.security.domain.user.Permission;
import com.demien.main.security.domain.user.User;

public class PermissionException extends RuntimeException {
    private Permission permission;
    private User user;
    private String message;

    public PermissionException(Permission permission, User user, String message) {
        super(message);
        this.permission = permission;
        this.user = user;
        this.message = message;
    }
}
