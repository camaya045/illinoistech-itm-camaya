package io.github.camaya045.lab4.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
* /secure/admin
*
* Purpose:
* -Demonstartes RBAC where ONLY ADMIN is allowed
* -If the caller is not ADMIN, the container denies before the code runs.
*/
@WebServlet("/secure/admin")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class SecureAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        String username = (req.getUserPrincipal() == null)
                ? "anonymous"
                : req.getUserPrincipal().getName();

        resp.getWriter().println("SecureAdminServlet");
        resp.getWriter().println("------------------");
        resp.getWriter().println("Admin access granted to: " + username);
        resp.getWriter().println();
        resp.getWriter().println("Role checks:");
        resp.getWriter().println("is USER?" + req.isUserInRole("USER"));
        resp.getWriter().println("is ADMIN?" + req.isUserInRole("ADMIN"));
    }
}