package com.joje.postmelon.exception;

public class NotFoundDataException extends RuntimeException {

    public NotFoundDataException() {}
    public NotFoundDataException(String message) {
        super(message);
    }
    public NotFoundDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
