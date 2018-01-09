package com.demien.es.system;

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
                .filter(event -> event.getResponse() != null)
                .filter(event -> event.getResponse().getClass().equals(cl))
                .map(event -> (T) event.getResponse())
                .collect(Collectors.toList());
    }

    public List<T> findEntitiesById(long id) {
        return getAll().stream().filter(ent -> ent.getId() == id).collect(Collectors.toList());
    }

    public T findEntityById(long id) {
        List<T> list = findEntitiesById(id);
        return (list.size() > 0) ? list.get(list.size() - 1) : null;
    }
}
