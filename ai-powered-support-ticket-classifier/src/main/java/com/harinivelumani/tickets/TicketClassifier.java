package com.harinivelumani.tickets;

import org.springframework.stereotype.Service;
import java.util.Locale;

/**
 * Baseline keyword classifier. Replace/extend this service with a trained ML model
 * or a Python model API before describing it as ML-powered.
 */
@Service
public class TicketClassifier {
    public TicketCategory classify(String text) {
        String value = text.toLowerCase(Locale.ROOT);
        if (containsAny(value, "payment", "refund", "charged", "invoice", "transaction"))
            return TicketCategory.PAYMENT;
        if (containsAny(value, "password", "login", "account", "profile", "sign in"))
            return TicketCategory.ACCOUNT;
        if (containsAny(value, "error", "bug", "crash", "not working", "server", "technical"))
            return TicketCategory.TECHNICAL;
        return TicketCategory.GENERAL;
    }
    private boolean containsAny(String text, String... words) {
        for (String word : words) if (text.contains(word)) return true;
        return false;
    }
}
