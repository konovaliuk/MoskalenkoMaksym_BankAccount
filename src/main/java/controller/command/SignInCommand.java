package main.java.controller.command;

import main.java.models.Profile;
import main.java.service.ProfileService;
import main.java.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            Profile p = ProfileService.signIn(login, password);

            if (p != null) {
                CookieUtil.setSessionCookie(response, p.getId());

                response.sendRedirect("/");
            } else {
                // TODO: handle error
                request.getRequestDispatcher("/views/sign_in.jsp").forward(request, response);
            }

        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
