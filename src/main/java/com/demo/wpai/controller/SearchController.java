package com.demo.wpai.controller;

import com.demo.wpai.document.search.SearchResult;
import com.demo.wpai.document.search.VectorSearchService;
import com.demo.wpai.dto.SearchRequest;
import com.demo.wpai.dto.SearchResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search/code")
public class SearchController {

    private final VectorSearchService vectorSearchService;

    public SearchController(
            VectorSearchService vectorSearchService) {

        this.vectorSearchService = vectorSearchService;

    }

    @PostMapping
    public List<SearchResponse> search(
            @RequestBody SearchRequest request) {

        return vectorSearchService

                .search(request.question())

                .stream()

                .map(this::toResponse)

                .toList();

    }

    private SearchResponse toResponse(
            SearchResult result) {

        return new SearchResponse(

                result.similarity(),

                result.chunk()

                        .chunk()

                        .source(),

                result.chunk()

                        .chunk()

                        .title(),

                preview(

                        result.chunk()

                                .chunk()

                                .content()

                )

        );

    }

    private String preview(String content) {

        return content.substring(
                0,
                Math.min(300,
                        content.length()));
    }

}