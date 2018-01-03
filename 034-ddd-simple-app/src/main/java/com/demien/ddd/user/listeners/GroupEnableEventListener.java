package com.demien.ddd.user.listeners;

import com.demien.ddd.base.BaseEvent;
import com.demien.ddd.base.BaseEventListener;
import com.demien.ddd.base.annotations.DDDEventListener;
import com.demien.ddd.group.events.GroupEnableEvent;
import com.demien.ddd.user.infrastructure.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DDDEventListener
@Component
public class GroupEnableEventListener extends BaseEventListener<GroupEnableEvent> {

    private UserService userService;

    @Autowired
    public GroupEnableEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handleEvent(BaseEvent event) {
        if (event instanceof GroupEnableEvent) {
            GroupEnableEvent groupEvent = (GroupEnableEvent) event;
            userService.enableDisableUsersByGroup(groupEvent.getEntityId(), groupEvent.isGroupEnabled());
        }
    }
}
