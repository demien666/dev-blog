package com.demien.ddd.group.events;

import com.demien.ddd.base.BaseEvent;
import com.demien.ddd.base.annotations.DDDEvent;

@DDDEvent
public class GroupEnableEvent extends BaseEvent {
    private final boolean groupEnabled;

    public GroupEnableEvent(Long groupId, boolean groupEnabled) {
        super(groupId);
        this.groupEnabled = groupEnabled;
    }

    public boolean isGroupEnabled() {
        return groupEnabled;
    }
}
