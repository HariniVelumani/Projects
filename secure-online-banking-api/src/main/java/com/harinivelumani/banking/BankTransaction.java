package com.harinivelumani.banking;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class BankTransaction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private Long fromAccountId;
    private Long toAccountId;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal amount;
    @Column(nullable=false) private Instant createdAt = Instant.now();
    @Column(nullable=false) private String type;

    protected BankTransaction() {}
    public BankTransaction(Long from, Long to, BigDecimal amount, String type) {
        this.fromAccountId=from; this.toAccountId=to; this.amount=amount; this.type=type;
    }
    public Long getId(){return id;} public Long getFromAccountId(){return fromAccountId;}
    public Long getToAccountId(){return toAccountId;} public BigDecimal getAmount(){return amount;}
    public Instant getCreatedAt(){return createdAt;} public String getType(){return type;}
}
