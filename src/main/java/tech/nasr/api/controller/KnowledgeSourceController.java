package tech.nasr.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.service.KnowledgeSourceService;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-sources")
public class KnowledgeSourceController {

    private final KnowledgeSourceService knowledgeSourceService;

    public KnowledgeSourceController(
            KnowledgeSourceService knowledgeSourceService) {

        this.knowledgeSourceService = knowledgeSourceService;
    }

    @GetMapping
    public List<KnowledgeSource> findAll() {

        return knowledgeSourceService.findAll();

    }

}