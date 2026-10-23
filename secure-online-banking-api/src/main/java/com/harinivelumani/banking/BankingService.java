package com.harinivelumani.banking;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BankingService {
    private final AccountRepository accounts;
    private final TransactionRepository transactions;
    public BankingService(AccountRepository accounts, TransactionRepository transactions) {
        this.accounts=accounts; this.transactions=transactions;
    }

    public List<Account> myAccounts(String username) { return accounts.findByOwnerUsername(username); }

    public Account getOwnedAccount(Long id, String username) {
        Account a = accounts.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if (!a.getOwnerUsername().equals(username))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This account does not belong to you");
        return a;
    }

    @Transactional
    public BankTransaction transfer(Long fromId, Long toId, BigDecimal amount, String username) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than zero");
        if (fromId.equals(toId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Source and destination must differ");
        Account from = getOwnedAccount(fromId, username);
        Account to = accounts.findById(toId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination account not found"));
        if (from.getBalance().compareTo(amount) < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        from.debit(amount); to.credit(amount);
        accounts.save(from); accounts.save(to);
        return transactions.save(new BankTransaction(fromId, toId, amount, "TRANSFER"));
    }

    public List<BankTransaction> history(Long accountId, String username) {
        getOwnedAccount(accountId, username);
        return transactions.findByFromAccountIdOrToAccountIdOrderByCreatedAtDesc(accountId, accountId);
    }
}
