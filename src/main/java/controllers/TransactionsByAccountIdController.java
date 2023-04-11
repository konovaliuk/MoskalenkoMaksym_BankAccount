package main.java.controllers;

import main.java.dao.TransactionDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Transaction;
import main.java.utils.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TransactionsByAccountIdController extends BaseController {
    protected Command getCommand(String commandClassName) {
        return new TransactionsByAccountIdCommand();
    }

    private static class TransactionsByAccountIdCommand implements Command {
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String accountId = request.getParameter("id");
            assert !Objects.equals(accountId, "");

            TransactionDao transactionDao = DaoFactory.getTransactionDao();
            List<Transaction> transactions = transactionDao.getByAccountId(UUID.fromString(accountId));

            request.setAttribute("transactions", transactions);
            return "transactions.jsp";
        }
    }
}
