package com.academy.persistence.app.repositories;

import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.app.exceptions.ApplicationException;
import com.academy.persistence.model.Employee;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee> {
    default List<EmployeeDto> findAllDto() {
        throw new ApplicationException("Not implemented");
    }

    default List<Employee> findAllFetch() {
        throw new ApplicationException("Not implemented");
    }
}
