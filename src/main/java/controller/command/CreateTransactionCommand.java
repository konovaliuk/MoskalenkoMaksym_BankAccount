package controller.command;

import service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Objects;


public class CreateTransactionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            String sourceAccount = request.getParameter("from");
            Boolean isSourceExternal = Objects.equals(request.getParameter("isSourceExternal"), "on");
            String destinationAccount = request.getParameter("to");
            Boolean isDestinationExternal = Objects.equals(request.getParameter("isDestinationExternal"), "on");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));

            try {
                TransactionService.handleTransactionCreation(sourceAccount, isSourceExternal, destinationAccount, isDestinationExternal, amount, id);
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
