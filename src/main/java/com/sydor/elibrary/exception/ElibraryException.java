package com.sydor.elibrary.exception;

public class ElibraryException extends Exception {
    public ElibraryException() {
    }

    public ElibraryException(String message) {
        super(message);
    }

    public ElibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElibraryException(Throwable cause) {
        super(cause);
    }

    public ElibraryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
