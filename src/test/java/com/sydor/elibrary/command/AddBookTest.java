package com.sydor.elibrary.command;

import com.sydor.elibrary.Main;
import com.sydor.elibrary.entity.Book;
import com.sydor.elibrary.exception.InvalidArgumentsException;
import com.sydor.elibrary.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class AddBookTest {

    private final String author = "Author O.";
    private final String name = "Book Name";

    @Autowired
    @Qualifier("addBook")
    private Command addBook;

    @Autowired
    private BookService bookService;

    @Value("${elibrary.default-book-author}")
    private String defaultAuthor;

    @Test
    public void testExecuteWithNameAndAuthorArgs() throws InvalidArgumentsException {
        addBook.execute(new String[]{author, name});
        Book book = bookService.find(new Book(name, author));
        Assert.assertNotNull(book);
    }

    @Test
    public void testCorrectExecuteWithNameArgOnly() throws InvalidArgumentsException {
        addBook.execute(new String[]{name});
        Book book = bookService.find(new Book(name, "Unknown"));
        Assert.assertNotNull(book);
    }


    @Test(expected = InvalidArgumentsException.class)
    public void testExecuteWithNotEnoughArgs() throws InvalidArgumentsException {
        addBook.execute(new String[0]);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testExecuteWithTooManyArgs() throws InvalidArgumentsException {
        addBook.execute(new String[]{name, author, "something"});
    }
}
