package com.example.applicationonlineshop.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;

@WebServlet(value = "")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("nom1","1");
        req.setAttribute("nom2","2");
        req.setAttribute("nom3","3");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/index.jsp");
        requestDispatcher.forward(req,resp);

    }
}
