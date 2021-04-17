package com.academy.persistence.app.repositories;

import com.academy.persistence.app.dtos.DepartmentDto;
import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("springJpa")
public class EmployeeRepositorySpringJpa extends AbstractRepositorySpringJpa<Employee> implements EmployeeRepository {

    public EmployeeRepositorySpringJpa() {
        clazz = Employee.class;
    }

    @Override
    public List<EmployeeDto> findAllDto() {
        return super.findAll().stream()
                .map(employee -> EmployeeDto.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .salary(employee.getSalary())
                        .departments(employee.getDepartments().stream()
                                .map(department -> DepartmentDto.builder()
                                        .id(department.getId())
                                        .name(department.getName())
                                        .city(department.getCity().getName())
                                        .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }
}
