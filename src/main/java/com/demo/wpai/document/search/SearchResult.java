package com.demo.wpai.document.search;

import com.demo.wpai.document.model.EmbeddedKnowledgeChunk;

public record SearchResult(

        EmbeddedKnowledgeChunk chunk,

        double similarity

) {
}