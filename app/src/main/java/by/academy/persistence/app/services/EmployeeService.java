package by.academy.persistence.app.services;

import by.academy.persistence.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee saveEmployee(String name, int salary);
}
