package by.academy.persistence.app.services;

import by.academy.persistence.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> getDepartment(Integer id);

    Department saveDepartment(Department employee);

    Optional<Department> deleteDepartment(Integer id);
}
