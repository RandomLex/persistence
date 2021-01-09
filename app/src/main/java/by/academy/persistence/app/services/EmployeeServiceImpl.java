package by.academy.persistence.app.services;

import by.academy.persistence.app.repositories.EmployeeRepository;
import by.academy.persistence.app.repositories.EmployeeRepositoryInMemory;
import by.academy.persistence.app.repositories.EmployeeRepositoryPostgres;
import by.academy.persistence.model.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    private final EmployeeRepository repository = EmployeeRepositoryPostgres.getInstance();

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee saveEmployee(String name, int salary) {
        Employee employee = repository.save(new Employee()
                .withName(name)
                .withSalary(salary));
        log.info("A new Employee is added : {}", employee);
        return employee;
    }
}
