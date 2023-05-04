package main.java.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("profileId");

            response.sendRedirect("/sign_in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
