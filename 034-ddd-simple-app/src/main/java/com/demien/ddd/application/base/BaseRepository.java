package com.demien.ddd.application.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseRepository<T extends BaseEntity> {
    private Map<Long, T> storage = new HashMap<Long, T>();
    private Long maxId = 0L;

    public T save(T entity) {
        maxId++;
        entity.setId(maxId);
        storage.put(entity.getId(), entity);
        return entity;
    }

    public T findById(Long id) {
        return storage.get(id);
    }

    public void update(T entity) {
        storage.put(entity.getId(), entity);
    }

    public void delete(Long id) {
        storage.remove(id);
    }

    public Stream<T> getStream() {
        return storage.entrySet().stream().map(entry -> entry.getValue());
    }

    public List<T> filter(Predicate<T> predicate) {
        return getStream().filter(user -> predicate.test(user)).collect(Collectors.toCollection(ArrayList::new));
    }

}
