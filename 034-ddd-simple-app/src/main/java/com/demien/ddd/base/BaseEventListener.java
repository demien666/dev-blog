package com.demien.ddd.base;

public abstract class BaseEventListener<T extends BaseEvent> {

    public abstract void handleEvent(BaseEvent event);

}
