package com.demo.wpai.dto;

public record SearchResponse(

        double similarity,

        String source,

        String preview

) {
}