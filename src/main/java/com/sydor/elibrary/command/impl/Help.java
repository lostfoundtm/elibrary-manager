package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.command.CommandLibrary;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.exception.CommandInvocationException;
import com.sydor.elibrary.exception.NoSuchCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("help")
public class Help implements Command {

    public static final String IDENTIFIER = "help";

    @Autowired
    private CommandLine commandLine;

    @Autowired
    private CommandLibrary commandLibrary;

    @Override
    public void execute(String[] args) throws CommandInvocationException {
        if (args == null || args.length < 0) {
            commandLibrary.getCommandList().forEach(command -> commandLine.println(String.format("%-30s - %-10s",
                    command.getInvocation(), command.getDescription())));
            commandLine.println("Type 'help <command>' to get detail information about each command.");
        } else if (args.length == 1 && !args[0].equals(IDENTIFIER)) {
            try {
                Command command = commandLibrary.getCommand(args[0]);
                String man = command.getMan();
                if (man != null && !man.isEmpty()) {
                    commandLine.println(command.getMan());
                } else {
                    commandLine.println("Command '" + args[0] + "' does not have a manual.");
                }
            } catch (NoSuchCommandException e) {
                commandLine.println("Command '" + args[0] + "' not found.");
            }
        } else {
            throw new CommandInvocationException("Invalid number of command arguments");
        }
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public String getInvocation() {
        return getIdentifier();
    }

    @Override
    public String getDescription() {
        return "displays this list";
    }

    @Override
    public String getMan() {
        return ""; //unused
    }
}
