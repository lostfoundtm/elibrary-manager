package com.sydor.elibrary.command.impl;

import com.sydor.elibrary.command.Command;
import com.sydor.elibrary.command.CommandLibrary;
import com.sydor.elibrary.exception.NoSuchCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("defaultCommandList")
public class DefaultCommandLibrary implements CommandLibrary {

    @Autowired
    private List<Command> commands;
    private Map<String, Command> commandMap = new HashMap<>();

    @PostConstruct
    private void init() {
        commands.forEach(command -> commandMap.put(command.getIdentifier(), command));
    }

    /**
     * Returns a command with specified name.
     *
     * @param name command name
     * @return command with specified name
     * @throws NoSuchCommandException if command with such name does not exist
     */
    @Override
    public Command getCommand(String name) throws NoSuchCommandException {
        if (commandMap.containsKey(name)) {
            return commandMap.get(name);
        }
        throw new NoSuchCommandException("Command '" + name + "' does not exist");
    }

    @Override
    public Collection<Command> getCommandList() {
        return commandMap.values();
    }
}
