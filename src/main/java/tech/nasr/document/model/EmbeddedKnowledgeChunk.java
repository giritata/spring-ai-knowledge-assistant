package tech.nasr.document.model;

public record EmbeddedKnowledgeChunk(

        KnowledgeChunk chunk,

        float[] embedding

) {
}
