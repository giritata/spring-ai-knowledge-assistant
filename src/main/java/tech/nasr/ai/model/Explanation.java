package tech.nasr.ai.model;

import tech.nasr.ai.prompt.KnowledgeType;
import tech.nasr.document.retrieval.SearchResult;

import java.util.List;

public record Explanation(

        String answer,

        KnowledgeType knowledgeType,

        List<SearchResult> searchResults,

        double confidence,

        long executionTimeMs

) {
}