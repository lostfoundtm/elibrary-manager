package com.sydor.elibrary.command.impl;

import com.sun.istack.internal.NotNull;
import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.entity.Book;
import com.sydor.elibrary.exception.InvalidArgumentsException;
import com.sydor.elibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("addBook")
public class AddBook implements Command {

    public static final String IDENTIFIER = "add";

    @Value("${elibrary.default-book-author}")
    private String defaultAuthor;

    @Autowired
    private BookService bookService;
    @Autowired
    private CommandLine commandLine;

    @Override
    public void execute(String[] args) throws InvalidArgumentsException {
        Book book;
        if (args == null || args.length < 1 || args.length > 2) {
            throw new InvalidArgumentsException("Invalid number of command arguments");
        }

        if (args.length > 1) {
            book = new Book(args[1], args[0]);
        } else {
            book = new Book(args[0], defaultAuthor);
        }
        if (bookService.find(book) == null) {
            bookService.save(book);
            commandLine.println("book '" + book + "' was added");
        } else {
            commandLine.println("such book '" + book + "' already exists");
        }
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public String getInvocation() {
        return getIdentifier() + " [<author>] <book name>";
    }

    @Override
    public String getDescription() {
        return "adds book to library";
    }

    @Override
    public String getMan() {
        //todo man
        return "";
    }
}
