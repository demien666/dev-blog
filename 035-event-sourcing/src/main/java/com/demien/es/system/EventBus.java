package com.demien.es.system;

import com.demien.es.system.event.Event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class EventBus {

    private List<Event> events = new LinkedList<>();
    private Map<String, Event> guidIndex = new HashMap<>();

    private Map<Class<?>, EventHandler> handlers = new HashMap<>();


    public List<Event> getEvents() {
        return events;
    }

    public void registerHandler(Class<?> cl, EventHandler<?> handler) {
        handlers.put(cl, handler);
    }

    public String getGUID() {
        return java.util.UUID.randomUUID().toString();
    }

    public String dispatchEvent(Event event) {
        events.add(event);
        final String GUID = getGUID();
        event.setGUID(GUID);
        CompletableFuture.supplyAsync(() -> {
            EventHandler handler = handlers.get(event.getClass());
            if (handler != null) {
                handler.processEvent(event);
            }
            return null;
        });

        guidIndex.put(GUID, event);

        return GUID;
    }

    public Event getEventByGUID(String guid) {
        return guidIndex.get(guid);
    }

}
