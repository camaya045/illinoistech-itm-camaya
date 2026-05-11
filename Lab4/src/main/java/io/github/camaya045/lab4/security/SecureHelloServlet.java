package io.github.camaya045.lab4.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
 *  /secure/hello
 *
 * Purpose:
 * - Demonstrates RBAC where both USER and ADMIN are allowed.
 * - Prints the authenticated username (Principal) and role membership checks.
 */
@WebServlet("/secure/hello")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER", "ADMIN"}))
public class SecureHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        //Principal name is the authenticated username (null if not authenticated)
        String username = (req.getUserPrincipal() == null)
                ? "anonymous"
                : req.getUserPrincipal().getName();

        resp.getWriter().println("SecureHelloServlet");
        resp.getWriter().println("------------------");
        resp.getWriter().println("Hello, " + username + "!");
        resp.getWriter().println();
        resp.getWriter().println("Role checks:");
        resp.getWriter().println("is USER?" + req.isUserInRole("USER"));
        resp.getWriter().println("is ADMIN?" + req.isUserInRole("ADMIN"));
    }
}