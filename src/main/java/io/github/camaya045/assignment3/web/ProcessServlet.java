package io.github.camaya045.assignment3.web;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/process")
public class ProcessServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {

        //Get Parameters
        String name = request.getParameter("name");
        String food = request.getParameter("food");
        int age = Integer.parseInt(request.getParameter("age"));
        //Determine weather user is a minor or adult
        String category;
        if(age <18) {
            category = "minor";
        }else {
            category = "adult";
        }
        //Creates a message that incorporates the user’s name and favorite food
        String message = name + " likes  " + food + ".";
        //Places all needed output data into request attributes
        request.setAttribute("name", name);
        request.setAttribute("food", food);
        request.setAttribute("age", age);
        request.setAttribute("category", category);
        request.setAttribute("message", message);
        //Forwards the request to a JSP
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}