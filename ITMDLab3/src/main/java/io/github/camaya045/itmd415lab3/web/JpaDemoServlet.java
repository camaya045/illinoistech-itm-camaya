package io.github.camaya045.itmd415lab3.web;

import io.github.camaya045.itmd415lab3.entity.Employee;
import io.github.camaya045.itmd415lab3.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/jpa/demo")
public class JpaDemoServlet extends HttpServlet {
    @Inject
    private EmployeeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        String action = req.getParameter("action");
        if (action == null) {
            resp.getWriter().println("Use:");
            resp.getWriter().println("/jpa/demo?action=create&empId=9001&fullName=Ada%20Lovelace&title=Engineer&deptId=1&salary=120000.00&hireDate=2024-01-15");
            resp.getWriter().println("/jpa/demo?action=find&empId=9001");
            resp.getWriter().println("/jpa/demo?action=update&empId=9001&title=Senior%20Engineer");
            return;
        }
        switch (action) {
            case "create" -> {
                int empId = Integer.parseInt(req.getParameter("empId"));
                String fullName = req.getParameter("fullName");
                String title = req.getParameter("title");
                int deptId = Integer.parseInt(req.getParameter("deptId"));
                BigDecimal salary = new BigDecimal(req.getParameter("salary"));
                LocalDate hireDate = LocalDate.parse(req.getParameter("hireDate"));
                service.createEmployee(empId, fullName, title, deptId, salary, hireDate);
                resp.getWriter().println("Created employee emp_id=" + empId);
            }
            case "find" -> {
                int empId = Integer.parseInt(req.getParameter("empId"));
                Employee e = service.findEmployee(empId);
                if (e == null) {
                    resp.getWriter().println("Not found");
                    return;
                }
                resp.getWriter().println("Found emp_id=" + e.getEmpId());
                resp.getWriter().println("full_name=" + e.getFullName());
                resp.getWriter().println("title=" + e.getTitle());
                resp.getWriter().println("salary=" + e.getSalary());
                resp.getWriter().println("hire_date=" + e.getHireDate());
                resp.getWriter().println("dept_id=" + e.getDepartment().getDeptId());
            }
            case "update" -> {
                int empId = Integer.parseInt(req.getParameter("empId"));
                String newTitle = req.getParameter("title");
                service.updateTitle(empId, newTitle);
                resp.getWriter().println("Updated emp_id=" + empId + " title=" + newTitle);
            }
            default -> resp.getWriter().println("Unknown action=" + action);
        }
    }
}