package com.sydor.elibrary.app;

import ch.qos.logback.classic.Logger;
import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.command.CommandLibrary;
import com.sydor.elibrary.command.CommandQuery;
import com.sydor.elibrary.command.impl.Help;
import com.sydor.elibrary.console.CommandLine;
import com.sydor.elibrary.console.CommandQueryProcessor;
import com.sydor.elibrary.exception.CommandInvocationException;
import com.sydor.elibrary.exception.InvalidCommandQueryException;
import com.sydor.elibrary.exception.NoSuchCommandException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Application {

    private static final Logger logger = (Logger) LoggerFactory.getLogger("common");

    private boolean isRunning = false;

    @Value("${elibrary.logo}")
    private String logo;
    @Autowired
    private CommandLine commandLine;
    @Autowired
    private CommandQueryProcessor commandQueryProcessor;
    @Autowired
    @Qualifier("defaultCommandList")
    private CommandLibrary commandLibrary;

    public void start() {
        isRunning = true;

        onStartup();

        while (isRunning) {
            commandLine.print("$> ");
            processCommandQuery(commandLine.readLine());
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
        }
    }

    private void onStartup() {
        commandLine.println(logo);
        commandLine.newLine();
        commandLine.println("Here is command list:");
        try {
            commandLibrary.getCommand(Help.IDENTIFIER).execute(null);
        } catch (NoSuchCommandException e) {
            logger.error("Help command no found!");
            commandLine.println("Sorry help command not found.");
        } catch (CommandInvocationException e) {
            logger.error("Failed to run help command", e);
        }
    }

    private void processCommandQuery(String query) {
        try {
            CommandQuery commandQuery = commandQueryProcessor.processCommandQuery(query);
            Command command = commandLibrary.getCommand(commandQuery.getCommand());
            command.execute(commandQuery.getArgs());
        } catch (InvalidCommandQueryException e) {
            commandLine.println("Invalid command query (" + e.getMessage() + ").\nType 'help' to get help info.");
        } catch (NoSuchCommandException e) {
            commandLine.println("Invalid command (" + e.getMessage() + ").\nType 'help' to get command list.");
        } catch (CommandInvocationException e) {
            commandLine.println("Invalid command invocation.\nType 'help <command>' to get detail command info.");
        }
    }
}
