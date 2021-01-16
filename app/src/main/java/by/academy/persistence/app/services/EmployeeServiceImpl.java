package by.academy.persistence.app.services;

import by.academy.persistence.app.repositories.EmployeeRepository;
import by.academy.persistence.app.repositories.EmployeeRepositoryPostgres;
import by.academy.persistence.app.repositories.RepositoryFactory;
import by.academy.persistence.model.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private static volatile EmployeeServiceImpl instance;

    public EmployeeServiceImpl() {
    }

    public static EmployeeServiceImpl getInstance() {
        if (instance == null) {
            synchronized (EmployeeServiceImpl.class) {
                if (instance == null) {
                    instance = new EmployeeServiceImpl();
                }
            }
        }
        return instance;
    }

    private final EmployeeRepository repository = RepositoryFactory.getEmployeeRepository();

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> getEmployee(Integer id) {
        return repository.find(id);
    }

    @Override
    public Employee saveEmployee(String name, int salary) {
        Employee employee = repository.save(new Employee()
                .withName(name)
                .withSalary(salary));
        log.info("A new Employee is added : {}", employee);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Optional<Employee> deleteEmployee(Integer id) {
        return repository.remove(id);
    }
}
