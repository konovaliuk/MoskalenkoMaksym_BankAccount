package controller.command;

import service.AccountsService;
import types.AccountStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreditApproveCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            Long accountId = Long.parseLong(request.getParameter("account_id"));
            AccountStatus status = AccountStatus.fromSqlName(request.getParameter("status"));

            try {
                AccountsService.changeCreditApprove(accountId, status, id);
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