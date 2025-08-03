package org.fintech.bank_transfer_api.model.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiError(
        String code,
        String message,
        HttpStatus status,
        Instant timestamp
) {
}
