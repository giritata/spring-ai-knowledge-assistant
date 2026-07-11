package com.demo.wpai.rag;

import java.time.LocalDateTime;

public record KnowledgeMetadata(

        String filename,

        String documentType,

        LocalDateTime loadedAt,
        String relativePath,
        String language,
        String projectName

) {
}
