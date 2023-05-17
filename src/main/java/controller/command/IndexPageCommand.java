package controller.command;

import models.Account;
import models.Profile;
import service.AccountsService;
import service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class IndexPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            List<Account> accounts = AccountsService.getAccountsByProfileId(id);
            Profile p = ProfileService.getProfileById(id);

            request.setAttribute("login", p.getLogin());
            request.setAttribute("profile_role", p.getRole());
            request.setAttribute("accounts", accounts);

            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
