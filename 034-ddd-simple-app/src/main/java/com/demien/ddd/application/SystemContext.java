package com.demien.ddd.application;

import com.demien.ddd.base.BaseEvent;
import com.demien.ddd.base.BaseEventListener;
import com.demien.ddd.group.domain.Group;
import com.demien.ddd.user.domain.User;
import com.demien.ddd.user.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemContext {

    private final static User SYSTEM_USER = new User("SYSTEM", UserStatus.ENABLED, null, null);

    private static List<BaseEventListener<?>> eventListeners;

    @Autowired
    public SystemContext(List<BaseEventListener<?>> eventListeners) {
        this.eventListeners = eventListeners;
    }


    public User getCurrentUser() {
        return SYSTEM_USER;
    }

    public static void dispatchEvent(BaseEvent event) {
        eventListeners.stream().forEach(listener -> listener.handleEvent(event));
    }
}
