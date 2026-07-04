package com.demo.wpai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String question) {

        return chatClient
                .prompt()
                .system("""
                    You are an enterprise banking assistant.

                    You answer only banking and finance questions.

                    If someone asks unrelated questions,
                    politely tell them that you only assist
                    with banking topics.

                    Keep responses concise and professional.
                    """)
                .user(question)
                .call()
                .content();

    }

}
