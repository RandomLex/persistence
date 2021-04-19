package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
    String FETCH_ALL = "select employee from Employee employee left join fetch employee.title title " +
            "left join fetch employee.departments departments " +
            "left join fetch departments.city city " +
            "left join fetch city.departments";

    String ID_FILTER = " where employee.id = :id";


    @Override
    List<Employee> findAll();

    @Query(FETCH_ALL)
    List<Employee> findAllFetch();

    @Query(FETCH_ALL + ID_FILTER)
    List<Employee> findByIdFetch(@Param("id") Integer id);
}
