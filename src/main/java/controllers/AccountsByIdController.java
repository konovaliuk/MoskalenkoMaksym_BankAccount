package main.java.controllers;

import main.java.dao.factory.DaoFactory;
import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.utils.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountsByIdController extends BaseController  {
    protected Command getCommand(String commandClassName) {
        return new AccountsByIdCommand();
    }

    private class AccountsByIdCommand implements Command {
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String id = request.getParameter("id");
            assert !Objects.equals(id, "");

            AccountDao accountDao = DaoFactory.getAccountDao();
            List<Account> accounts = accountDao.getByProfileId(UUID.fromString(id));

            request.setAttribute("accounts", accounts);
            return "accounts.jsp";
        }
    }
}
