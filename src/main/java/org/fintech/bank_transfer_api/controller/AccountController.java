package org.fintech.bank_transfer_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fintech.bank_transfer_api.model.dto.AccountDto;
import org.fintech.bank_transfer_api.model.dto.CreateAccountDto;
import org.fintech.bank_transfer_api.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<Page<AccountDto>> getAllAccounts(Pageable pageable) {
        return ResponseEntity.ok(accountService.getAllAccounts(pageable));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(
            @RequestBody @Valid CreateAccountDto dto
            ) {
        AccountDto createdAccount = accountService.createAccount(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAccount);
    }
}
