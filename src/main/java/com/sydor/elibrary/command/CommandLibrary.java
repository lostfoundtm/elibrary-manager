package com.sydor.elibrary.command;

import com.sydor.elibrary.exception.NoSuchCommandException;

import java.util.Collection;

public interface CommandLibrary {
    Command getCommand(String name) throws NoSuchCommandException;

    Collection<Command> getCommandList();
}
