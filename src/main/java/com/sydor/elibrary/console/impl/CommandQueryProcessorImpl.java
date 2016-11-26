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

    private static final String SIMPLE_GROUP = "simple";
    private static final String IN_DOUBLE_QUOTES_GROUP = "indoublequotes";
    private static final String IN_SINGLE_QUOTES_GROUP = "insinglequotes";
    private static final String UNCLOSED_QUOTES_GROUP = "unclosedquotes";

    private Pattern createPattern() {
        return Pattern.compile("(?<" + SIMPLE_GROUP + ">[^\"'\\s]+)" +
                "|\"(?<" + IN_DOUBLE_QUOTES_GROUP + ">.+?)\"" +
                "|'(?<" + IN_SINGLE_QUOTES_GROUP + ">.+?)'" +
                "|(?<" + UNCLOSED_QUOTES_GROUP + ">[\"']+)");
    }

    @Override
    public CommandQuery processCommandQuery(String line) throws InvalidCommandQueryException {
        final Matcher matcher = createPattern().matcher(line);

        if (matcher.find() && matcher.group(SIMPLE_GROUP) != null) {
            CommandQuery commandQuery = new CommandQuery();
            List<String> args = new ArrayList<>();

            commandQuery.setCommand(matcher.group(SIMPLE_GROUP));

            while (matcher.find()) {
                if (matcher.group(UNCLOSED_QUOTES_GROUP) != null) {
                    throw new InvalidCommandQueryException("Unclosed quotes");
                }
                String arg = getArg(matcher);
                args.add(arg);
            }
            commandQuery.setArgs(args.toArray(new String[args.size()]));
            return commandQuery;
        }
        throw new InvalidCommandQueryException("Command not found");
    }

    private String getArg(Matcher matcher) {
        return matcher.group(SIMPLE_GROUP) != null ?
                matcher.group(SIMPLE_GROUP) : matcher.group(IN_DOUBLE_QUOTES_GROUP) != null ?
                matcher.group(IN_DOUBLE_QUOTES_GROUP) : matcher.group(IN_SINGLE_QUOTES_GROUP) != null ?
                matcher.group(IN_SINGLE_QUOTES_GROUP) : null;
    }
}
