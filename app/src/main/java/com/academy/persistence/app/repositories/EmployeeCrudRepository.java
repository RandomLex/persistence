package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
    @Override
    List<Employee> findAll();
}
