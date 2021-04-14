package com.personal.knowledge.personalknowledge.controllers.validation.handlers;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;

public abstract class AbstractExceptionHandler extends ResponseEntityExceptionHandler {

    protected ErrorResponse buildErrorResponse(HttpServletRequest requestInfo, HttpStatus badRequest, List<ErrorData> errors) {
        return ErrorResponse.builder()
            .timestamp(Instant.now().toString())
            .status(createCompleteStatus(badRequest))
            .path(requestInfo.getRequestURI())
            .method(requestInfo.getMethod())
            .errors(errors)
            .build();
    }

    private String createCompleteStatus(HttpStatus status) {
        return String.format("%s %s", status.value(), status.getReasonPhrase());
    }

}
