package org.fintech.bank_transfer_api.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fintech.bank_transfer_api.model.dto.TransferRequest;
import org.fintech.bank_transfer_api.model.entity.Transaction;
import org.fintech.bank_transfer_api.service.TransferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody @Valid TransferRequest request) {
        return ResponseEntity.ok(transferService.transfer(
                request.fromAccountId(),
                request.toAccountId(),
                request.amount()
        ));
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<Page<Transaction>> history(
            @PathVariable UUID accountId,
            Pageable pageable
            ) {
        return ResponseEntity.ok(transferService.getHistory(accountId, pageable));
    }
}
