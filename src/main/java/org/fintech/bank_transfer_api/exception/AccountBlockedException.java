package org.fintech.bank_transfer_api.exception;

import java.util.UUID;

public class AccountBlockedException extends RuntimeException {
    public AccountBlockedException(UUID accountId) {
        super("Account is blocked: " + accountId);
    }
}
