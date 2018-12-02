package ru.gubenko.test.controller;

import ru.gubenko.test.dao.PartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PartDao dao = new PartDao();
        request.setAttribute("parts", dao.getAllParts());
        request.setAttribute("show_table", true);
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
