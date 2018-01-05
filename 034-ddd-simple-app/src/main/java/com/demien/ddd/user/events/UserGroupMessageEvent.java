package com.demien.ddd.user.events;

import com.demien.ddd.application.base.BaseEvent;
import com.demien.ddd.application.annotations.DDDEvent;

@DDDEvent
public class UserGroupMessageEvent extends BaseEvent {

    private final long groupId;
    private final String message;

    public UserGroupMessageEvent(long groupId, String message) {
        this.groupId = groupId;
        this.message = message;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getMessage() {
        return message;
    }
}
