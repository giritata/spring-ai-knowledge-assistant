package com.demo.wpai.dto;

public record SearchResponse(

        String id,

        String filename,

        double similarity,

        String content

) {
}