package com.harinivelumani.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.time.Instant;

@Controller
public class PresenceController {
    public record PresenceEvent(String username, String status, Instant time) {}
    public record PresenceRequest(String username, String status) {}

    @MessageMapping("/presence")
    @SendTo("/topic/presence")
    public PresenceEvent presence(PresenceRequest request) {
        String status = "ONLINE".equalsIgnoreCase(request.status()) ? "ONLINE" : "OFFLINE";
        return new PresenceEvent(request.username(), status, Instant.now());
    }
}
