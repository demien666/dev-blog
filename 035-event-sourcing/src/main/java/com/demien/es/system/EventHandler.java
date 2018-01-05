package com.demien.es.system;

import com.demien.es.system.event.Event;

public interface EventHandler<T extends Event> {

    void processEvent(T event);

}
