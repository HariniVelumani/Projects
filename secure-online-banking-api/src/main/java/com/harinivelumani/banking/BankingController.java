package com.harinivelumani.banking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BankingController {
    private final BankingService service;
    private final AccountRepository accounts;
    public BankingController(BankingService service, AccountRepository accounts) {
        this.service=service; this.accounts=accounts;
    }
    public record TransferRequest(@NotNull Long fromAccountId, @NotNull Long toAccountId,
        @NotNull @DecimalMin(value="0.01") BigDecimal amount) {}

    @GetMapping("/accounts")
    public List<Account> accounts(Authentication auth) { return service.myAccounts(auth.getName()); }

    @GetMapping("/accounts/{id}/balance")
    public BigDecimal balance(@PathVariable Long id, Authentication auth) {
        return service.getOwnedAccount(id, auth.getName()).getBalance();
    }

    @PostMapping("/transfers")
    public BankTransaction transfer(@Valid @RequestBody TransferRequest request, Authentication auth) {
        return service.transfer(request.fromAccountId(), request.toAccountId(), request.amount(), auth.getName());
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<BankTransaction> history(@PathVariable Long id, Authentication auth) {
        return service.history(id, auth.getName());
    }
}
