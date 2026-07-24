package tech.nasr.controller;

import tech.nasr.chat.RagChatService;
import tech.nasr.dto.ChatRequest;
import tech.nasr.dto.ChatResponse;
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