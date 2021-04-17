package com.academy.persistence.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;

@Repository
public class AbstractRepositorySpringJpa<T> implements com.academy.persistence.app.repositories.Repository<T> {

    protected Class<T> clazz;
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public List<T> findAll() {
        List<T> resultList = em.createQuery("from " + clazz.getName(), clazz).getResultList();
        return resultList;
    }

    @Override
    public Optional<T> find(Integer id) {
        return Optional.of(em.find(clazz, id));
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Optional<T> remove(Integer id) {
        Optional<T> entity = find(id);
        if (entity.isPresent()) {
            em.remove(entity);
            return entity;
        }
        return Optional.empty();
    }

}
