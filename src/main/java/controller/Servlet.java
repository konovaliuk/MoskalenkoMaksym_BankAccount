package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/", "/sign_in", "/sign_up", "/account", "/create_account", "/create_transaction", "/accounts_to_approve", "/credit_approve"})
public class Servlet extends HttpServlet {
    CommandHelper commandHelper = new CommandHelper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        commandHelper.getCommand(path, "GET").execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        commandHelper.getCommand(path, "POST").execute(req, resp);
    }
}
