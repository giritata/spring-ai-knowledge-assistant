package tech.nasr.document.model;

public record KnowledgeChunk(

        String id,

        String source,

        DocumentType type,

        SymbolType symbolType,

        String title,

        String content,

        int chunkNumber

) {
}