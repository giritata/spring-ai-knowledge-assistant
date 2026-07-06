package com.demo.wpai.service;

import com.demo.wpai.config.PromptLoader;
import com.demo.wpai.dto.ChatRequest;
import com.demo.wpai.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;
    private final PromptLoader promptLoader;

    public AiService(ChatClient.Builder builder, PromptLoader promptLoader, ChatMemory chatMemory) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.promptLoader=promptLoader;
    }

    public ChatResponse chat(ChatRequest request) {

        return chatClient
                .prompt()
                .system(promptLoader.loadSystemPrompt())
                .user(request.question())
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID,request.conversationId()))
                .call()
                .entity(ChatResponse.class);

    }

}
