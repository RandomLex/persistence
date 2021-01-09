package by.academy.persistence.app.repositories;

import by.academy.persistence.app.exceptions.DatabaseException;
import by.academy.persistence.model.City;
import by.academy.persistence.model.Department;
import by.academy.persistence.model.Employee;
import by.academy.persistence.model.Title;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class EmployeeRepositoryPostgres implements EmployeeRepository {
    private static final String SQL_EMPLOYEE_ALL_FIELDS = "select e.id e_id, e.name e_name, salary, " +
            "d.id d_id, d.name d_name, " +
            "c.id c_id, c.name c_name, " +
            "t.id t_id, t.name t_name " +
            "from employee e " +
            "join title t " +
            "on t.id = e.title_id " +
            "join department_employee de " +
            "on e.id = de.employee_id " +
            "join department d " +
            "on d.id = de.department_id " +
            "join city c " +
            "on d.city_id = c.id";
    //language=SQL
    private static final String ONE_EMPLOYEE_FILTER = " WHERE e.id = ?";
    private static final String FIND_EMPLOYEE_BY_ID = SQL_EMPLOYEE_ALL_FIELDS + ONE_EMPLOYEE_FILTER;
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
        Map<Integer, Employee> employeeMap = new LinkedHashMap<>();
        Map<Integer, Department> departmentMap = new HashMap<>();
        Map<Integer, City> cityMap = new HashMap<>();
        Map<Integer, Title> titleMap = new HashMap<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_EMPLOYEE_ALL_FIELDS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int tId = rs.getInt("t_id");
                int cId = rs.getInt("c_id");
                int dId = rs.getInt("d_id");
                int eId = rs.getInt("e_id");

                titleMap.putIfAbsent(tId, new Title()
                        .withId(tId)
                        .withName(rs.getString("t_name")));

                cityMap.putIfAbsent(cId, new City()
                        .withId(cId)
                        .withName(rs.getString("c_name")));

                departmentMap.putIfAbsent(dId, new Department()
                        .withId(dId)
                        .withName(getRsString(rs, "d_name")));

                employeeMap.putIfAbsent(eId,
                        new Employee()
                                .withId(eId)
                                .withName(rs.getString("e_name"))
                                .withSalary(rs.getInt("salary"))
                                .withTitle(titleMap.getOrDefault(tId,
                                        new Title()
                                                .withId(tId)
                                                .withName(rs.getString("t_name"))))
                                .withDepartment(departmentMap.get(dId)));

                cityMap.computeIfPresent(cId, (id, city) ->
                        city.withDepartment(departmentMap.get(dId)));
                departmentMap.computeIfPresent(dId, (id, department) ->
                        department
                                .withCity(cityMap.get(cId))
                                .withEmployee(employeeMap.get(eId)));
                employeeMap.computeIfPresent(eId, (id, employee) ->
                        employee.withDepartment(departmentMap.get(dId)));
            }

        } catch (SQLException e) {
            log.error("error while reading from employee");
            throw new DatabaseException(e);
        }
        return new ArrayList<>(employeeMap.values());
    }

    private String getRsString(ResultSet rs, String columnName) {
        try {
            return rs.getString(columnName);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Integer getRsInt(ResultSet rs, String columnName) {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }


    @Override
    public Optional<Employee> find(Integer id) {
        Employee result = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_EMPLOYEE_BY_ID);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                new Employee()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .withSalary(rs.getInt("salary"));
            }
        } catch (SQLException sqlException) {
            log.error("error while reading from employee");
        }
        return Optional.ofNullable(result);
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
