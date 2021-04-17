package com.academy.persistence.app.services;

import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends ServiceInterface<Employee>{
    List<Employee> getAll();

    List<EmployeeDto> getAllDto();

    Optional<Employee> get(Integer id);

    Employee save(Employee employee);

    Optional<Employee> delete(Integer id);
}
