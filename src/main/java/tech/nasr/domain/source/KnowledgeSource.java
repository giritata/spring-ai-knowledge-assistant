package tech.nasr.domain.source;

public record KnowledgeSource(

        String id,

        String knowledgeBaseId,

        String name,

        KnowledgeSourceType type,

        String location

) {
}