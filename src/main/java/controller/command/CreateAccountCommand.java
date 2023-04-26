package main.java.controller.command;

import main.java.service.AccountsService;
import main.java.types.AccountType;
import main.java.utils.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.UUID;

public class CreateAccountCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = CookieUtil.getSessionCookie(request);

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            String type = request.getParameter("type");
            String amount = request.getParameter("amount");

            AccountsService.createAccount(AccountType.fromSqlName(type), new BigDecimal(amount), uuid);
            response.sendRedirect("/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
