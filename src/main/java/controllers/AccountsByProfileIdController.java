package main.java.controllers;

import main.java.dao.AccountDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Account;
import main.java.utils.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountsByProfileIdController extends BaseController {
    protected Command getCommand(String commandClassName) {
        return new AccountsByProfileIdCommand();
    }

    private static class AccountsByProfileIdCommand implements Command {
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String profileId = request.getParameter("id");
            assert !Objects.equals(profileId, "");

            AccountDao accountDao = DaoFactory.getAccountDao();
            List<Account> accounts = accountDao.getByProfileId(UUID.fromString(profileId));

            request.setAttribute("accounts", accounts);
            return "accounts.jsp";
        }
    }
}
