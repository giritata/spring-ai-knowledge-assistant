package com.demo.wpai.document.model;

import com.demo.wpai.rag.KnowledgeMetadata;

public record KnowledgeChunk(

        String id,

        String source,

        String content

) {
}