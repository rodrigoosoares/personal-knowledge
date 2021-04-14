package com.personal.knowledge.personalknowledge.controllers.validation.handlers;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;
import com.personal.knowledge.personalknowledge.controllers.validation.exceptions.InvalidHeaderException;
import com.personal.knowledge.personalknowledge.controllers.validation.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class CustomResponseExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(final UnauthorizedException exception, WebRequest request) {
        final HttpServletRequest requestInfo = ((ServletWebRequest) request).getRequest();

        final ErrorResponse errorResponse = buildErrorResponse(requestInfo, HttpStatus.UNAUTHORIZED, createErrorsByException(exception));

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidHeaderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidHeaderException(final InvalidHeaderException exception, WebRequest request) {
        final HttpServletRequest requestInfo = ((ServletWebRequest) request).getRequest();

        final ErrorResponse errorResponse = buildErrorResponse(requestInfo, HttpStatus.BAD_REQUEST, createErrorsByException(exception));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private List<ErrorData> createErrorsByException(final UnauthorizedException exception) {
        return Collections.singletonList(
            ErrorData.builder()
                .fieldName(exception.getRequestField())
                .message(exception.getMessage())
            .build()
        );
    }

    private List<ErrorData> createErrorsByException(final InvalidHeaderException exception) {
        return Collections.singletonList(
            ErrorData.builder()
                .fieldName(exception.getRequestField())
                .message(exception.getMessage())
                .build()
        );
    }

}
