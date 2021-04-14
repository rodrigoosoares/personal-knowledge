package com.personal.knowledge.personalknowledge.controllers.validation.exceptions;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;

public class NotAccessException extends RuntimeException {

    private ErrorResponse error;

    public NotAccessException(final ErrorResponse error) {
        this.error = error;
    }
}
