package com.daosupport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public abstract class DaoBase<T, ID extends Serializable> {


    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getRealEntityClass();

    protected Session getCurrentSession() {
//        return this.sessionFactory.getCurrentSession();
        return entityManager.unwrap(Session.class);
    }


    public void insert(final T entity) {
        try {
            // 只有當實體是新的（ID 為 null）時才調用 persist
            this.entityManager.persist(entity);
            // 使用 merge 來更新已存在的實體
//            this.entityManager.merge(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    public void update(T entity) {
        //Use JPA　Repository
        this.entityManager.merge(entity);
    }

    public void flushAndClear() {
        Session session = getCurrentSession();
        session.flush();
        session.clear();
    }

//    @Transactional
//    public void delete(T entity) {
//        Transaction transaction = null;
//        try (Session session = getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.delete(entity);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    public void flush() {
        entityManager.flush();
    }

    @Transactional(readOnly = true)
    public T findById(ID id) {
        return getCurrentSession().get(getRealEntityClass(), id);
    }
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + getRealEntityClass().getName()).list();

    }
}