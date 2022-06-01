package com.userservice.exception;


public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public InvalidRequestException() {

    }

    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public InvalidRequestException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
