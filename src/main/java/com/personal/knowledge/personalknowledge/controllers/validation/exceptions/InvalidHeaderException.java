package com.personal.knowledge.personalknowledge.controllers.validation.exceptions;

public class InvalidHeaderException extends RuntimeException {

    private final String requestField;

    public InvalidHeaderException(String message, String requestField) {
        super(message);
        this.requestField = requestField;
    }

    public String getRequestField() {
        return requestField;
    }
}
