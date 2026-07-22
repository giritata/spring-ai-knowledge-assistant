package com.demo.wpai.chat;

import com.demo.wpai.document.prompt.PromptLoader;
import com.demo.wpai.document.search.SearchResult;
import com.demo.wpai.document.search.VectorSearchService;
import com.demo.wpai.dto.ChatRequest;
import com.demo.wpai.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RagChatService {

    private final ChatClient chatClient;

    private final VectorSearchService vectorSearchService;

    private final PromptLoader promptLoader;

    private final PromptContextBuilder contextBuilder;

    public RagChatService(

            ChatClient.Builder builder,

            VectorSearchService vectorSearchService,

            PromptLoader promptLoader,

            PromptContextBuilder contextBuilder) {

        this.chatClient = builder.build();

        this.vectorSearchService = vectorSearchService;

        this.promptLoader = promptLoader;

        this.contextBuilder = contextBuilder;

    }

    public ChatResponse chat(
            ChatRequest request) {

        List<SearchResult> results =

                vectorSearchService.search(
                        request.question());

        String context =

                contextBuilder.buildContext(
                        results);

        String systemPrompt =

                promptLoader.loadSystemPrompt()

                        + "\n\n"

                        + context;

        return chatClient

                .prompt()

                .system(systemPrompt)

                .user(request.question())

                .call()

                .entity(ChatResponse.class);

    }

}
