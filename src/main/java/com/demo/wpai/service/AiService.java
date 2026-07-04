package com.demo.wpai.service;

import com.demo.wpai.config.PromptLoader;
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

    public String ask(String question) {

        return chatClient
                .prompt()
                .system(promptLoader.loadSystemPrompt())
                .user(question)
                .call()
                .content();

    }

}
