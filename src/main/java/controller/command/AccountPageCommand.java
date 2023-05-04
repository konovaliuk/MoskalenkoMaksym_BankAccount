package main.java.controller.command;

import main.java.models.Account;
import main.java.models.Transaction;
import main.java.service.AccountsService;
import main.java.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class AccountPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = (UUID) request.getSession().getAttribute("profileId");

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            UUID accountId = UUID.fromString(request.getParameter("id"));
            Account account = AccountsService.getAccountById(accountId);

            if (!account.getProfileId().equals(uuid)) {
                response.sendRedirect("/");
                return;
            }

            List<Transaction> transactions = TransactionService.getTransactionsByAccountId(accountId);

            request.setAttribute("transactions", transactions);
            request.setAttribute("account", account);

            request.getRequestDispatcher("/views/account.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}