package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import org.springframework.stereotype.Repository;

@Repository("springJpa")
public class EmployeeRepositorySpringJpa extends AbstractRepositorySpringJpa<Employee> implements EmployeeRepository {

    public EmployeeRepositorySpringJpa() {
        clazz = Employee.class;
    }
}
