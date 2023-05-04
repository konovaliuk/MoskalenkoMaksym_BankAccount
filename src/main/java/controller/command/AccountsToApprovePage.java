package main.java.controller.command;

import main.java.models.Account;
import main.java.models.Profile;
import main.java.service.AccountsService;
import main.java.service.ProfileService;
import main.java.types.ProfileRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class AccountsToApprovePage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = (UUID) request.getSession().getAttribute("profileId");

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            Profile p = ProfileService.getProfileById(uuid);

            if (p.getRole() != ProfileRole.Admin && p.getRole() != ProfileRole.SuperAdmin) {
                response.sendRedirect("/");
                return;
            }

            List<Account> accounts = AccountsService.getProcessingCreditRequests();

            request.setAttribute("accounts", accounts);

            request.getRequestDispatcher("/views/accounts_to_approve.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}