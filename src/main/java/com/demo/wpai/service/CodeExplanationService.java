package com.demo.wpai.service;

import com.demo.wpai.ai.prompt.Audience;
import com.demo.wpai.ai.prompt.KnowledgeType;
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

    public CodeExplanationService(
            VectorSearchService vectorSearchService,
            PromptLoader promptLoader,
            ChatModel chatModel,
            KnowledgeContextBuilder knowledgeContextBuilder) {

        this.vectorSearchService = vectorSearchService;
        this.promptLoader = promptLoader;
        this.chatModel = chatModel;
        this.knowledgeContextBuilder = knowledgeContextBuilder;
    }

    public String explain(String question, Audience audience, KnowledgeType knowledgeType){
        List<SearchResult> results =
                vectorSearchService.search(question);

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
                                "knowledgeType",
                                knowledgeType.name(),
                                "context", context,
                                "question", question));

        String finalPrompt =
                systemPrompt
                        + "\n\n"
                        + renderedTask;

        return chatModel.call(finalPrompt);
    }



}
