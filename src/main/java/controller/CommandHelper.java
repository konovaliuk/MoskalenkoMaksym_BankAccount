package main.java.controller;

import main.java.controller.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    Map<String, Command> commands = new HashMap<>();

    public CommandHelper() {
        commands.put("GET:/sign_in", new SignInPageCommand());
        commands.put("GET:/sign_up", new SignUpPageCommand());

        commands.put("POST:/sign_in", new SignInCommand());
        commands.put("POST:/sign_up", new SignUpCommand());
        commands.put("GET:/", new IndexPageCommand());
    }

    public Command getCommand(String command, String method) {
        String key = method + ":" + command;

        if (commands.containsKey(key))
            return commands.get(key);
        // TODO: handle error
        throw new RuntimeException("Unknown command");
    }
}