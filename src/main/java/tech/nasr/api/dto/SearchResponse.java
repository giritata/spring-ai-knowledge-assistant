package tech.nasr.api.dto;

public record SearchResponse(

        double similarity,

        String source,

        String title,

        String content

) {
}