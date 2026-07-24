package tech.nasr.api.dto;

import tech.nasr.ai.prompt.Audience;

public record ExplainRequest(

        String question,

        Audience audience

) {
}