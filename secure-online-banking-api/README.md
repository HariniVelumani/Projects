# Secure Online Banking API

A learning/demo Spring Boot backend for account balance queries, transfers, and transaction history.

## Features
- Spring Security HTTP Basic authentication (JWT is **not implemented in this starter**)
- BCrypt password encoding for demo users
- Account ownership checks
- Transfer validation and insufficient-funds handling
- Transaction history with H2/JPA persistence

## Requirements
- Java 17+
- Maven 3.8+

## Run
```bash
mvn spring-boot:run
```
Demo credentials: `harini` / `ChangeMe123!` and `demo` / `ChangeMe123!`.
These are local demo credentials only. Do not deploy this application as a real banking system.

## API examples
List your accounts:
```bash
curl -u harini:ChangeMe123! http://localhost:8080/api/accounts
```
Check account 1 balance:
```bash
curl -u harini:ChangeMe123! http://localhost:8080/api/accounts/1/balance
```
Transfer 100 from account 1 to account 3:
```bash
curl -u harini:ChangeMe123! -X POST http://localhost:8080/api/transfers   -H "Content-Type: application/json"   -d '{"fromAccountId":1,"toAccountId":3,"amount":100.00}'
```
Transaction history:
```bash
curl -u harini:ChangeMe123! http://localhost:8080/api/accounts/1/transactions
```

## Important limitations and next steps
- This starter uses HTTP Basic, not JWT. Add a JWT login endpoint and stateless token filter before claiming JWT support.
- H2 is in-memory and resets on restart; use MySQL/PostgreSQL for persistent storage.
- This is an educational demo, not production banking software. Production use requires security review, concurrency-safe transfer design, audit controls, rate limits, secrets management, and tests.
