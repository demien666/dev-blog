package com.demien.swtest.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public abstract class AbstractService<T> {

    private long id;
    private Map<Long,T> storage = new HashMap<>();
    private BiFunction<T, Long, T> idSetter;

    public AbstractService(BiFunction<T, Long, T> idSetter) {
        this.idSetter = idSetter;
    }


    public T add(T entity) {
        id++;
        T result =  idSetter.apply(entity, id);
        storage.put(id, result);
        return result;

    }

    public T get(Long id) {
        return storage.get(id);
    }

    public void update(Long id, T entity) {
        storage.put(id, entity);
    }

    public void delete(Long id) {
        storage.put(id, null);
    }
}
