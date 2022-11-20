package com.raynigon.rqms.api.general.model;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Generic DTO for API Errors
 * */
public record ApiError(
        HttpStatusCode status,
        String message,
        List<String> errors
) {
    public ResponseEntity<ApiError> toResponseEntity() {
        return new ResponseEntity<>(this, this.status);
    }
}
