package io.github.camaya045.lab_1.JSPLab;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet ("/echo")
public class EchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //read request parameters
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        // compute abd storing request attributes
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.setAttribute("currentTime", new Date());
        request.setAttribute("serverInfo", getServletContext().getServerInfo());
        request.setAttribute("requestMethod", request.getMethod());

        //Forwarding request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/echo.jsp");
        dispatcher.forward(request, response);
    }
}
