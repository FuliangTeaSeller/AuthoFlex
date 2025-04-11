package com.fuliang.authoflex.exception;

public class NotAfLoginException extends AfLoginException {
    public NotAfLoginException() {
        super();
    }

    public NotAfLoginException(String message) {
        super(message);
    }

    public NotAfLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAfLoginException(Throwable cause) {
        super(cause);
    }
}