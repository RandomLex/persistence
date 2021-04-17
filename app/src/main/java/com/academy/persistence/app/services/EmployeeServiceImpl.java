package com.academy.persistence.app.services;

import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.app.repositories.EmployeeRepository;
import com.academy.persistence.app.repositories.RepositoryFactory;
import com.academy.persistence.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@PropertySource("classpath:app.properties")
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

//    private EmployeeRepository repository;
    @Autowired
    private Map<String, EmployeeRepository> repositoryMap;
    @Value("${repository.type}")
    private String repositoryType;

    @PostConstruct
    private void init() {
        repository = repositoryMap.get(repositoryType);
    }

    @Override
    public List<EmployeeDto> getAllDto() {
        return transactionTemplate.execute(status -> {
            try {
                return ((EmployeeRepository)repository).findAllDto();
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return new ArrayList<>();
        });
    }

    @Override
    public List<Employee> getAllFetch() {
        return transactionTemplate.execute(status -> {
            try {
                return ((EmployeeRepository)repository).findAllFetch();
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return new ArrayList<>();
        });
    }


}
