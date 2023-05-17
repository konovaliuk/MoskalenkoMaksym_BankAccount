package controller.command;

import models.Account;
import service.AccountsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateTransactionPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = (Long) request.getSession().getAttribute("profileId");

            if (id == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            Account defaultAccount = AccountsService.getDefaultAccountByProfileId(id);

            String from = request.getParameter("from");
            String to = request.getParameter("to");

            request.setAttribute("from", from);
            request.setAttribute("to", to);
            request.setAttribute("defaultAccount", defaultAccount.getAccountNumber());

            request.getRequestDispatcher("/views/create_transaction.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
