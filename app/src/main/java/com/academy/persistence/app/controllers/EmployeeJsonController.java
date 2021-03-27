package com.academy.persistence.app.controllers;

import com.academy.persistence.app.services.EmployeeService;
import com.academy.persistence.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/employee", produces = "application/json;charset=UTF-8")
@ResponseBody
public class EmployeeJsonController {
    private static final String ID = "id";

    private final EmployeeService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @RequestMapping(method = RequestMethod.GET, params = "id")
    public Employee get(@RequestParam int id) {
        Optional<Employee> employee = service.getEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " + id);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Employee deleteEmployee(@RequestParam int id) {
        Optional<Employee> employee = service.deleteEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " + id);
        }
    }
}