package com.harinivelumani.banking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(AccountRepository accounts) {
        return args -> {
            if (accounts.count() == 0) {
                accounts.save(new Account("harini", new BigDecimal("5000.00")));
                accounts.save(new Account("harini", new BigDecimal("1250.00")));
                accounts.save(new Account("demo", new BigDecimal("3000.00")));
            }
        };
    }
}
