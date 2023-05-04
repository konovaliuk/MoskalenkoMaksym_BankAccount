package main.java.controller.command;

import main.java.service.AccountsService;
import main.java.types.AccountStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CreditApproveCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = (UUID) request.getSession().getAttribute("profileId");

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            UUID accountId = UUID.fromString(request.getParameter("account_id"));
            AccountStatus status = AccountStatus.fromSqlName(request.getParameter("status"));

            try {
                AccountsService.changeCreditApprove(accountId, status, uuid);
            } catch (Exception e) {
                // TODO: return error message as an alert
                throw new RuntimeException(e);
            }

            response.sendRedirect("/accounts_to_approve");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}