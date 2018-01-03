package com.demien.ddd.user.domain;

import com.demien.ddd.base.BaseEntity;
import com.demien.ddd.base.annotations.DDDAggregateRoot;
import com.demien.ddd.base.annotations.DDDEntity;
import com.demien.ddd.group.domain.Group;

import java.util.Set;

@DDDEntity
@DDDAggregateRoot
public class User extends BaseEntity {

    private String name;
    private Set<ContactInfo> contactInfos;
    private UserStatus status;
    private Group group;
    private User createdBy;

    public User(String name, UserStatus status, Group group, User createdBy) {
        this.name = name;
        this.status = status;
        this.group = group;
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(Set<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
