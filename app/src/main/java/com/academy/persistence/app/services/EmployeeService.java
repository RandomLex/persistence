package com.academy.persistence.app.services;

import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.app.dtos.PageEntityDto;
import com.academy.persistence.model.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends ServiceInterface<Employee>{
    List<Employee> getAll();

    PageEntityDto<Employee> getAll(Pageable pageable);

    List<EmployeeDto> getAllDto();

    List<Employee> getAllFetch();

    List<Employee> findAllBySalary(int salary);

    List<Employee> findAllByName(String name);

    List<Employee> findAllByDepartment(String department);


    Optional<Employee> get(Integer id);

    Employee save(Employee employee);

    Optional<Employee> delete(Integer id);
}
