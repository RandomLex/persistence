package com.academy.persistence.app.controllers;

import com.academy.persistence.app.services.EmployeeService;
import com.academy.persistence.app.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        modelAndView.addObject("employees", service.getAllEmployees());
        modelAndView.setViewName("employees");
        return modelAndView;
    }
}
