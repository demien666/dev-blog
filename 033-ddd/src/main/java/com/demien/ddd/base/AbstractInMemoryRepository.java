package com.demien.ddd.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AbstractInMemoryRepository<T extends AbstractEntity> {

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

    public List<T> filter(Predicate<T> predicate) {
        return storage.entrySet().stream().filter(e -> predicate.test(e.getValue())).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
