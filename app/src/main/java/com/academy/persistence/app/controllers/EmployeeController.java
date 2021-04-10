package com.academy.persistence.app.controllers;

import com.academy.persistence.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@WebServlet("/employee-controller")
@Controller
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(path = "employees-info", method = RequestMethod.GET)
    public ModelAndView employeesInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", service.getAll());
        modelAndView.setViewName("employees");
        return modelAndView;
    }
}
