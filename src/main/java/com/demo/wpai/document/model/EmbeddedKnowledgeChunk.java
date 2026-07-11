package com.demo.wpai.document.model;

public record EmbeddedKnowledgeChunk(

        KnowledgeChunk chunk,

        float[] embedding

) {
}
