package by.academy.persistence.app.repositories;

import by.academy.persistence.model.City;
import by.academy.persistence.model.Department;
import by.academy.persistence.model.Employee;
import by.academy.persistence.model.Title;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EmployeeRepositoryJpa extends AbstractRepositoryJpa<Employee> implements EmployeeRepository {
    private static volatile EmployeeRepositoryJpa instance;

    private EmployeeRepositoryJpa() {
    }

    public static EmployeeRepositoryJpa getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepositoryJpa.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryJpa();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<Employee> findAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Employee", Employee.class);
    }

    @Override
    protected TypedQuery<Employee> findOneQuery() {
        return helper.getEntityManager()
                .createQuery("from Employee where id = :id", Employee.class);
    }
}
