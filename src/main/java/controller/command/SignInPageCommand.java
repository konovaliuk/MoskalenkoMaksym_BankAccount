package main.java.controller.command;

import main.java.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (CookieUtil.getSessionCookie(request) != null) {
                response.sendRedirect("/");
                return;
            }

            request.getRequestDispatcher("/views/sign_in.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
