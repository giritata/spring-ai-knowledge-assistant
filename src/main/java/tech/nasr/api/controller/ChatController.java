package tech.nasr.api.controller;

import tech.nasr.intelligence.RagChatService;
import tech.nasr.api.dto.ChatRequest;
import tech.nasr.api.dto.ChatResponse;
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