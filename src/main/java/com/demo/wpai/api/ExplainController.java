package com.demo.wpai.api;

import com.demo.wpai.ai.model.Explanation;
import com.demo.wpai.api.dto.ExplainRequest;
import com.demo.wpai.api.dto.ExplainResponse;
import com.demo.wpai.api.dto.SourceReference;
import com.demo.wpai.document.search.SearchResult;
import com.demo.wpai.service.CodeExplanationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/explain")

public class ExplainController {

    private final CodeExplanationService codeExplanationService;

    public ExplainController(CodeExplanationService codeExplanationService){
        this.codeExplanationService = codeExplanationService;
    }

    @PostMapping
    public ExplainResponse explain(
            @RequestBody ExplainRequest request) {

        Explanation explanation =
                codeExplanationService.explain(
                        request.question(),
                        request.audience());

        Map<String, List<SearchResult>> groupedSources =
                explanation.searchResults()

                        .stream()

                        .collect(Collectors.groupingBy(

                                result -> result.chunk()
                                        .chunk()
                                        .source()

                        ));

        List<SourceReference> sources =

                groupedSources.entrySet()

                        .stream()

                        .map(entry -> {

                            List<SearchResult> results = entry.getValue();

                            return new SourceReference(

                                    entry.getKey(),

                                    results.get(0)
                                            .chunk()
                                            .chunk()
                                            .type(),

                                    results.stream()

                                            .map(result ->
                                                    result.chunk()
                                                            .chunk()
                                                            .title())

                                            .distinct()

                                            .toList()

                            );

                        })

                        .toList();

        return new ExplainResponse(

                explanation.answer(),

                request.audience(),

                explanation.knowledgeType(),

                explanation.confidence(),

                explanation.executionTimeMs(),

                explanation.searchResults().size(),

                sources.stream()
                        .mapToInt(source -> source.symbols().size())
                        .sum(),

                sources
        );
    }
}