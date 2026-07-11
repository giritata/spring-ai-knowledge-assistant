package com.demo.wpai.rag;

import com.demo.wpai.document.model.KnowledgeChunk;

public record SearchResult(

        KnowledgeChunk chunk,

        double similarity

) {
}
