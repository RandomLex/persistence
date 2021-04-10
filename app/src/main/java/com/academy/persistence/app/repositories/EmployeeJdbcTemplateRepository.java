package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("jdbcTempl")
@AllArgsConstructor
public class EmployeeJdbcTemplateRepository implements EmployeeRepository {
    private static final String SQL_EMPLOYEE_ALL_FIELDS = "select e.id e_id, e.name e_name, salary, " +
            "d.id d_id, d.name d_name, " +
            "c.id c_id, c.name c_name, " +
            "t.id t_id, t.name t_name " +
            "from employee e " +
            "left join title t " +
            "on t.id = e.title_id " +
            "left join department_employee de " +
            "on e.id = de.employee_id " +
            "left join department d " +
            "on d.id = de.department_id " +
            "left join city c " +
            "on d.city_id = c.id";

    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRowMapper rowMapper;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(SQL_EMPLOYEE_ALL_FIELDS, rowMapper);
    }

    @Override
    public Optional<Employee> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee entity) {
        return null;
    }

    @Override
    public Optional<Employee> remove(Integer id) {
        return Optional.empty();
    }


}
