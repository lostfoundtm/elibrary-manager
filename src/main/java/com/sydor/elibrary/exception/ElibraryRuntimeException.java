package com.sydor.elibrary.exception;

public class ElibraryRuntimeException extends RuntimeException {
    public ElibraryRuntimeException() {
    }

    public ElibraryRuntimeException(String message) {
        super(message);
    }

    public ElibraryRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElibraryRuntimeException(Throwable cause) {
        super(cause);
    }

    public ElibraryRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
