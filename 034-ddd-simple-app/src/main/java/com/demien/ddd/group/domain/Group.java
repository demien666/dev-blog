package com.demien.ddd.group.domain;

import com.demien.ddd.application.SystemContext;
import com.demien.ddd.base.BaseEntity;
import com.demien.ddd.base.annotations.DDDEntity;
import com.demien.ddd.group.events.GroupEnableEvent;

@DDDEntity
public class Group extends BaseEntity {

    private String name;
    private boolean enabled = true;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        this.enabled = true;
        SystemContext.dispatchEvent(new GroupEnableEvent(this.getId(), this.enabled));
    }

    public void disable() {
        this.enabled = false;
        SystemContext.dispatchEvent(new GroupEnableEvent(this.getId(), this.enabled));
    }
}
