package controller.command;

import service.AccountsService;
import types.AccountType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


public class CreateAccountCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            String type = request.getParameter("type");
            String amount = request.getParameter("amount");

            AccountsService.createAccount(AccountType.fromSqlName(type), new BigDecimal(amount), id);
            response.sendRedirect("/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
