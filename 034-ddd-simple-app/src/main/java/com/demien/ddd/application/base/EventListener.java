package com.demien.ddd.application.base;

public interface EventListener {
    void handleEvent(BaseEvent event);
}
