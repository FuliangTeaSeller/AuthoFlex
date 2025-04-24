package com.fuliang.authoflex.exception;

public class AfException extends RuntimeException {
    public AfException() {
        super();
    }

    public AfException(String message) {
        super(message);
    }

    public AfException(String message, Throwable cause) {
        super(message, cause);
    }

    public AfException(Throwable cause) {
        super(cause);
    }
}
