package com.userservice.exception;

public class InvalidFileException extends RuntimeException {

    private String errorMessage;
    private Integer errorCode;

    public InvalidFileException() {
    }

    public InvalidFileException(String errorMessage, Integer errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public InvalidFileException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
