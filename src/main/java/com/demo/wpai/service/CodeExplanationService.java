package com.demo.wpai.service;

import com.demo.wpai.ai.classifier.KnowledgeTypeClassifier;
import com.demo.wpai.ai.model.Explanation;
import com.demo.wpai.ai.prompt.Audience;
import com.demo.wpai.ai.prompt.KnowledgeType;
import com.demo.wpai.document.model.DocumentType;
import com.demo.wpai.document.prompt.KnowledgeContextBuilder;
import com.demo.wpai.document.prompt.PromptLoader;
import com.demo.wpai.document.prompt.PromptTemplate;
import com.demo.wpai.document.search.SearchResult;
import com.demo.wpai.document.search.VectorSearchService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CodeExplanationService {

    private final VectorSearchService vectorSearchService;

    private final PromptLoader promptLoader;

    private final ChatModel chatModel;

    private final KnowledgeContextBuilder knowledgeContextBuilder;

    private final KnowledgeTypeClassifier knowledgeTypeClassifier;

    public CodeExplanationService(
            VectorSearchService vectorSearchService,
            PromptLoader promptLoader,
            ChatModel chatModel,
            KnowledgeContextBuilder knowledgeContextBuilder,
            KnowledgeTypeClassifier knowledgeTypeClassifier) {

        this.vectorSearchService = vectorSearchService;
        this.promptLoader = promptLoader;
        this.chatModel = chatModel;
        this.knowledgeContextBuilder = knowledgeContextBuilder;
        this.knowledgeTypeClassifier=knowledgeTypeClassifier;
    }

    public Explanation explain(String question, Audience audience){
        long start = System.currentTimeMillis();
        List<SearchResult> results =
                vectorSearchService.search(question);

        double confidence =
                calculateConfidence(results);

        List<DocumentType> documentTypes =
                results.stream()
                        .map(result -> result.chunk()
                                .chunk()
                                .type())
                        .toList();

        KnowledgeType knowledgeType =
                knowledgeTypeClassifier
                        .determineKnowledgeType(documentTypes);

        String context =
                knowledgeContextBuilder.build(results);

        String systemPrompt =
                promptLoader.loadSystemPrompt("enterprise-system");

        String taskPrompt =
                promptLoader.loadTaskPrompt("explain-code");

        String renderedTask =
                new PromptTemplate(taskPrompt)
                        .render(Map.of(
                                "audience", audience.name(),
                                "knowledgeType", knowledgeType.name(),
                                "context", context,
                                "question", question));

        String finalPrompt =
                systemPrompt
                        + "\n\n"
                        + renderedTask;

        String answer =  chatModel.call(finalPrompt);
        long executionTime =
                System.currentTimeMillis() - start;
        return new Explanation(

                answer,

                knowledgeType,

                results,

                confidence,

                executionTime
        );
    }

    private double calculateConfidence(
            List<SearchResult> results) {

        if (results.isEmpty()) {
            return 0.0;
        }

        return results.stream()

                .mapToDouble(SearchResult::similarity)

                .average()

                .orElse(0.0);
    }



}
