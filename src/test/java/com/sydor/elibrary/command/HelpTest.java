package com.sydor.elibrary.command;

import com.sydor.elibrary.Main;
import com.sydor.elibrary.exception.InvalidArgumentsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class HelpTest {

    @Autowired
    @Qualifier("help")
    private Command help;

    @Test
    public void testExecute() throws InvalidArgumentsException {
        help.execute(null);
    }
}
