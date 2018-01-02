package com.demien.main.security.domain.user;

import com.demien.ddd.annotations.AggregateRoot;
import com.demien.ddd.base.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

@AggregateRoot
public class User extends AbstractEntity {

    private String login;

    private PasswordInfo passwordInfo;
    private Set<Permission> permissions = new HashSet<Permission>();

    public User(String login, PasswordInfo passwordInfo) {
        this.login = login;
        this.passwordInfo = passwordInfo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PasswordInfo getPasswordInfo() {
        return passwordInfo;
    }

    public void setPasswordInfo(PasswordInfo passwordInfo) {
        this.passwordInfo = passwordInfo;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
