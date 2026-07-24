package com.demo.wpai.api.dto;

import com.demo.wpai.ai.prompt.Audience;
import com.demo.wpai.ai.prompt.KnowledgeType;

import java.util.List;

public record ExplainResponse(

        String answer,

        Audience audience,

        KnowledgeType knowledgeType,

        double confidence,

        long executionTimeMs,

        int retrievedChunks,

        int usedChunks,

        List<SourceReference> sources

) {
}