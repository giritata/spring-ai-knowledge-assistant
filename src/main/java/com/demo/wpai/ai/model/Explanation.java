package com.demo.wpai.ai.model;

import com.demo.wpai.ai.prompt.KnowledgeType;
import com.demo.wpai.document.search.SearchResult;

import java.util.List;

public record Explanation(

        String answer,

        KnowledgeType knowledgeType,

        List<SearchResult> searchResults,

        double confidence,

        long executionTimeMs

) {
}