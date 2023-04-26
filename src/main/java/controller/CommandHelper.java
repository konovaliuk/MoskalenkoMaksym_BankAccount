package main.java.controller;

import main.java.controller.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    Map<String, Command> commands = new HashMap<>();

    public CommandHelper() {
        commands.put("GET:/sign_in", new SignInPageCommand());
        commands.put("GET:/sign_up", new SignUpPageCommand());
        commands.put("GET:/", new IndexPageCommand());
        commands.put("GET:/account", new AccountPageCommand());
        commands.put("GET:/create_account", new CreateAccountPageCommand());
        commands.put("GET:/create_transaction", new CreateTransactionPageCommand());
        commands.put("GET:/accounts_to_approve", new AccountsToApprovePage());

        commands.put("POST:/sign_in", new SignInCommand());
        commands.put("POST:/sign_up", new SignUpCommand());
        commands.put("POST:/sign_out", new SignOutCommand());
        commands.put("POST:/create_account", new CreateAccountCommand());
        commands.put("POST:/create_transaction", new CreateTransactionCommand());
        commands.put("POST:/credit_approve", new CreditApproveCommand());
    }

    public Command getCommand(String command, String method) {
        String key = method + ":" + command;

        if (commands.containsKey(key))
            return commands.get(key);
        // TODO: handle error
        throw new RuntimeException("Unknown command");
    }
}