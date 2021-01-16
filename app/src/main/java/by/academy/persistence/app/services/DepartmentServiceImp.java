package by.academy.persistence.app.services;

import by.academy.persistence.app.repositories.CityRepository;
import by.academy.persistence.app.repositories.CityRepositoryPostgres;
import by.academy.persistence.app.repositories.DepartmentRepository;
import by.academy.persistence.app.repositories.DepartmentRepositoryPostgres;
import by.academy.persistence.app.repositories.RepositoryFactory;
import by.academy.persistence.model.City;
import by.academy.persistence.model.Department;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository depRepository = RepositoryFactory.getDepartmentRepository();
    private final CityRepository cityRepository = RepositoryFactory.getCityRepository();

    @Override
    public List<Department> getAllDepartments() {
        return depRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartment(Integer id) {
        return depRepository.find(id);
    }

    @Override
    public Department saveDepartment(Department department) {
        City city = department.getCity();
        if (city == null) {
            return depRepository.save(department);
        }
        City existingCity = cityRepository.findAll().stream()
                .filter(haveSameName(city))
                .findFirst()
                .orElseGet(() -> cityRepository.save(city.withDepartment(department)));
        return depRepository.save(department.withCity(existingCity));
    }

    private Predicate<City> haveSameName(City city) {
        return c -> c.getName().equals(city.getName());
    }

    @Override
    public Optional<Department> deleteDepartment(Integer id) {
        return depRepository.remove(id);
    }
}
