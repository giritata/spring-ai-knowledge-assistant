package tech.nasr.document.model;

import java.time.Instant;
import java.util.List;

public record KnowledgeIndex(

        String projectName,

        String embeddingModel,

        Instant indexedAt,

        List<EmbeddedKnowledgeChunk> chunks

) {
}