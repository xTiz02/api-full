package org.example.marvelapi.exception;
public class ApiErrorException extends RuntimeException {
    public ApiErrorException(String message) {
        super(message);
    }

    public ApiErrorException() {
        super();
    }

    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiErrorException(Throwable cause) {
        super(cause);
    }
}
