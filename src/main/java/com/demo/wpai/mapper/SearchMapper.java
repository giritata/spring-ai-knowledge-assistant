package com.demo.wpai.mapper;

import com.demo.wpai.dto.SearchResponse;
import com.demo.wpai.rag.SearchResult;
import org.springframework.stereotype.Component;

@Component
public class SearchMapper {

    public SearchResponse toResponse(SearchResult result){

        return new SearchResponse(
                result.chunk().id(),
                result.chunk().metadata().filename(),
                result.similarity(),
                result.chunk().content()
        );

    }

}
