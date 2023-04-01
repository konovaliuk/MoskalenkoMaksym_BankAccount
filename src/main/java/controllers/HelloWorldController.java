package main.java.controllers;
import main.java.utils.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldController extends BaseController {
    protected Command getCommand(String commandClassName) {
        return new HelloWorldCommand();
    }

    private class HelloWorldCommand implements Command {
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String message = "Hello, world!";
            request.setAttribute("message", message);
            return "hello.jsp";
        }
    }
}