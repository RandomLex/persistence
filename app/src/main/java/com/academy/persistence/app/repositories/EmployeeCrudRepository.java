package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeCrudRepository extends JpaRepository<Employee, Integer> {
    String FETCH_ALL = "select employee from Employee employee left join fetch employee.title title " +
            "left join fetch employee.departments departments " +
            "left join fetch departments.city city " +
            "left join fetch city.departments";

    String ID_FILTER = " where employee.id = :id";


    @Override
    List<Employee> findAll();

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findAllBySalaryGreaterThanEqual(int salary);

    List<Employee> findAllByName(String name);

    @Query("select employee from Employee employee join employee.departments departments " +
            "where departments.name = :departmentName")
    List<Employee> findAllByDepartment(@Param("departmentName") String department);

    @Query(FETCH_ALL)
    List<Employee> findAllFetch();

    @Query(FETCH_ALL + ID_FILTER)
    List<Employee> findByIdFetch(@Param("id") Integer id);


}
