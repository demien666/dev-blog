package com.demien.testloan.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDAO<T> {
    public T get(Integer id);
    public T save(T object);
    public void update(T object);
    public void delete(T object);
    public List<T> query(String hsql, Map<String, Object> params);

}
