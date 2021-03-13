package com.academy.persistence.app.controllers;

import com.academy.persistence.app.services.EmployeeService;
import com.academy.persistence.app.services.EmployeeServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee-controller")
public class EmployeeController extends HttpServlet {
    private final EmployeeService service;

    public EmployeeController() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.academy.persistence.app");
        service = ctx.getBean("employeeServiceImpl", EmployeeServiceImpl.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", service.getAllEmployees());
        req.getRequestDispatcher("/employees.jsp").forward(req, resp);
    }
}
