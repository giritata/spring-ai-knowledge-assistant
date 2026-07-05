package com.demo.wpai.service;

import com.demo.wpai.config.PromptLoader;
import com.demo.wpai.dto.ChatRequest;
import com.demo.wpai.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;
    private final PromptLoader promptLoader;

    public AiService(ChatClient.Builder builder,PromptLoader promptLoader) {
        this.chatClient = builder.build();
        this.promptLoader=promptLoader;
    }

    public ChatResponse chat(ChatRequest request) {

        return chatClient
                .prompt()
                .system(promptLoader.loadSystemPrompt())
                .user(request.question())
                .call()
                .entity(ChatResponse.class);

    }

}
