package com.demien.testloan.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.demien.testloan.dao.IBaseDAO;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	
	private IBaseDAO<T> dao;
	private Class<T> cl;
	
	
	public BaseServiceImpl(Class<T> cl, IBaseDAO<T> dao) {
		this.cl=cl;
		this.dao=dao;
	}
	
	
	@Transactional
	@Override
	public T get(Integer id) {
		//LOGGER.trace("STARTED - get");
		return (T) dao.get(id);
	}

	@Transactional
	@Override
	public T save(T object) {
		T result=(T)dao.save(object);
		return result;
	}

	@Transactional
	@Override
	public void update(T object) {
		dao.update(object);		
	}

	@Transactional
	@Override
	public void delete(T object) {
		dao.delete(object);		
	}

	@Transactional
	@Override
	public List<T> query(String hsql, Map<String, Object> params) {
		return (List<T>)dao.query(hsql, params);
	}

	@Transactional
	@Override
	public List<T> getAll() {
		return query("from "+cl.getName(), null);
	}

	@Transactional
	@Override
	public void deleteAll() {
		query("delete from "+cl.getName(),null);
		
	}

}
