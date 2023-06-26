package com.example.applicationonlineshop.dao;

import com.example.applicationonlineshop.entity.Auditable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.NonNull;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseDAO<T extends Auditable, ID extends Serializable> {
    protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
    protected static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    protected BaseDAO() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<T> t = (Class<T>) actualTypeArguments[0];
        this.persistentClass = t;
    }

    protected void begin() {
        entityManager.getTransaction().begin();
    }

    protected void rollback() {
        entityManager.getTransaction().rollback();
    }

    protected void commit() {
        entityManager.getTransaction().commit();
    }

    public T save(T t) {
        begin();
        entityManager.persist(t);
        commit();
        return t;
    }

    public T update(T t) {
        begin();
        entityManager.merge(t);
        commit();
        return t;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll(T t) {
        begin();
        List<T> singleResult = (List<T>) entityManager.createQuery("from " + persistentClass.getSimpleName(), persistentClass).getSingleResult();
        commit();
        return singleResult;
    }


    public T findById(@NonNull ID id) {
        begin();
        T t = entityManager.find(persistentClass, id);
        commit();
        return t;
    }

    public boolean deleteById(@NonNull ID id) {
        begin();
        entityManager.createQuery("delete from " + persistentClass.getSimpleName() + " where id:=id")
                .setParameter(1, id).executeUpdate();
        commit();
        return true;
    }
}
