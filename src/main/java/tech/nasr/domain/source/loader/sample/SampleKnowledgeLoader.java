package tech.nasr.domain.source.loader.sample;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.KnowledgeSourceType;
import tech.nasr.domain.source.loader.KnowledgeLoader;

@Component
public class SampleKnowledgeLoader implements KnowledgeLoader {

    @Override
    public boolean supports(KnowledgeSource source) {
        return source.type() == KnowledgeSourceType.SAMPLE;
    }

    @Override
    public Path load(KnowledgeSource source) {
        return Path.of(source.location());
    }

}