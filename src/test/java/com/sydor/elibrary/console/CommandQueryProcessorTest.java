package com.sydor.elibrary.console;

import com.sydor.elibrary.Main;
import com.sydor.elibrary.command.CommandQuery;
import com.sydor.elibrary.exception.InvalidCommandQueryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class CommandQueryProcessorTest {

    @Autowired
    private CommandQueryProcessor commandQueryProcessor;

    @Test
    public void testProcessCommandWithCorrectQuery() throws InvalidCommandQueryException {
        final String line = " cmd asd a as \"Asd A.D.\"  \" Asd asd - asd asd asd  \" 'asd  asd' \"'asd' - asd\" 'asd \"asd \" asd'";
        CommandQuery pc = commandQueryProcessor.processCommandQuery(line);
        Assert.assertEquals(pc.getCommand(), "cmd");
        Assert.assertTrue(Arrays.equals(pc.getArgs(), new String[]{
                "asd", "a", "as", "Asd A.D.", " Asd asd - asd asd asd  ", "asd  asd", "'asd' - asd", "asd \"asd \" asd"
        }));
    }

    @Test(expected = InvalidCommandQueryException.class)
    public void testProcessCommandWithIncorrectQuery1() throws InvalidCommandQueryException {
        commandQueryProcessor.processCommandQuery("\"Asd A.D.\" asd");
    }

    @Test(expected = InvalidCommandQueryException.class)
    public void testProcessCommandWithIncorrectQuery2() throws InvalidCommandQueryException {
        commandQueryProcessor.processCommandQuery("\'Asd A.D.\' asd");
    }

    @Test(expected = InvalidCommandQueryException.class)
    public void testProcessCommandWithEmptyQuery() throws InvalidCommandQueryException {
        commandQueryProcessor.processCommandQuery("");
    }
}
