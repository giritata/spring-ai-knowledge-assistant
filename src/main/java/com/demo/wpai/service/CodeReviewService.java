package com.demo.wpai.service;

import com.demo.wpai.document.search.SearchResult;
import com.demo.wpai.document.search.VectorSearchService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeReviewService {

    private final VectorSearchService vectorSearchService;

    private final ReviewPromptBuilder promptBuilder;

    private final ChatClient chatClient;

    public CodeReviewService(

            VectorSearchService vectorSearchService,

            ReviewPromptBuilder promptBuilder,

            ChatClient.Builder builder) {

        this.vectorSearchService = vectorSearchService;

        this.promptBuilder = promptBuilder;

        this.chatClient = builder.build();

    }

    public String review(String question) {

        List<SearchResult> results =

                vectorSearchService.search(question);

        String prompt =

                promptBuilder.build(

                        question,

                        results);

        return chatClient.prompt()

                .user(prompt)

                .call()

                .content();

    }

}