# Real-Time Chat Application

A Spring Boot chat backend using WebSocket/STOMP for live messages and MongoDB for message history.

## Features
- STOMP WebSocket endpoint at `/ws`
- Publish chat messages to `/app/chat.send`
- Subscribers receive messages at `/topic/messages`
- MongoDB message persistence
- REST endpoint for recent message history
- Simple online/offline presence event topic

## Requirements
- Java 17+
- Maven 3.8+
- MongoDB running locally on port 27017

## Run
Start MongoDB, then:
```bash
mvn spring-boot:run
```

## WebSocket protocol
Connect a STOMP client to `ws://localhost:8080/ws`.
Subscribe to `/topic/messages`, then publish to `/app/chat.send` with JSON:
```json
{"username":"harini","content":"Hello everyone!"}
```
Presence events can be published to `/app/presence`:
```json
{"username":"harini","status":"ONLINE"}
```
Subscribe to `/topic/presence` to receive presence events.

Recent message history:
```bash
curl http://localhost:8080/api/messages
```

## Limitations / next steps
- This repository provides the backend; it does not include a browser chat UI.
- Presence events are client-reported and not a secure authoritative presence system.
- Add authentication, server-side user identity, origin configuration for your actual frontend, message moderation, pagination, and tests before deployment.
