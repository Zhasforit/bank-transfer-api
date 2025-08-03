package org.fintech.bank_transfer_api.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateAccountDto(
        @NotBlank(message = "Owner name is required")
        @Size(max = 100, message = "Owner name too long")
        String ownerName,

        @NotNull(message = "Initial balance is required")
        @DecimalMin(value = "0.0", message = "Balance cannot be negative")
        BigDecimal initialBalance,

        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be 3 characters")
        String currency
) {
}
