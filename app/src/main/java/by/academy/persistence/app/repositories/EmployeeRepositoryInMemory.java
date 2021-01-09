package by.academy.persistence.app.repositories;

import by.academy.persistence.model.Employee;


public class EmployeeRepositoryInMemory extends AbstractRepositoryInMemory<Employee> implements EmployeeRepository {
    private static volatile EmployeeRepositoryInMemory instance;
    private EmployeeRepositoryInMemory() {

    }

    public static EmployeeRepositoryInMemory getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepositoryInMemory.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryInMemory();
                }
            }
        }
        return instance;
    }

    {
        map.put(1, new Employee()
                .withId(1)
                .withName("Виктор")
                .withSalary(100));
        map.put(2, new Employee()
                .withId(2)
                .withName("Пётр")
                .withSalary(100));
        map.put(3, new Employee()
                .withId(3)
                .withName("Люда")
                .withSalary(80));
        map.put(4, new Employee()
                .withId(4)
                .withName("Аня")
                .withSalary(150));
        map.put(5, new Employee()
                .withId(5)
                .withName("Дима")
                .withSalary(120));
    }
}
