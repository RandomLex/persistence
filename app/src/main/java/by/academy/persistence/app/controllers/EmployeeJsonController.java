package by.academy.persistence.app.controllers;

import by.academy.persistence.app.services.EmployeeService;
import by.academy.persistence.app.services.EmployeeServiceImpl;
import by.academy.persistence.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/employee")
public class EmployeeJsonController extends HttpServlet {
    private EmployeeService service = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> allEmployees = service.getAllEmployees();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(allEmployees);
//        String json = new Gson()
//                .toJson(allEmployees);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
