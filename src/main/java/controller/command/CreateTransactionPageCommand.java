package main.java.controller.command;

import main.java.models.Account;
import main.java.service.AccountsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CreateTransactionPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UUID uuid = (UUID) request.getSession().getAttribute("profileId");

            if (uuid == null) {
                response.sendRedirect("/sign_in");
                return;
            }

            Account defaultAccount = AccountsService.getDefaultAccountByProfileId(uuid);

            String from = request.getParameter("from");
            String to = request.getParameter("to");

            request.setAttribute("from", from);
            request.setAttribute("to", to);
            request.setAttribute("defaultAccount", defaultAccount.getId());

            request.getRequestDispatcher("/views/create_transaction.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
