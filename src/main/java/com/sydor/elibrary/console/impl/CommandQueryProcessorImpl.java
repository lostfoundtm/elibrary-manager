package com.sydor.elibrary.console.impl;

import com.sydor.elibrary.command.CommandQuery;
import com.sydor.elibrary.console.CommandQueryProcessor;
import com.sydor.elibrary.exception.InvalidCommandQueryException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandQueryProcessorImpl implements CommandQueryProcessor {

    @Override
    public CommandQuery processCommandQuery(String line) throws InvalidCommandQueryException {
        final Matcher matcher = Pattern.compile("(?<simple>[^\"'\\s]+)" +
                "|[\"](?<indoublequotes>.+?)[\"]" +
                "|['](?<insinglequotes>.+?)[']").matcher(line);

        if (matcher.find() && matcher.group("simple") != null) {
            CommandQuery commandQuery = new CommandQuery();
            List<String> args = new ArrayList<>();

            commandQuery.setCommand(matcher.group("simple"));

            while (matcher.find()) {
                String arg = matcher.group("simple") != null ?
                        matcher.group("simple") : matcher.group("indoublequotes") != null ?
                        matcher.group("indoublequotes") : matcher.group("insinglequotes") != null ?
                        matcher.group("insinglequotes") : "";
                args.add(arg);
            }
            commandQuery.setArgs(args.toArray(new String[args.size()]));
            return commandQuery;
        }
        throw new InvalidCommandQueryException("Command not found");
    }
}
