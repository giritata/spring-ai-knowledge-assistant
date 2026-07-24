package tech.nasr.domain.source.registry;

import org.springframework.stereotype.Component;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.KnowledgeSourceType;

import java.util.List;

@Component
public class KnowledgeSourceRegistry {

    public List<KnowledgeSource> getKnowledgeSources() {

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