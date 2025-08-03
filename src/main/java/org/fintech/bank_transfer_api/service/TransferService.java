package org.fintech.bank_transfer_api.service;

import lombok.RequiredArgsConstructor;
import org.fintech.bank_transfer_api.exception.AccountNotFoundException;
import org.fintech.bank_transfer_api.model.entity.Account;
import org.fintech.bank_transfer_api.model.entity.Transaction;
import org.fintech.bank_transfer_api.repository.AccountRepository;
import org.fintech.bank_transfer_api.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final TransactionalValidationService validationService;

    public Transaction transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {

        validationService.validateTransfer(fromAccountId, toAccountId, amount);

        Account fromAccount = accountRepo.findById(fromAccountId).orElseThrow(
                () -> new AccountNotFoundException(fromAccountId));
        Account toAccount = accountRepo.findById(toAccountId).orElseThrow(
                () -> new AccountNotFoundException(toAccountId));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient fund");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccountId);
        transaction.setToAccountId(toAccountId);
        transaction.setAmount(amount);
        transaction.setStatus(Transaction.TransactionStatus.COMPLETED);

        return transactionRepo.save(transaction);

    }

    public Page<Transaction> getHistory(UUID accountId, Pageable pageable) {
        if (!accountRepo.existsById(accountId)) {
            throw new AccountNotFoundException(accountId);
        }
        return transactionRepo.findByFromAccountIdOrToAccountId(accountId, accountId, pageable);
    }
}
