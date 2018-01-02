package com.demien.ddd.base;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {
    T save(T entity);

    T findById(Long id);

    void delete(Long id);

    List<T> filter(Predicate<T> predicate);
}
