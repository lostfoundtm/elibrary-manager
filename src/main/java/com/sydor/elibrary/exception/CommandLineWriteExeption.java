package com.sydor.elibrary.exception;

public class CommandLineWriteExeption extends ElibraryRuntimeException {
    public CommandLineWriteExeption() {
    }

    public CommandLineWriteExeption(String message) {
        super(message);
    }

    public CommandLineWriteExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandLineWriteExeption(Throwable cause) {
        super(cause);
    }

    public CommandLineWriteExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
