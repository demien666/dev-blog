package com.demien.hibgeneric.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
	private Logger LOGGER;
	private SessionFactory sessionFactory;

	public GenericDAOImpl(Class<T> cl, SessionFactory sessionFactory) {
		this.LOGGER = Logger.getLogger(cl.getName() + "GenericDAO");
		this.sessionFactory = sessionFactory;
		if (sessionFactory == null)
			throw new RuntimeException("Session factory is null!!!");
	}

	@Override
	public T get(Class<T> cl, Integer id) throws DAOException {
		LOGGER.trace("STARTED - get");
		T element = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			element = (T) session.get(cl, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new DAOException("Exception in GET", e);
		}
		LOGGER.trace("FINISHED - get");
		return element;
	}

	@Override
	public T save(T object) throws DAOException {
		LOGGER.trace("STARTED - save");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new DAOException("Exception in SAVE", e);
		}
		LOGGER.trace("FINISHED - save");
		return object;
	}

	@Override
	public void update(T object) throws DAOException {
		LOGGER.trace("STARTED - update");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new DAOException("Exception in UPDATE", e);
		}
		LOGGER.trace("FINISHED - update");
	}

	@Override
	public void delete(T object) throws DAOException {
		try {
			LOGGER.trace("STARTED - delete");
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new DAOException("Exception in DELETE", e);
		}
		LOGGER.trace("FINISHED - delete");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String hsql, Map<String, Object> params)
			throws DAOException {
		LOGGER.trace("STARTED - query");
		List<T> result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hsql);
			if (params != null) {
				for (String i : params.keySet()) {
					query.setParameter(i, params.get(i));
				}
			}

			if ((hsql.toUpperCase().indexOf("DELETE") == -1)
					&& (hsql.toUpperCase().indexOf("UPDATE") == -1)
					&& (hsql.toUpperCase().indexOf("INSERT") == -1)) {
				result = query.list();
				LOGGER.trace("FINISHED - query. Result size=" + result.size());
			} else {
				LOGGER.trace("FINISHED - query. ");
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new DAOException("Exception in QUERY", e);
		}
		return result;
	}

}
