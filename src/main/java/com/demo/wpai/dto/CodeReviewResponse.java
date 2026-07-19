package com.demo.wpai.dto;

import java.util.List;

public record CodeReviewResponse(

        String review,

        List<String> sources

) {
}
