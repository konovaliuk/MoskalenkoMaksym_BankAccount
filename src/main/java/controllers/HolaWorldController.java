package main.java.controllers;
import main.java.utils.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HolaWorldController extends BaseController {
    protected Command getCommand(String commandClassName) {
        return new HolaWorldCommand();
    }

    private class HolaWorldCommand implements Command {
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String message = "Hola, world!";
            request.setAttribute("message", message);
            return "hola.jsp";
        }
    }
}