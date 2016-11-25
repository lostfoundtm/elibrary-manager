package com.sydor.elibrary.console;

import com.sydor.elibrary.command.CommandQuery;
import com.sydor.elibrary.exception.InvalidCommandQueryException;

public interface CommandQueryProcessor {
    CommandQuery processCommandQuery(String line) throws InvalidCommandQueryException;
}
