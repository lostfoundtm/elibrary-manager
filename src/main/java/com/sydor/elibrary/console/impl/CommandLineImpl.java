package com.sydor.elibrary.console.impl;

import ch.qos.logback.classic.Logger;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.exception.CommandLineReadExeption;
import com.sydor.elibrary.exception.CommandLineWriteExeption;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.function.Predicate;

@Component
public class CommandLineImpl implements CommandLine {

    private static final Logger logger = (Logger) LoggerFactory.getLogger("common");

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            logger.error("Error while reading", e);
            throw new CommandLineReadExeption("An error occurred while reading", e);
        }
    }

    /**
     * Prints a line separator.
     */
    @Override
    public void newLine() {
        try {
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            logger.error("Error while writing", e);
            throw new CommandLineWriteExeption("An error occurred while writing", e);
        }
    }

    /**
     * Prints a number of line separators.
     *
     * @param number number of line separators
     */

    @Override
    public void skipLines(int number) {
        for (int i = 0; i < number; i++) {
            newLine();
        }
    }

    @Override
    public void print(String msg) {
        try {
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            logger.error("Error while writing", e);
            throw new CommandLineWriteExeption("An error occurred while writing", e);
        }
    }

    @Override
    public void println(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            logger.error("Error while writing", e);
            throw new CommandLineWriteExeption("An error occurred while writing", e);
        }
    }
}
