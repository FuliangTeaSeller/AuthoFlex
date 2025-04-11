package com.fuliang.authoflex.exception;

public class AfLoginException extends RuntimeException {
    public AfLoginException() {
        super();
    }

    public AfLoginException(String message) {
        super(message);
    }

    public AfLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public AfLoginException(Throwable cause) {
        super(cause);
    }
}
