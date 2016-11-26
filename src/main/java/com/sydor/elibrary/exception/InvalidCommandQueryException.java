package com.sydor.elibrary.exception;

public class InvalidCommandQueryException extends ElibraryException {
    public InvalidCommandQueryException() {
    }

    public InvalidCommandQueryException(String message) {
        super(message);
    }

    public InvalidCommandQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandQueryException(Throwable cause) {
        super(cause);
    }

    public InvalidCommandQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
