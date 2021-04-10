package com.academy.persistence.app.repositories;

import com.academy.persistence.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee()
                .withId(rs.getInt("e_id"))
                .withName(rs.getString("e_name"))
                .withSalary(rs.getInt("salary"));
    }
}
