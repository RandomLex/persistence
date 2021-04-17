package com.academy.persistence.app.services;

import com.academy.persistence.app.repositories.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AbstractService<T> implements ServiceInterface<T> {

    protected Repository<T> repository;


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Optional<T> get(Integer id) {
        return repository.find(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> delete(Integer id) {
        return Optional.empty();
    }
}
