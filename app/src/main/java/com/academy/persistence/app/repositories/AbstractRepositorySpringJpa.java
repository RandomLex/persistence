package com.academy.persistence.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class AbstractRepositorySpringJpa<T> implements com.academy.persistence.app.repositories.Repository<T> {

    protected Class<T> clazz;
    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();

    private EntityManagerFactory factory;

    @Autowired
    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<T> findAll() {
        begin();
        List<T> resultList = getEm().createQuery("from " + clazz.getName(), clazz).getResultList();
        commit();
        return resultList;
    }

    @Override
    public Optional<T> find(Integer id) {
        return Optional.of(getEm().find(clazz, id));
    }

    @Override
    public T save(T entity) {
        begin();
        getEm().persist(entity);
        commit();
        return entity;
    }

    @Override
    public Optional<T> remove(Integer id) {
        begin();
        Optional<T> entity = find(id);
        if (entity.isPresent()) {
            getEm().remove(entity);
            commit();
            return entity;
        }
        commit();
        return Optional.empty();
    }

    public EntityManager getEm() {
        if (em.get() == null) {
            em.set(factory.createEntityManager());
        }
        return em.get();
    }

    public void begin() {
        getEm().getTransaction().begin();
    }

    public void commit() {
        getEm().getTransaction().commit();
    }

    public void rollback() {
        getEm().getTransaction().rollback();
    }

}
