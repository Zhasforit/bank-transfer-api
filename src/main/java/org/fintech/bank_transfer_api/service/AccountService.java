package org.fintech.bank_transfer_api.service;

import lombok.RequiredArgsConstructor;
import org.fintech.bank_transfer_api.model.dto.AccountDto;
import org.fintech.bank_transfer_api.model.dto.CreateAccountDto;
import org.fintech.bank_transfer_api.model.entity.Account;
import org.fintech.bank_transfer_api.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepo;

    public Page<AccountDto> getAllAccounts(Pageable pageable) {
        return accountRepo.findAll(pageable)
                .map(AccountDto::fromEntity);
    }

    public AccountDto createAccount(CreateAccountDto dto) {
        Account account = new Account();
        account.setOwnerName(dto.ownerName());
        account.setBalance(dto.initialBalance());
        account.setCurrency(dto.currency());
        account.setActive(true);

        Account savedAccount = accountRepo.save(account);
        return AccountDto.fromEntity(savedAccount);
    }
}
