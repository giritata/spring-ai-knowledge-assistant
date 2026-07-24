package tech.nasr.dto;

import java.util.List;

public record CodeReviewResponse(

        String review,

        List<String> sources

) {
}
