package main.java.controller.command;

import main.java.models.Profile;
import main.java.service.AccountsService;
import main.java.service.ProfileService;
import main.java.types.AccountType;
import main.java.utils.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class SignUpCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            Profile p = ProfileService.signUp(login, password);

            if (p != null) {
                CookieUtil.setSessionCookie(response, p.getId());
                AccountsService.createAccount(AccountType.Default, new BigDecimal(0), p.getId());

                response.sendRedirect("/");
            } else {
                // TODO: handle error
                request.getRequestDispatcher("/views/sign_up.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
