package com.demo.wpai.controller;

import com.demo.wpai.chat.RagChatService;
import com.demo.wpai.dto.ChatRequest;
import com.demo.wpai.dto.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final RagChatService ragChatService;

    public ChatController(RagChatService ragChatService) {
        this.ragChatService = ragChatService;
    }

    @PostMapping
    public ChatResponse chat(
            @RequestBody ChatRequest request) {

        return ragChatService.chat(request);

    }

}