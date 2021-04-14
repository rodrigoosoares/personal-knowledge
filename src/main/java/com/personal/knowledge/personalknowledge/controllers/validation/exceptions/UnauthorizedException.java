package com.personal.knowledge.personalknowledge.controllers.validation.exceptions;

public class UnauthorizedException extends RuntimeException {

    private final String requestField;

    public UnauthorizedException(final String message, final String requestField) {
        super(message);

        this.requestField = requestField;
    }

    public String getRequestField() {
        return this.requestField;
    }
}
