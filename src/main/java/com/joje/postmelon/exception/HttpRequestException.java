package com.joje.postmelon.exception;

public class HttpRequestException extends RuntimeException {
    public HttpRequestException() {}
    public HttpRequestException(String message) {
        super(message);
    }
    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
