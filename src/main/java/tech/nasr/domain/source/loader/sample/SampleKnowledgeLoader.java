package tech.nasr.domain.source.loader.sample;

import org.springframework.stereotype.Component;
import tech.nasr.domain.knowledgebase.KnowledgeBase;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.loader.KnowledgeLoader;

@Component
public class SampleKnowledgeLoader implements KnowledgeLoader {

    @Override
    public KnowledgeBase load(KnowledgeSource source) {

        return new KnowledgeBase(
                "sample-kb",
                "Spring PetClinic",
                source.knowledgeBaseId()
        );
    }
}