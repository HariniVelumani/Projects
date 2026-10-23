package com.harinivelumani.tickets;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<SupportTicket, Long> {
    List<SupportTicket> findByStatus(TicketStatus status);
}
