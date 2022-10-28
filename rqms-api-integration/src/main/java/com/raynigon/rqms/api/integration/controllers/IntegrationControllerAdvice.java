package com.raynigon.rqms.api.integration.controllers;

import com.raynigon.rqms.api.integration.exceptions.SearchEngineNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = {SearchEngineController.class, SearchQueryController.class})
public class IntegrationControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SearchEngineNotFoundException.class)
    public ResponseEntity<Object> handleBikeNotFoundException(SearchEngineNotFoundException exception) {
        logger.warn("A search engine was not found", exception);
        return ResponseEntity.notFound().build();
    }
}
