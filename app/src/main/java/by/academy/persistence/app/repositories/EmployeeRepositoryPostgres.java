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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class EmployeeRepositoryPostgres implements EmployeeRepository {
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
    //language=SQL
    private static final String ONE_EMPLOYEE_FILTER = " WHERE e.id = ?";
    private static final String FIND_EMPLOYEE_BY_ID = SQL_EMPLOYEE_ALL_FIELDS + ONE_EMPLOYEE_FILTER;
    private static final String T_ID = "t_id";
    private static final String C_ID = "c_id";
    private static final String D_ID = "d_id";
    private static final String E_ID = "e_id";
    private static final String E_NAME = "e_name";
    private static final String SALARY = "salary";
    private static final String T_NAME = "t_name";
    private static final String D_NAME = "d_name";
    private static final String C_NAME = "c_name";
    private static final String ID = "id";
    private static final String NAME = "name";

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
             PreparedStatement ps = con.prepareStatement(SQL_EMPLOYEE_ALL_FIELDS);
             ResultSet rs = ps.executeQuery()) {
            result = rsToEmployees(rs);
        } catch (SQLException e) {
            log.error("error while reading from employee");
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    public Optional<Employee> find(Integer id) {
        List<Employee> result;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_EMPLOYEE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            result = rsToEmployees(rs);
        } catch (SQLException e) {
            log.error("error while reading from employee");
            throw new DatabaseException(e);
        }
        return result.stream().findAny();
    }

    @Override
    public Employee save(Employee employee) {
        return employee.getId() == null ? insert(employee) : update(employee);
    }

    private Employee update(Employee employee) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                    "UPDATE employee set name=?, salary=? " +
                            "WHERE id=?")) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setInt(3, employee.getId());
            if (ps.executeUpdate() > 0) {
                return employee;
            }
            return null;
        } catch (SQLException e) {
            log.error("error while reading from employee");
            throw new DatabaseException(e);
        }
    }

    private Employee insert(Employee employee) {
        ResultSet rs = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO employee (name, salary) " +
                     "VALUES (?, ?) RETURNING id")) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            rs = ps.executeQuery();
            if (rs.next()) {
                return employee.withId(rs.getInt(ID));
            }
            return null;
        } catch (SQLException e) {
            log.error("error while reading from employee");
            throw new DatabaseException(e);
        } finally {
            close(rs);
        }
    }

    @Override
    public Optional<Employee> remove(Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM employee " +
                             "WHERE id=?")) {
            ps.setInt(1, id);
            Optional<Employee> employee = find(id);
            if (employee.isEmpty()) {
                return employee;
            }
            if (ps.executeUpdate() > 0) {
                return employee;
            }
            return Optional.empty();
        } catch (SQLException e) {
            log.error("error while reading from employee");
            throw new DatabaseException(e);
        }
    }

    private List<Employee> rsToEmployees(ResultSet rs) throws SQLException {
        Map<Integer, Employee> employeeMap = new LinkedHashMap<>();
        Map<Integer, Department> departmentMap = new HashMap<>();
        Map<Integer, City> cityMap = new HashMap<>();
        Map<Integer, Title> titleMap = new HashMap<>();

        while (rs.next()) {
            Integer tId = getInteger(rs, T_ID);
            Integer cId = getInteger(rs, C_ID);
            Integer dId = getInteger(rs, D_ID);
            Integer eId = getInteger(rs, E_ID);

            employeeMap.putIfAbsent(eId,
                    new Employee()
                            .withId(eId)
                            .withName(rs.getString(E_NAME))
                            .withSalary(rs.getInt(SALARY))
                            .withTitle(putIfAbsentAndReturn(titleMap, tId,
                                    new Title()
                                            .withId(tId)
                                            .withName(rs.getString(T_NAME))))
                            .withDepartment(putIfAbsentAndReturn(departmentMap, dId,
                                    new Department()
                                            .withId(dId)
                                            .withName(getRsString(rs, D_NAME))
                                            .withCity(putIfAbsentAndReturn(cityMap, cId,
                                                    new City()
                                                            .withId(cId)
                                                            .withName(getRsString(rs, C_NAME)))))));
            employeeMap.computeIfPresent(eId, (id, employee) ->
                    employee.withDepartment(departmentMap.get(dId)));
        }
        return new ArrayList<>(employeeMap.values());
    }

    private static Integer getInteger(ResultSet rs, String tId) throws SQLException {
        return rs.getObject(tId, Integer.class);
    }

    private static <K, V> V putIfAbsentAndReturn(Map<K, V> map, K key, V value) {
        if (key == null) {
            return null;
        }
        map.putIfAbsent(key, value);
        return map.get(key);
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

    private void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void rollbackWithLogging(Connection con, SQLException e) {
        log.error(e.getMessage(), e);
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e1) {
            log.error(e1.getMessage(), e1);
        }
    }
}
