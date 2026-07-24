package tech.nasr.domain.source.registry;

import org.springframework.stereotype.Component;
import tech.nasr.config.KnowledgeSourceProperties;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.KnowledgeSourceType;

import java.util.List;

@Component
public class KnowledgeSourceRegistry {

    private final KnowledgeSourceProperties properties;

    public KnowledgeSourceRegistry(
            KnowledgeSourceProperties properties) {

        this.properties = properties;
    }

    public List<KnowledgeSource> findAll() {

        return properties.getSources()
                .stream()
                .map(source -> new KnowledgeSource(

                        source.getId(),

                        source.getKnowledgeBaseId(),

                        source.getName(),

                        source.getType(),

                        source.getLocation()

                ))
                .toList();

    }
}