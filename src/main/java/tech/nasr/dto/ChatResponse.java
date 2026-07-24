package tech.nasr.dto;

import java.util.List;

public record ChatResponse(

        String answer,

        String category,

        String confidence,
        List<String> sources

) {
}