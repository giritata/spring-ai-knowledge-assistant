package tech.nasr.domain.source.service.impl;

import org.springframework.stereotype.Service;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.registry.KnowledgeSourceRegistry;
import tech.nasr.domain.source.service.KnowledgeSourceService;

import java.util.List;

@Service
public class DefaultKnowledgeSourceService implements KnowledgeSourceService {

    private final KnowledgeSourceRegistry registry;

    public DefaultKnowledgeSourceService(KnowledgeSourceRegistry registry) {
        this.registry = registry;
    }

    @Override
    public List<KnowledgeSource> findAll() {
        return registry.findAll();
    }
}