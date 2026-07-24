package tech.nasr.dto;

public record SearchResponse(

        double similarity,

        String source,

        String title,

        String content

) {
}