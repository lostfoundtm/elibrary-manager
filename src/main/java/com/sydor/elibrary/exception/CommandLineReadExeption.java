package com.sydor.elibrary.exception;

public class CommandLineReadExeption extends ElibraryRuntimeException {
    public CommandLineReadExeption() {
    }

    public CommandLineReadExeption(String message) {
        super(message);
    }

    public CommandLineReadExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandLineReadExeption(Throwable cause) {
        super(cause);
    }

    public CommandLineReadExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
