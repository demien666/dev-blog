package com.demien.ssecurity.testapp.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AbstractModel<T, PK extends Serializable> {

    protected static SessionFactory sessionFactory;
    protected Session session;
    protected boolean autoCommit = true;

    private Class<T> cl;

    public AbstractModel() {
    }

    public AbstractModel(Class<T> cl) {
        this.cl = cl;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void setGlobalSessionFactory(SessionFactory sessionFactory) {
        AbstractModel.sessionFactory = sessionFactory;
    }


    protected Session getCurrentSession() {

        try {
            session = getSessionFactory().getCurrentSession();
        } catch (Exception e) {

        }
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    public Session getNewSession() {
        return getSessionFactory().openSession();
    }

    protected void setSession(Session session) {
        this.session = session;
    }

    protected void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void checkAndStartTransaction(Session session) {
        if (autoCommit) {
            session.beginTransaction();
        }
    }

    public void checkAndCommitTransaction(Session session) {
        if (autoCommit) {
            session.getTransaction().commit();
        }
    }

    public T get(PK id) {
        Session session = getCurrentSession();
        checkAndStartTransaction(session);
        T element = (T) session.get(cl, id);
        checkAndCommitTransaction(session);
        return element;
    }

    public PK save() {
        Session session = getCurrentSession();
        checkAndStartTransaction(session);
        PK result = (PK) session.save(this);
        checkAndCommitTransaction(session);
        return result;
    }

    public void update() {
        Session session = getCurrentSession();
        checkAndStartTransaction(session);
        session.update(this);
        checkAndCommitTransaction(session);
    }

    public void delete() {
        Session session = getCurrentSession();
        checkAndStartTransaction(session);
        session.delete(this);
        checkAndCommitTransaction(session);
    }

    public List<T> query(String hsql, Map<String, Object> params) {

        Session session = getCurrentSession();
        checkAndStartTransaction(session);

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
        } else {
            query.executeUpdate();
        }
        checkAndCommitTransaction(session);
        return result;
    }


    public List<T> getAll() {
        return query("from " + cl.getName(), null);
    }

    public void deleteAll() {
        query("delete from " + cl.getName(), null);

    }

}
