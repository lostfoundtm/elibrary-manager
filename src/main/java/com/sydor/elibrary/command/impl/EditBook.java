package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.entity.Book;
import com.sydor.elibrary.exception.CommandInvocationException;
import com.sydor.elibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("editBook")
public class EditBook implements Command {

    public static final String IDENTIFIER = "edit";

    @Value("${elibrary.default-book-author}")
    private String defaultAuthor;

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
        if (books.size() < 1) {
            commandLine.println("Not found any books with such name.");
        } else if (books.size() == 1) {
            edit(books.get(0));
        } else {
            commandLine.println("Found " + books.size() + " books with such name:");
            int i = 1;
            for (Book book : books) {
                commandLine.println(i++ + ". " + book);
            }
            commandLine.print("Type number of book you want to edit or 'q' to cancel: ");
            while (true) {
                String input = commandLine.readLine();
                if (input.equalsIgnoreCase("q")) {
                    break;
                } else {
                    try {
                        Book book = books.get(Integer.parseInt(input) - 1);
                        edit(book);
                        break;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        commandLine.print("Incorrect input! Please type a correct number or 'q' to cancel: ");
                    }
                }
            }
        }
    }

    private void edit(Book book) {
        commandLine.println("Editing book '" + book + "':");
        commandLine.println("\tType book new name or press 'enter' to skip");
        commandLine.print("\tName [" + book.getName() + "]: ");
        String name = commandLine.readLine();
        if (!name.isEmpty()) {
            book.setName(name);
        }
        commandLine.println("\tType book new author or press 'enter' to skip or type '-' to set default");
        commandLine.print("\tAuthor [" + book.getAuthor() + "]: ");
        String author = commandLine.readLine();
        if (author.equals("-")) {
            book.setAuthor(defaultAuthor);
        } else if (!author.isEmpty()) {
            book.setAuthor(author);
        }
        if (bookService.find(book) == null) {
            bookService.save(book);
            commandLine.println("Book '" + book + "' was updated.");
        } else {
            commandLine.println("Such book '" + book + "' already exists.");
            commandLine.print("Press 'enter' to change try change something or type 'q' to cancel: ");
            while (true) {
                String input = commandLine.readLine();
                if (input.equalsIgnoreCase("q")) {
                    break;
                } else if (input.isEmpty()) {
                    edit(book);
                    break;
                } else {
                    commandLine.print("Incorrect input! Press 'enter' to change try change something or type 'q' to cancel: ");
                }
            }
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
        return "remove specified book from library";
    }

    @Override
    public String getMan() {
        //todo man
        return "";
    }

}
