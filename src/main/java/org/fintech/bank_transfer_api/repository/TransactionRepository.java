package org.fintech.bank_transfer_api.repository;

import org.fintech.bank_transfer_api.model.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findByFromAccountIdOrToAccountId(
            UUID fromAccountId, UUID toAccountId, Pageable pageable
    );

    boolean existsByFromAccountIdAndToAccountIdAndAmountAndStatus(
            UUID fromAccountId, UUID toAccountId, BigDecimal amount, Transaction.TransactionStatus status
    );
}
