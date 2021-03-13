package com.academy.persistence.app.controllers;

import com.academy.persistence.app.services.EmployeeService;
import com.academy.persistence.app.services.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/login")
public class LoginController extends HttpServlet {
//    private final EmployeeService service = EmployeeServiceImpl.getInstance();
//    @Override
//    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//        service.saveEmployee(name, Integer.parseInt(password));
//        req.getRequestDispatcher("/employee-controller").forward(req, resp);
//    }
}
