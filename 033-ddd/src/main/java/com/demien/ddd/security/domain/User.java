package com.demien.ddd.security.domain;

import com.demien.ddd.shared.annotations.Entity;
import com.demien.ddd.shared.domain.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AbstractEntity {

    private String login;

    private PasswordInfo passwordInfo;
    private Set<Permission> permissions = new HashSet<Permission>();

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
}
