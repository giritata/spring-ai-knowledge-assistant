package tech.nasr.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nasr.api.dto.KnowledgeSourceResponse;
import tech.nasr.api.mapper.KnowledgeSourceMapper;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.service.KnowledgeSourceService;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-sources")
public class KnowledgeSourceController {

    private final KnowledgeSourceService knowledgeSourceService;

    private final KnowledgeSourceMapper mapper;

    public KnowledgeSourceController(
            KnowledgeSourceService knowledgeSourceService,
            KnowledgeSourceMapper mapper) {

        this.knowledgeSourceService = knowledgeSourceService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<KnowledgeSourceResponse> findAll() {

        return knowledgeSourceService.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

}