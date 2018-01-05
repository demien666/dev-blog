package com.demien.es.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class VOQuery<T> {

    private final EventBus eventBus;

    public VOQuery(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public List<T> queryByEntityType(Class<T> cl, Predicate<T> predicate) {
        Map<String, Object> result = new HashMap<>();
        eventBus.getEvents().forEach(event -> {
            if (event instanceof VOChangeEvent) {
                VOChangeEvent<T> voe = (VOChangeEvent) event;
                if (voe.getVo().getClass().getTypeName().equals(cl.getTypeName())) {
                    if (predicate == null || predicate.test(voe.getVo())) result.put(voe.getGUID(), voe.getVo());
                }

            }
        });
        return (List<T>) Arrays.asList(result.values().toArray());
    }

    public List<T> queryByEntityType(Class<T> cl) {
        return queryByEntityType(cl, null);
    }

}
