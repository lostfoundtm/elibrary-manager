package com.sydor.elibrary.command;

import com.sydor.elibrary.exception.InvalidArgumentsException;

public interface Command {

    void execute(String[] args) throws InvalidArgumentsException;

    String getIdentifier();

    String getInvocation();

    String getDescription();

    String getMan();
}
