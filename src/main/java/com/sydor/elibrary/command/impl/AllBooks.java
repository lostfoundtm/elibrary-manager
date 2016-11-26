package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.entity.Book;
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

        showAllBooks();
    }

    private void showAllBooks() {
        List<Book> books = bookService.getAllSorted();
        if (books.size() > 0) {
            commandLine.println("You have " + books.size() + " books:");
            int i = 1;
            for (Book book : books) {
                commandLine.println("\t" + i++ + ". " + book);
            }
        } else {
            commandLine.println("You have no books.");
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
        return "displays books in your library";
    }

    @Override
    public String getMan() {
        return getInvocation() + " - " + getDescription() + "\n" +
                "Example:\n" +
                "\t$> all\n" +
                "\tYou have 2 books:\n" +
                "\t1. Martin \"A Song of Ice and Fire\n" +
                "\t2. J. Rowling \"Harry Potter\"";
    }
}
