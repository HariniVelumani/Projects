package com.harinivelumani.banking;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String ownerUsername;
    @Column(nullable=false, precision=19, scale=2) private BigDecimal balance;

    protected Account() {}
    public Account(String ownerUsername, BigDecimal balance) {
        this.ownerUsername = ownerUsername; this.balance = balance;
    }
    public Long getId() { return id; }
    public String getOwnerUsername() { return ownerUsername; }
    public BigDecimal getBalance() { return balance; }
    public void debit(BigDecimal amount) { balance = balance.subtract(amount); }
    public void credit(BigDecimal amount) { balance = balance.add(amount); }
}
