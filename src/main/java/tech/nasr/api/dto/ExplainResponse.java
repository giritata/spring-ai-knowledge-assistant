package tech.nasr.api.dto;

import tech.nasr.ai.prompt.Audience;
import tech.nasr.ai.prompt.KnowledgeType;

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