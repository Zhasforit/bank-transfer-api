package org.fintech.bank_transfer_api.service;

import lombok.RequiredArgsConstructor;
import org.fintech.bank_transfer_api.exception.AccountBlockedException;
import org.fintech.bank_transfer_api.exception.AccountNotFoundException;
import org.fintech.bank_transfer_api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionalValidationService {

    private final AccountRepository accountRepo;

    public void validateTransfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (!accountRepo.existsById(fromAccountId)) {
            throw new AccountNotFoundException(fromAccountId);
        }

        if (!accountRepo.existsById((toAccountId))) {
            throw new AccountNotFoundException(toAccountId);
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer must be positive");
        }

        if (!accountRepo.existsByIdAndActiveTrue(fromAccountId)) {
            throw new AccountBlockedException(fromAccountId);
        }
    }
}
