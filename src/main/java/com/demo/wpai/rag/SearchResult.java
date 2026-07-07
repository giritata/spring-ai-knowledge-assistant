package com.demo.wpai.rag;

public record SearchResult(

        KnowledgeChunk chunk,

        double similarity

) {
}
