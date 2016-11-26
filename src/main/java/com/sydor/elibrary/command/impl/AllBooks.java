package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.exception.CommandInvocationException;
import com.sydor.elibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("allBooks")
public class AllBooks implements Command {

    public static final String IDENTIFIER = "all";

    @Autowired
    private BookService bookService;

    @Autowired
    private CommandLine commandLine;

    @Override
    public void execute(String[] args) throws CommandInvocationException {
        if (args != null && args.length > 0) {
            throw new CommandInvocationException("Invalid number of command arguments");
        }

        List<String> bookNames = bookService.findDistinctSortedNames();
        if (bookNames.size() > 0) {
            commandLine.println("Your books:");
            bookNames.forEach(commandLine::println);
        } else {
            commandLine.println("You have not any books.");
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
        return "displays list of books in library";
    }

    @Override
    public String getMan() {
        //todo man
        return "";
    }

}
