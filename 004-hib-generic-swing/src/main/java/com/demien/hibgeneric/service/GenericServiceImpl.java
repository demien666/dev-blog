package com.demien.hibgeneric.service;

import java.util.List;
import java.util.Map;




import org.hibernate.SessionFactory;

import com.demien.hibgeneric.dao.DAOException;
import com.demien.hibgeneric.dao.GenericDAOImpl;
import com.demien.hibgeneric.dao.IGenericDAO;

public class GenericServiceImpl<T> implements IGenericService<T> {
	
	private IGenericDAO<T> dao;
	private Class<T> cl;
	
	
	public GenericServiceImpl(Class<T> cl, SessionFactory sessionFactory) {
		this.cl=cl;
		dao=new GenericDAOImpl<T>(cl, sessionFactory);
	}

	@Override
	public T get(Class<T> cl, Integer id) throws DAOException {
		//LOGGER.trace("STARTED - get");
		return (T) dao.get(cl, id);
	}

	@Override
	public T save(T object) throws DAOException {
		return (T)dao.save(object);
	}

	@Override
	public void update(T object) throws DAOException {
		dao.update(object);		
	}

	@Override
	public void delete(T object) throws DAOException {
		dao.delete(object);		
	}

	@Override
	public List<T> query(String hsql, Map<String, Object> params) throws DAOException {
		return (List<T>)dao.query(hsql, params);
	}

	@Override
	public List<T> getAll() throws DAOException {
		return query("from "+cl.getName(), null);
	}

	@Override
	public void deleteAll() throws DAOException {
		query("delete from "+cl.getName(),null);
		
	}

	@Override
	public Class<T> getElementClass() {
		return cl;
	}

}
