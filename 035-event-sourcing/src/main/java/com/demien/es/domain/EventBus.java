package com.demien.es.domain;

import java.util.LinkedList;
import java.util.List;

public class EventBus {

    private List<Event> events = new LinkedList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void dispatchEvent(Event event) {
        events.add(event);
    }

}
