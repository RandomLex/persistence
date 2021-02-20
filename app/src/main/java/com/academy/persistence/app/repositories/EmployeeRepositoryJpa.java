package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.TypedQuery;

@Slf4j
public class EmployeeRepositoryJpa extends AbstractRepositoryJpa<Employee> implements EmployeeRepository {
    private static volatile EmployeeRepositoryJpa instance;

    private EmployeeRepositoryJpa() {
    }

    public static EmployeeRepositoryJpa getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepositoryJpa.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryJpa();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<Employee> findAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Employee", Employee.class);
    }

    @Override
    protected TypedQuery<Employee> findOneQuery() {
        return helper.getEntityManager()
                .createQuery("from Employee where id = :id", Employee.class);
    }
}
