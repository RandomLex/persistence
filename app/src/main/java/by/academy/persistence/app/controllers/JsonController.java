package by.academy.persistence.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class JsonController extends HttpServlet {
    protected void toJson(Object obj, HttpServletResponse resp) throws ServletException, IOException {
        obj = (obj instanceof Optional) ? ((Optional<?>) obj).orElse(null) : obj;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
