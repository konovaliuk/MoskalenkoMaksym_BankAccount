package controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateAccountPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            request.getRequestDispatcher("/views/create_account.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
