package com.demo.wpai.service;

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

    public String explain(String question){
        List<SearchResult> results =
                vectorSearchService.search(question);

        String context =
                knowledgeContextBuilder.build(results);

        String template =
                promptLoader.load("explain-code");

        String prompt =
                new PromptTemplate(template)

                        .render(

                                Map.of(

                                        "context", context,

                                        "question", question

                                )

                        );
        return chatModel.call(prompt);
    }



}
