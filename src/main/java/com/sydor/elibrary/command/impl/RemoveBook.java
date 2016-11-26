package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.entity.Book;
import com.sydor.elibrary.exception.CommandInvocationException;
import com.sydor.elibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("removeBook")
public class RemoveBook implements Command {

    public static final String IDENTIFIER = "remove";

    @Autowired
    private BookService bookService;

    @Autowired
    private CommandLine commandLine;

    @Override
    public void execute(String[] args) throws CommandInvocationException {
        if (args == null || args.length != 1) {
            throw new CommandInvocationException("Invalid number of command arguments");
        }

        List<Book> books = bookService.findByName(args[0]);
        if (books.isEmpty()) {
            commandLine.println("Not found any books with such name.");
        } else if (books.size() == 1) {
            remove(books.get(0));
        } else {
            commandLine.println("Found " + books.size() + " books with such name:");
            int i = 1;
            for (Book book : books) {
                commandLine.println("\t" + i++ + ". " + book);
            }
            commandLine.print("Type number of book you want to remove or 'q' to cancel: ");
            while (true) {
                String param = commandLine.readLine();
                if (param.equalsIgnoreCase("q")) {
                    break;
                } else {
                    try {
                        remove(books.get(Integer.parseInt(param) - 1));
                        break;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        commandLine.print("Incorrect input! Please type a correct number or 'q' to cancel: ");
                    }
                }
            }
        }
    }

    private void remove(Book book) {
        bookService.remove(book.getId());
        commandLine.println("Book " + book + " was removed.");
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public String getInvocation() {
        return getIdentifier() + " <book name>";
    }

    @Override
    public String getDescription() {
        return "remove specified book from the library";
    }

    @Override
    public String getMan() {
        return getInvocation() + " - " + getDescription() + "\n" +
                "Example:\n" +
                "\t$> remove \"A Song of Ice and Fire\"\n" +
                "\tBook J. Martin \"A Song of Ice and Fire\" was removed.";
    }
}
