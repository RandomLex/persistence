package by.academy.persistence.app.repositories;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class RepositoryFactory {
    private static final RepositoryTypes type;

    static {
        Properties dbProperties = new Properties();
        try {
            dbProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        type = RepositoryTypes.getTypeByStr(dbProperties.getProperty("repository.type"));
    }

    public static EmployeeRepository getEmployeeRepository() {
        switch (type) {
            case POSTGRES:
                return EmployeeRepositoryPostgres.getInstance();
            case MEMORY:
            default:
                return EmployeeRepositoryInMemory.getInstance();
        }
    }

    public static DepartmentRepository getDepartmentRepository() {
        switch (type) {
            case POSTGRES:
                return DepartmentRepositoryPostgres.getInstance();
            case MEMORY:
            default:
                return DepartmentRepositoryInMemory.getInstance();
        }
    }

    public static CityRepository getCityRepository() {
        switch (type) {
            case POSTGRES:
                return CityRepositoryPostgres.getInstance();
            case MEMORY:
            default:
                return CityRepositoryInMemory.getInstance();
        }
    }

}
