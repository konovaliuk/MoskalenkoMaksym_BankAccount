package main.java.controller.command;

import main.java.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CreateAccountPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = CookieUtil.getSessionCookie(request);

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            request.getRequestDispatcher("/views/create_account.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
