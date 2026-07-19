package com.demo.wpai.controller;

import com.demo.wpai.dto.CodeReviewRequest;
import com.demo.wpai.dto.CodeReviewResponse;
import com.demo.wpai.service.CodeReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class CodeReviewController {

    private final CodeReviewService reviewService;

    public CodeReviewController(
            CodeReviewService reviewService) {

        this.reviewService = reviewService;

    }

    @PostMapping
    public CodeReviewResponse review(

            @RequestBody
            CodeReviewRequest request) {

        String answer =

                reviewService.review(

                        request.question());

        return new CodeReviewResponse(

                answer,

                List.of()

        );

    }

}