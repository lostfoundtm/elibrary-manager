package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.entity.Book;
import com.sydor.elibrary.exception.InvalidArgumentsException;
import com.sydor.elibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("allBooks")
public class AllBooks implements Command {

    public static final String IDENTIFIER = "all";

    @Autowired
    private BookService bookService;

    @Autowired
    private CommandLine commandLine;

    @Override
    public void execute(String[] args) throws InvalidArgumentsException {
        if (args != null && args.length > 0) {
            throw new InvalidArgumentsException("Invalid number of command arguments");
        }

        List<Book> books = bookService.findAll();
        books.sort(Comparator.comparing(Book::getName));
        int i = 1;
        for (Book book : books) {
            commandLine.println(i++ + ". " + book);
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
