package controller.command;

import models.Account;
import models.Transaction;
import service.AccountsService;
import service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AccountPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            Long accountId = Long.parseLong(request.getParameter("id"));
            Account account = AccountsService.getAccountById(accountId);

            if (!account.getProfileId().equals(id)) {
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