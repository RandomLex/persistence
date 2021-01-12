package by.academy.persistence.app.controllers;

import by.academy.persistence.app.services.EmployeeService;
import by.academy.persistence.app.services.EmployeeServiceImpl;
import by.academy.persistence.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee")
public class EmployeeJsonController extends JsonController {
    private static final String ID = "id";

    private final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idAsString = req.getParameter(ID);
        if (idAsString == null) {
            toJson(service.getAllEmployees(), resp);
        } else {
            toJson(service.getEmployee(Integer.parseInt(idAsString)), resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = toObject(Employee.class, req);
        toJson(service.saveEmployee(employee), resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = toObject(Employee.class, req);
        toJson(service.saveEmployee(employee), resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idAsString = req.getParameter(ID);
        if (idAsString != null) {
            toJson(service.deleteEmployee(Integer.parseInt(idAsString)), resp);
        }
    }
}