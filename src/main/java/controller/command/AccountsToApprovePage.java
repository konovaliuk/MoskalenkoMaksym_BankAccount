package controller.command;

import models.Account;
import models.Profile;
import service.AccountsService;
import service.ProfileService;
import types.ProfileRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class AccountsToApprovePage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            Profile p = ProfileService.getProfileById(id);

            if (!Objects.equals(p.getRole(), ProfileRole.Admin.toSqlName()) && !Objects.equals(p.getRole(), ProfileRole.SuperAdmin.toSqlName())) {
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