package com.academy.persistence.app.services;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    List<T> getAll();

    Optional<T> get(Integer id);

    T save(T entity);

    Optional<T> delete(Integer id);
}
