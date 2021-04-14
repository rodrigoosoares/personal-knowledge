package com.personal.knowledge.personalknowledge.controllers.validation.handlers;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.*;

@ControllerAdvice
public class ResponseExceptionsHandler extends AbstractExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        final HttpServletRequest requestInfo = ((ServletWebRequest) request).getRequest();
        final ErrorResponse body = buildErrorResponse(requestInfo, status, createErrorsByException(ex));

        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final HttpServletRequest requestInfo = ((ServletWebRequest) request).getRequest();
        final ErrorResponse body = buildErrorResponse(requestInfo, status, createErrorsByException(ex));

        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final HttpServletRequest requestInfo = ((ServletWebRequest) request).getRequest();
        final ErrorResponse body = buildErrorResponse(requestInfo, status, createErrorsByException(ex));

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        final HttpServletRequest requestInfo = ((ServletWebRequest) request).getRequest();
        final ErrorResponse body = buildErrorResponse(requestInfo, HttpStatus.BAD_REQUEST, createErrorsByException(ex));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    private List<ErrorData> createErrorsByException(MethodArgumentNotValidException exception) {
        final List<ErrorData> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> errors.add(ErrorData.builder()
            .fieldName(((FieldError) error).getField())
            .message(error.getDefaultMessage())
            .build()));

        return errors;
    }

    private List<ErrorData> createErrorsByException(MissingServletRequestParameterException exception) {
        return Collections.singletonList(
            ErrorData.builder()
                .fieldName(exception.getParameterName())
                .message(exception.getMessage())
                .build()
        );
    }

    private List<ErrorData> createErrorsByException(TypeMismatchException exception) {
        return Collections.singletonList(
            ErrorData.builder()
                .fieldName(((MethodArgumentTypeMismatchException) exception).getName())
                .message(exception.getMessage())
                .build()
        );
    }

    private List<ErrorData> createErrorsByException(ConstraintViolationException exception) {
        final List<ErrorData> errors = new ArrayList<>();
        exception.getConstraintViolations().forEach(constraintViolation ->
            errors.add(
                ErrorData.builder()
                    .fieldName(getFieldName(constraintViolation))
                    .message(constraintViolation.getMessage())
                    .build()
            )
        );

        return errors;
    }

    private String getFieldName(final ConstraintViolation<?> violation) {
        Iterator<Path.Node> properties = violation.getPropertyPath().iterator();
        Path.Node currentNode = null;
        while (properties.hasNext()) {
            currentNode = properties.next();
        }

        if(Objects.nonNull(currentNode)) {
            return currentNode.getName();
        }

        return "";
    }

}
