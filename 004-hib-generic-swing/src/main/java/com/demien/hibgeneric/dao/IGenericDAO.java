package com.demien.hibgeneric.dao;

import java.util.List;
import java.util.Map;

public interface IGenericDAO<T> {
    public T get(Class<T> cl, Integer id) throws DAOException;
    public T save(T object) throws DAOException;
    public void update(T object) throws DAOException;
    public void delete(T object) throws DAOException;
    public List<T> query(String hsql, Map<String, Object> params) throws DAOException;

}
