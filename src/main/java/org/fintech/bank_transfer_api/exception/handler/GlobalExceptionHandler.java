package org.fintech.bank_transfer_api.exception.handler;

import org.fintech.bank_transfer_api.model.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError error = new ApiError(
                "INVALID_REQUEST",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                Instant.now()
        );
        return ResponseEntity.badRequest().body(error);
    }
}
