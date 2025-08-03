package org.fintech.bank_transfer_api.model.dto;

import org.fintech.bank_transfer_api.model.entity.Account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        UUID id,
        String ownerName,
        BigDecimal balance,
        String currency,
        boolean active
) {
    public static AccountDto fromEntity(Account account) {
        return new AccountDto(
                account.getId(),
                account.getOwnerName(),
                account.getBalance(),
                account.getCurrency(),
                account.isActive()
        );
    }
}
