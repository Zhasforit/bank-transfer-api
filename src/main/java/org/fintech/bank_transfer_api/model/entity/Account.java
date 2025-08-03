package org.fintech.bank_transfer_api.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean active = true;

    @Column(updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

}
