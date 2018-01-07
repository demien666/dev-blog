package com.demien.es.system;

import com.demien.es.domain.Entity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityFinder<T extends Entity> {

    private final EventBus eventBus;
    private final Class<T> cl;


    public EntityFinder(EventBus eventBus, Class<T> cl) {
        this.eventBus = eventBus;
        this.cl = cl;
    }

    public List<T> getAll() {
        return eventBus.getEvents().stream()
                .filter(event -> event.getResponse().getClass().equals(cl))
                .map(event -> (T) event.getResponse())
                .collect(Collectors.toList());
    }

    public List<T> findEntityById(long id) {
        return eventBus.getEvents().stream()
                .filter(event -> event.getResponse().getClass().equals(cl))
                .map(event -> (T) event.getResponse())
                .filter(resp -> resp.getId() == id)
                .collect(Collectors.toList());

    }
}
