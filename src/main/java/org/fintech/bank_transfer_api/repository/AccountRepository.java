package org.fintech.bank_transfer_api.repository;

import org.fintech.bank_transfer_api.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByOwnerName(String ownerName);

    boolean existsByIdAndActiveTrue(UUID accountId);

    boolean existsById(UUID accountId);

    Page<Account> findAll(Pageable pageable);
}
