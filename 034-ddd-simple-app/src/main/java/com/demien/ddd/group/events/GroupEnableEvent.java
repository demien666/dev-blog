package com.demien.ddd.group.events;

import com.demien.ddd.application.base.BaseEvent;
import com.demien.ddd.application.annotations.DDDEvent;

@DDDEvent
public class GroupEnableEvent extends BaseEvent {
    private final long groupId;
    private final boolean groupEnabled;

    public GroupEnableEvent(Long groupId, boolean groupEnabled) {
        this.groupId = groupId;
        this.groupEnabled = groupEnabled;
    }

    public long getGroupId() {
        return groupId;
    }

    public boolean isGroupEnabled() {
        return groupEnabled;
    }
}
