package org.fintech.bank_transfer_api.exception;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(UUID accountId) {
        super("Account not found with ID: " + accountId);
    }
}
