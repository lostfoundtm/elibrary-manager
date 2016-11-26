package com.sydor.elibrary.command;

import com.sydor.elibrary.exception.CommandInvocationException;

public interface Command {

    void execute(String[] args) throws CommandInvocationException;

    String getIdentifier();

    String getInvocation();

    String getDescription();

    String getMan();
}
