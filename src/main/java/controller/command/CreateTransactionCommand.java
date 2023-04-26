package main.java.controller.command;

import main.java.service.TransactionService;
import main.java.utils.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class CreateTransactionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = CookieUtil.getSessionCookie(request);

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            String sourceAccount = request.getParameter("from");
            Boolean isSourceExternal = Objects.equals(request.getParameter("isSourceExternal"), "on");
            String destinationAccount = request.getParameter("to");
            Boolean isDestinationExternal = Objects.equals(request.getParameter("isDestinationExternal"), "on");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));

            try {
                TransactionService.handleTransactionCreation(UUID.fromString(sourceAccount), isSourceExternal, UUID.fromString(destinationAccount), isDestinationExternal, amount, uuid);
            } catch (Exception e) {
                // TODO: return error message as an alert
                throw new RuntimeException(e);
            }

            response.sendRedirect("/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
