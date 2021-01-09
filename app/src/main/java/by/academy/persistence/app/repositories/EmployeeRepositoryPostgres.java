package by.academy.persistence.app.repositories;

import by.academy.persistence.model.Employee;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EmployeeRepositoryPostgres implements EmployeeRepository {
    private final DataSource dataSource = DataSource.getInstance();
    private static volatile EmployeeRepositoryPostgres instance;

    private EmployeeRepositoryPostgres() {
    }

    public static EmployeeRepositoryPostgres getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryPostgres();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from employee");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Employee()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .withSalary(rs.getInt("salary")));
            }

        } catch (SQLException sqlException) {
            log.error("error while reading from employee");
        }
        return result;
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
    public Optional<Employee> remove(Employee entity) {
        return Optional.empty();
    }
}
