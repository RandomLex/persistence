package com.academy.persistence.app.services;

import com.academy.persistence.app.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AbstractService<T> implements ServiceInterface<T> {

    protected Repository<T> repository;


    protected TransactionTemplate transactionTemplate;

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<T> getAll() {
        return transactionTemplate.execute(transactionalStatus -> {
            try {
                return repository.findAll();
            } catch (Exception e) {
                transactionalStatus.setRollbackOnly();
            }
            return null;
        });
    }

    @Override
    public Optional<T> get(Integer id) {
        return repository.find(id);
    }

    @Override
    public T save(T entity) {
        return transactionTemplate.execute(transactionalStatus -> {
            try {
                return repository.save(entity);
            } catch (Exception e) {
                transactionalStatus.setRollbackOnly();
            }
            return null;
        });
    }

    @Override
    public Optional<T> delete(Integer id) {
        return Optional.empty();
    }
}
