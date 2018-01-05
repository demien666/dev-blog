package com.demien.ddd.application;

import com.demien.ddd.application.base.BaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demien.ddd.application.base.EventListener;

import java.util.List;

@Component
public class EventBus {

    private final List<EventListener> eventListeners;

    @Autowired
    public EventBus(List<EventListener> eventListeners) {
        this.eventListeners = eventListeners;
    }

    public void dispatchEvent(BaseEvent event) {
        eventListeners.stream().forEach(listener -> listener.handleEvent(event));
    }

}
