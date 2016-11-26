package com.sydor.elibrary.exception;

public class CommandInvocationException extends ElibraryException {
    public CommandInvocationException() {
    }

    public CommandInvocationException(String message) {
        super(message);
    }

    public CommandInvocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandInvocationException(Throwable cause) {
        super(cause);
    }

    public CommandInvocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
