package com.academy.persistence.app.controllers;

import com.academy.persistence.app.dtos.EmployeeDto;
import com.academy.persistence.app.dtos.PageEntityDto;
import com.academy.persistence.app.services.EmployeeService;
import com.academy.persistence.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/employees", produces = "application/json;charset=UTF-8")
@ResponseBody
public class EmployeeJsonController {
    private static final String ID = "id";

    private final EmployeeService service;

    // GET /zzz/employees?salary>=100
    @GetMapping
    public List<Employee> getAll(
            @RequestParam(name = "salary>", required = false, defaultValue = "0") int salaryGreatOrEquals) {
        return salaryGreatOrEquals == 0
                ? service.getAll()
                : service.findAllBySalary(salaryGreatOrEquals);
    }

    @GetMapping(params = {"page", "items"})
    public PageEntityDto<Employee> getAll(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "items") int items) {
        return service.getAll(PageRequest.of(page, items));
    }

    @GetMapping(params = "name")
    public List<Employee> getAllByName(
            @RequestParam(name = "name") String name) {
        return service.findAllByName(name);
    }

    @GetMapping(params = "department")
    public List<Employee> getAllByDepartment(
            @RequestParam(name = "department") String department) {
        return service.findAllByDepartment(department);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getByPath(@PathVariable int id) {
        return ResponseEntity.of(service.get(id));
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @RequestBody Employee employee,
            @PathVariable int id) {
        return employee != null && id != employee.getId()
                ? createExceptionMessage("Id in path and in entity must be the same")
                : ResponseEntity.ok(service.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int id) {
        return ResponseEntity.of(service.delete(id));
    }

    private ResponseEntity<ExceptionMessage> createExceptionMessage(String msg) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(msg)
                        .build());
    }

    @Builder
    @Getter
    public static class ExceptionMessage {
        private final String message;
    }
}