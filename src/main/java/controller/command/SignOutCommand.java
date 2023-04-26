package main.java.controller.command;

import main.java.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.removeSessionCookie(response);

            response.sendRedirect("/sign_in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
