package com.demien.ddd.user.domain;

import com.demien.ddd.application.base.BaseEntity;
import com.demien.ddd.application.annotations.DDDAggregateRoot;
import com.demien.ddd.application.annotations.DDDEntity;
import com.demien.ddd.group.domain.Group;
import com.demien.ddd.user.events.UserGroupMessageEvent;

import java.util.Set;

@DDDEntity
@DDDAggregateRoot
public class User extends BaseEntity {

    private String name;
    private Set<ContactInfo> contactInfos;
    private UserStatus status;
    private Group group;

    public User(String name, UserStatus status, Group group) {
        this.name = name;
        this.status = status;
        this.group = group;
        getEventBus().dispatchEvent(new UserGroupMessageEvent(group.getId(), "There is a new user in your group:" + name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfo[] getContactInfos() {
        return (ContactInfo[]) contactInfos.toArray();
    }

    public void addContactInfo(ContactInfo contactInfo) {
        this.contactInfos.add(contactInfo);
    }

    public void removeContactInfo(ContactInfo contactInfo) {
        this.contactInfos.remove(contactInfo);
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

}
