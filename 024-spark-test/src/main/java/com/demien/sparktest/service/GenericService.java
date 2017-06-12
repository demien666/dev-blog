package com.demien.sparktest.service;

import com.demien.sparktest.domain.IPersistable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericService<T extends IPersistable> {
    private Long maxId=0L;

    private Map<Long, T> storage=new HashMap<Long, T>();

    public void clearStorage() {
        storage.clear();
    }

    public T getById(Long id) {
        return storage.get(id);
    }

    public T add(T element) {
        Long id=element.getId();
        if (id==null) {
            maxId++;
            element.setId(maxId);
        } else {
            if (maxId.longValue()<id.longValue()) {
                maxId=id+1;
            }
        }
        return update(element);
    }

    public List<T> getAll() {
        List<T> result=new ArrayList<T>();
        for (T element:storage.values()) {
            result.add(element);
        }
        return result;
    }

    public T update(T element) {
        storage.put(element.getId(), element);
        return storage.get(element.getId());
    }

    public void delete(T element) {
        storage.remove(element.getId());
    }

}
