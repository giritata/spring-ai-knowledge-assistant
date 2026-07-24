package tech.nasr.document.retrieval;

import tech.nasr.document.model.EmbeddedKnowledgeChunk;

public record SearchResult(

        EmbeddedKnowledgeChunk chunk,

        double similarity

) {
}