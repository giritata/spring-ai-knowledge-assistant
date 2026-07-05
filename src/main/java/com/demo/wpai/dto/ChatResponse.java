package com.demo.wpai.dto;

public record ChatResponse(

        String answer,

        String category,

        String confidence

) {
}