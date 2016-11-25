package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.app.Application;
import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.exception.InvalidArgumentsException;
import com.sydor.elibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("exit")
public class Exit implements Command {

    public static final String IDENTIFIER = "exit";

    @Autowired
    private Application application;

    @Override
    public void execute(String[] args) throws InvalidArgumentsException {
        if (args != null && args.length > 0) {
            throw new InvalidArgumentsException("Invalid number of command arguments");
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
        return "exits the e-library";
    }

    @Override
    public String getMan() {
        //todo man
        return "";
    }

}
