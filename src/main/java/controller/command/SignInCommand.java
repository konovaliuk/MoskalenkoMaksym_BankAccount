package main.java.controller.command;

import main.java.models.Profile;
import main.java.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements Command {
    ProfileService profileService = new ProfileService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Profile p = profileService.signIn(login, password);

        if (p != null) {
            Cookie c = new Cookie("profile", p.getId().toString());
            c.setMaxAge(30 * 60);
            response.addCookie(c);

            try {
                request.getRequestDispatcher("/views/index.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // TODO: handle error
        }

        try {
            request.getRequestDispatcher("/views/sign_in.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
