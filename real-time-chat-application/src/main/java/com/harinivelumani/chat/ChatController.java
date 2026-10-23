package com.harinivelumani.chat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ChatController {
    private final ChatMessageRepository repository;
    public ChatController(ChatMessageRepository repository) { this.repository=repository; }

    public record IncomingMessage(@NotBlank @Size(max=40) String username,
                                  @NotBlank @Size(max=1000) String content) {}

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public ChatMessage send(@Valid IncomingMessage incoming) {
        return repository.save(new ChatMessage(incoming.username().trim(), incoming.content().trim()));
    }

    @RestController
    @RequestMapping("/api/messages")
    static class HistoryController {
        private final ChatMessageRepository repository;
        HistoryController(ChatMessageRepository repository) { this.repository=repository; }
        @GetMapping
        public List<ChatMessage> history() {
            return repository.findTop50ByOrderBySentAtDesc().stream().toList();
        }
    }
}
