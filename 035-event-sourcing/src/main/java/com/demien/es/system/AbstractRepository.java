package com.demien.es.system;

import com.demien.es.domain.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AbstractRepository<T extends Entity> {

    private Map<Long, T> storage = new HashMap<Long, T>();
    private Long maxId = 0L;

    public T save(T entity) {
        if (entity.getId() == 0) {
            maxId++;
            entity.setId(maxId);
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    public T findById(Long id) {
        return storage.get(id);
    }

    public void delete(Long id) {
        storage.remove(id);
    }

    public List<T> filter(Predicate<T> predicate) {
        return null;
        //return storage.entrySet().stream().filter(e -> predicate.test(e.getValue())).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
