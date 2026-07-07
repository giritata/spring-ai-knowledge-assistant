package com.demo.wpai.rag;

import java.util.Map;

public record KnowledgeChunk(

        String id,

        String content,

        float[] embedding,
        KnowledgeMetadata metadata

) {
}