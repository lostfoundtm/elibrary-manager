package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.app.Application;
import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.exception.CommandInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("exit")
public class Exit implements Command {

    public static final String IDENTIFIER = "exit";

    @Autowired
    private Application application;

    @Override
    public void execute(String[] args) throws CommandInvocationException {
        if (args != null && args.length > 0) {
            throw new CommandInvocationException("Invalid number of command arguments");
        }

        application.stop();
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
        return "exits the library";
    }

    public String getMan() {
        return getInvocation() + " - " + getDescription();
    }
}
