package com.raynigon.rqms.api.general.controller;


import com.raynigon.rqms.api.general.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * This is a generic ControllerAdvice that handles all common MVC exceptions and formats them
 * as {@link ApiError}.
 * <p>
 * These are mostly a relict of accidental complexity: we don't want them in our project,
 * but they could happen because we use HTTP. They are not of interest to our clients and
 * don't require further documentation.
 */
@SuppressWarnings("unchecked")
@ControllerAdvice
public class FallbackControllerAdvice extends ResponseEntityExceptionHandler {


    private final Logger structuredLogger = LoggerFactory.getLogger(getClass());

    // wraps responses from ResponseEntityExceptionHandler in our ApiError format
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request
    ) {
        ResponseEntity<Object> internalResponse = super.handleExceptionInternal(ex, body, headers, statusCode, request);
        ResponseEntity<ApiError> response = new ApiError(
                internalResponse == null ? HttpStatus.INTERNAL_SERVER_ERROR : internalResponse.getStatusCode(),
                body != null ? body.toString() : null,
                List.of()
        ).toResponseEntity();
        return ((ResponseEntity<Object>) ((Object) response));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        structuredLogger.warn("Invalid input was given", ex);
        ResponseEntity<ApiError> response = new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Unprocessable entity",
                ex.getBindingResult().getAllErrors().stream().map(it -> {
                    if (it instanceof FieldError) {
                        return ((FieldError) it).getField() + " " + it.getDefaultMessage();
                    } else {
                        return it.toString();
                    }
                }).toList()
        ).toResponseEntity();
        return ((ResponseEntity<Object>) ((Object) response));
    }
}


