package main.java.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute("profileId") != null) {
                response.sendRedirect("/");
                return;
            }

            request.getRequestDispatcher("/views/sign_in.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
