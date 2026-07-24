package com.demo.wpai.api.dto;

import com.demo.wpai.ai.prompt.Audience;

public record ExplainRequest(

        String question,

        Audience audience

) {
}