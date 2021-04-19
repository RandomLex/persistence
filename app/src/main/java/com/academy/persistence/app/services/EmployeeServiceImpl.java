package com.academy.persistence.app.services;

import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.app.dtos.PageEntityDto;
import com.academy.persistence.app.repositories.EmployeeCrudRepository;
import com.academy.persistence.app.repositories.EmployeeRepository;
import com.academy.persistence.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:app.properties")
@Transactional
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {
    private final EmployeeCrudRepository repository;

    //    private EmployeeRepository repository;
//    @Autowired
//    private Map<String, EmployeeRepository> repositoryMap;
//    @Value("${repository.type}")
//    private String repositoryType;
//
//    @PostConstruct
//    private void init() {
//        repository = repositoryMap.get(repositoryType);
//    }

    @Override
    public List<EmployeeDto> getAllDto() {
        return ((EmployeeRepository) repository).findAllDto();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Employee> getAllFetch() {
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Employee> getAll() {
        return repository.findAllFetch();
    }

    public List<Employee> findAllBySalary(int salary) {
        return repository.findAllBySalaryGreaterThanEqual(salary);
    }

    public List<Employee> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    public List<Employee> findAllByDepartment(String department) {
        return repository.findAllByDepartment(department);
    }


    public PageEntityDto<Employee> getAll(Pageable pageable) {
        Page<Employee> employeePage = repository.findAll(pageable);
        return new PageEntityDto<>(
                employeePage.getTotalPages(),
                employeePage.getTotalElements(),
                employeePage.getNumber(),
                employeePage.getNumberOfElements(),
                employeePage.getContent());
    }
}
