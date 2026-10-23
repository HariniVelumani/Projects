package com.harinivelumani.tickets;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketRepository repository;
    private final TicketClassifier classifier;

    public TicketController(TicketRepository repository, TicketClassifier classifier) {
        this.repository = repository; this.classifier = classifier;
    }

    public record CreateTicketRequest(
        @NotBlank @Size(max=120) String subject,
        @NotBlank @Size(max=2000) String description) {}
    public record UpdateStatusRequest(TicketStatus status) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupportTicket create(@Valid @RequestBody CreateTicketRequest request) {
        TicketCategory category = classifier.classify(request.subject() + " " + request.description());
        return repository.save(new SupportTicket(request.subject(), request.description(), category));
    }

    @GetMapping
    public List<SupportTicket> list(@RequestParam(required=false) TicketStatus status) {
        return status == null ? repository.findAll() : repository.findByStatus(status);
    }

    @GetMapping("/{id}")
    public SupportTicket get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
    }

    @PatchMapping("/{id}/status")
    public SupportTicket updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest request) {
        if (request.status() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status is required");
        SupportTicket ticket = get(id);
        ticket.setStatus(request.status());
        return repository.save(ticket);
    }
}
