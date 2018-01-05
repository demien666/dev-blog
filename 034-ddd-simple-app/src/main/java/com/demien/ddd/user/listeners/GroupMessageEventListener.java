package com.demien.ddd.user.listeners;

import com.demien.ddd.application.base.BaseEvent;
import com.demien.ddd.application.base.EventListener;
import com.demien.ddd.application.annotations.DDDEventListener;
import com.demien.ddd.user.events.UserGroupMessageEvent;
import org.springframework.stereotype.Component;

@DDDEventListener
@Component
public class GroupMessageEventListener implements EventListener {
    @Override
    public void handleEvent(BaseEvent event) {
        if (event instanceof UserGroupMessageEvent) {
            System.out.println(((UserGroupMessageEvent) event).getMessage());
        }
    }
}
