package com.harinivelumani.chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document("messages")
public class ChatMessage {
    @Id private String id;
    private String username;
    private String content;
    private Instant sentAt;
    public ChatMessage() {}
    public ChatMessage(String username, String content) {
        this.username=username; this.content=content; this.sentAt=Instant.now();
    }
    public String getId(){return id;}
    public String getUsername(){return username;}
    public String getContent(){return content;}
    public Instant getSentAt(){return sentAt;}
}
