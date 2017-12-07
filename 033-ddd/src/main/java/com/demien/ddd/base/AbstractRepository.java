package com.demien.ddd.base;

import java.util.HashMap;
import java.util.Map;

public class AbstractRepository<T extends AbstractEntity> {

    private Map<Long, T> storage = new HashMap<Long, T>();

    public void save(T entity) {
        storage.put(entity.getId(), entity);
    }

    public T findById(Long id) {
        return storage.get(id);
    }

    public void delete(Long id) {
        storage.remove(id);
    }
}
