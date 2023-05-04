package main.java.controller.command;

import main.java.models.Account;
import main.java.models.Profile;
import main.java.service.AccountsService;
import main.java.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class IndexPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = (UUID) request.getSession().getAttribute("profileId");

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            List<Account> accounts = AccountsService.getAccountsByProfileId(uuid);
            Profile p = ProfileService.getProfileById(uuid);

            request.setAttribute("profile_id", uuid);
            request.setAttribute("profile_role", p.getRole().toSqlName());
            request.setAttribute("accounts", accounts);

            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
