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
public class AllBooksTest {

    @Autowired
    @Qualifier("allBooks")
    private Command allBooks;

    @Test
    public void testExecute() throws InvalidArgumentsException {
        allBooks.execute(null);
    }
}
