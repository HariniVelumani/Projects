package com.harinivelumani.tickets;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SupportTicket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String subject;
    @Column(nullable = false, length = 2000)
    private String description;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private TicketCategory category;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private TicketStatus status = TicketStatus.OPEN;
    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    protected SupportTicket() {}
    public SupportTicket(String subject, String description, TicketCategory category) {
        this.subject = subject; this.description = description; this.category = category;
    }
    public Long getId() { return id; }
    public String getSubject() { return subject; }
    public String getDescription() { return description; }
    public TicketCategory getCategory() { return category; }
    public TicketStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
