package com.academy.persistence.app.controllers;

import com.academy.persistence.app.services.EmployeeService;
import com.academy.persistence.app.services.EmployeeServiceImpl;
import com.academy.persistence.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//@WebServlet("/employee")

@Controller
@AllArgsConstructor
@RequestMapping(path = "/employee", produces = "application/json;charset=UTF-8")
public class EmployeeJsonController {
    private static final String ID = "id";

    private final EmployeeService service;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @RequestMapping(method = RequestMethod.GET, params = "id")
    @ResponseBody
    public Employee get(@RequestParam int id) {
        Optional<Employee> employee = service.getEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " + id);
        }
    }

//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idAsString = req.getParameter(ID);
//        if (idAsString == null) {
//            toJson(service.getAllEmployees(), resp);
//        } else {
//            toJson(service.getEmployee(Integer.parseInt(idAsString)), resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Employee employee = toObject(Employee.class, req);
//        toJson(service.saveEmployee(employee), resp);
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Employee employee = toObject(Employee.class, req);
//        toJson(service.saveEmployee(employee), resp);
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idAsString = req.getParameter(ID);
//        if (idAsString != null) {
//            toJson(service.deleteEmployee(Integer.parseInt(idAsString)), resp);
//        }
//    }
}