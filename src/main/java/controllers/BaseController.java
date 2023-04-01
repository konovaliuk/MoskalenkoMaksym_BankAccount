package main.java.controllers;


import main.java.utils.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandClassName = request.getParameter("command");
        Command command = getCommand(commandClassName);
        String viewName = command.execute(request, response);
        request.getRequestDispatcher("/views/" + viewName).forward(request, response);
    }

    protected abstract Command getCommand(String commandClassName);
}