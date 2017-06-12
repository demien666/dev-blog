package com.demien.testloan.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDAOImpl<T> implements IBaseDAO<T> {
	private Logger LOGGER;
	private SessionFactory sessionFactory;
	private Class<T> cl;

	public BaseDAOImpl(Class<T> cl, SessionFactory sessionFactory) {
		this.cl=cl;
		this.sessionFactory = sessionFactory;
		this.LOGGER = Logger.getLogger("BaseDAO["+cl.getName()+"]");
	}

	@Override
	public T get(Integer id) {
		LOGGER.info("STARTED - get");
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		@SuppressWarnings("unchecked")
		T element = (T) session.get(cl, id);
		//session.getTransaction().commit();
		LOGGER.info("FINISHED - get");
		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T object) {
		LOGGER.info("STARTED - save");
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		T result=(T)session.save(object);
		//session.getTransaction().commit();
		LOGGER.info("FINISHED - save");
		return result;
	}

	@Override
	public void update(T object) {
		LOGGER.info("STARTED - update");
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		session.update(object);
		//session.getTransaction().commit();
		LOGGER.info("FINISHED - update");
	}

	@Override
	public void delete(T object) {
		LOGGER.info("STARTED - delete");
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		session.delete(object);
		//session.getTransaction().commit();
		LOGGER.info("FINISHED - delete");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String hsql, Map<String, Object> params) {
		LOGGER.info("STARTED - query");
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Query query = session.createQuery(hsql);
		if (params != null) {
			for (String i : params.keySet()) {
				query.setParameter(i, params.get(i));
			}
		}

		List<T> result = null;
		if ((hsql.toUpperCase().indexOf("DELETE") == -1)
				&& (hsql.toUpperCase().indexOf("UPDATE") == -1)
				&& (hsql.toUpperCase().indexOf("INSERT") == -1)) {
			result = query.list();
			LOGGER.info("FINISHED - query. Result size=" + result.size());
		} else {
			query.executeUpdate();
			LOGGER.info("FINISHED - query(executeUpdate). ");
		}
		//session.getTransaction().commit();
		return result;
	}

}
