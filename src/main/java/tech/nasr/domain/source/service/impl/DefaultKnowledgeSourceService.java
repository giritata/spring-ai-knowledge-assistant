package tech.nasr.domain.source.service.impl;

import org.springframework.stereotype.Service;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.KnowledgeSourceType;
import tech.nasr.domain.source.service.KnowledgeSourceService;

import java.util.List;

@Service
public class DefaultKnowledgeSourceService implements KnowledgeSourceService {

    @Override
    public List<KnowledgeSource> findAll() {

        return List.of(
                new KnowledgeSource(
                        "sample",
                        "petclinic-kb",
                        "Spring PetClinic",
                        KnowledgeSourceType.SAMPLE
                )
        );
    }
}