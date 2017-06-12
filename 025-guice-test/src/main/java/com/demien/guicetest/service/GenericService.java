package com.demien.guicetest.service;

import com.google.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class GenericService<T, PK> {
    @Inject
    private EntityManagerFactory factory;
    private EntityManager em;

    private Class<T> cl;

    public GenericService(Class<T> cl) {
        this.cl=cl;
    }

    public Class<T> getEntityClass() {
        return cl;
    }

    @FunctionalInterface
    public interface TransactionalOperation{
        void process(Object entity);
    }

    @FunctionalInterface
    public interface TransactionalFunction{
        Object process(Object entity);
    }


    public void doInTransaction(T entity, TransactionalOperation operation) {
        try {
            em=beginTransaction();
            operation.process(entity);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    public T persist(T entity) {
        doInTransaction(entity, em::persist);
        return entity;
    }

    public void remove(T entity) {
        doInTransaction(entity, em::remove);
    }

    public T getById(PK id) {
        em=beginTransaction();
        T result=em.find(cl, id);
        em.close();
        return result;
    }

    public List<T> getAll() {
        em=beginTransaction();
        return em.createQuery("select t from "+cl.getSimpleName()+" t").getResultList();
    }

    public EntityManager beginTransaction() {
        EntityManager em=factory.createEntityManager();
        em.getTransaction().begin();
        return em;
    }


}
