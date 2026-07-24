package tech.nasr.domain.source.loader;

import org.springframework.stereotype.Component;
import tech.nasr.domain.source.KnowledgeSource;

import java.util.List;

@Component
public class KnowledgeLoaderFactory {

    private final List<KnowledgeLoader> loaders;

    public KnowledgeLoaderFactory(List<KnowledgeLoader> loaders) {
        this.loaders = loaders;
    }

    public KnowledgeLoader get(KnowledgeSource source) {

        return loaders.stream()
                .filter(loader -> loader.supports(source))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No KnowledgeLoader found for source type: "
                                        + source.type()));
    }

}