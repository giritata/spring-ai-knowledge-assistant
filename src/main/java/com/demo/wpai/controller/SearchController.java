package com.demo.wpai.controller;

import com.demo.wpai.dto.SearchResponse;
import com.demo.wpai.rag.SearchResult;
import com.demo.wpai.service.SimilaritySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.wpai.mapper.SearchMapper;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SimilaritySearchService similaritySearchService;

    @GetMapping
    public List<SearchResponse> search(
            @RequestParam String question) {

        SearchMapper searchMapper = new SearchMapper();

        return similaritySearchService.search(question)
                .stream()
                .map(searchMapper::toResponse)
                .toList();

    }
    private SearchResponse toResponse(SearchResult result) {

        return new SearchResponse(

                result.chunk().id(),

                result.chunk().toString(),

                result.similarity(),

                result.chunk().content()

        );
    }


}