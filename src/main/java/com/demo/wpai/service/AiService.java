package com.demo.wpai.service;

import com.demo.wpai.config.PromptLoader;
import com.demo.wpai.dto.ChatRequest;
import com.demo.wpai.dto.ChatResponse;
import com.demo.wpai.rag.SearchResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {

    private final SimilaritySearchService searchService;

    private final ChatClient chatClient;

    private final PromptLoader promptLoader;

    public AiService(
            SimilaritySearchService searchService,
            ChatClient.Builder builder,
            PromptLoader promptLoader) {

        this.searchService = searchService;

        this.chatClient = builder.build();

        this.promptLoader = promptLoader;

    }

    public ChatResponse chat(ChatRequest request) {

        String question = request.question();

        List<SearchResult> documents =
                searchService.search(question);

        List<String> sources =
                documents.stream()

                        .map(r ->
                                r.chunk()
                                        .metadata()
                                        .filename())

                        .distinct()

                        .toList();

        double bestSimilarity =
                documents.get(0)
                        .similarity();

        String confidence;

        if (bestSimilarity >= 0.90) {

            confidence = "HIGH";

        } else if (bestSimilarity >= 0.75) {

            confidence = "MEDIUM";

        } else {

            confidence = "LOW";

        }

        if (bestSimilarity < 0.65) {

            return new ChatResponse(

                    "I couldn't find that information in the knowledge base.",

                    "Knowledge Search",

                    "LOW",

                    List.of()

            );

        }

        String context = buildContext(documents);

        String prompt = loadRagPrompt()
                .replace("{{context}}", context)
                .replace("{{question}}", question);

        String answer = chatClient
                .prompt()
                .system(promptLoader.loadSystemPrompt())
                .user(prompt)
                .call()
                .content();

        return new ChatResponse(answer, "Knowledge search", "High", sources);

    }

    public String loadRagPrompt() {
        return promptLoader.loadPrompt("prompts/rag-system-prompt.txt");
    }

    private String buildContext(
            List<SearchResult> results) {
        StringBuilder builder =
                new StringBuilder();


        for (SearchResult result : results) {
            builder.append("====================================\n");
            builder.append("Source : ");
            builder.append(result.chunk()
                    .metadata()
                    .filename());
            builder.append("\n\n");
            builder.append(result.chunk().content());

            builder.append("\n\n");

        }
        return builder.toString();
    }

}
