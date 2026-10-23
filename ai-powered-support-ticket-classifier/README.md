# AI-Powered Support Ticket Classifier

A Spring Boot REST API for creating support tickets, assigning categories, and tracking status.

## Features
- Create and retrieve support tickets
- Baseline keyword categorization: ACCOUNT, PAYMENT, TECHNICAL, GENERAL
- Filter tickets by status and update status
- JPA persistence using an in-memory H2 database
- Request validation

> **Honest project note:** the current implementation uses a transparent keyword baseline, not a trained AI model. Add and evaluate a trained model or a Python model service before describing the ML integration as completed. H2 is in-memory and resets on restart.

## Requirements
- Java 17+
- Maven 3.8+

## Run
```bash
mvn spring-boot:run
```
API starts at `http://localhost:8080`.

## API examples
Create a ticket:
```bash
curl -X POST http://localhost:8080/api/tickets   -H "Content-Type: application/json"   -d '{"subject":"Payment refund missing","description":"I was charged twice for my order."}'
```
List all tickets:
```bash
curl http://localhost:8080/api/tickets
```
Filter by status:
```bash
curl "http://localhost:8080/api/tickets?status=OPEN"
```
Update status:
```bash
curl -X PATCH http://localhost:8080/api/tickets/1/status   -H "Content-Type: application/json"   -d '{"status":"IN_PROGRESS"}'
```

## Next improvements
- Replace keyword classifier with a trained model and report precision/recall
- Use MySQL/PostgreSQL for durable storage
- Add authentication, pagination, audit logging, and integration tests
